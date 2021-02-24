package com.dpstudio.module.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.dpstudio.module.mybatis.exception.MybatisException;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.ymate.platform.commons.IPasswordProcessor;
import net.ymate.platform.commons.impl.DefaultPasswordProcessor;
import net.ymate.platform.commons.util.FileUtils;
import net.ymate.platform.commons.util.ResourceUtils;
import net.ymate.platform.commons.util.RuntimeUtils;
import net.ymate.platform.core.persistence.base.Type;
import net.ymate.platform.persistence.jdbc.AbstractDatabaseDataSourceAdapter;
import net.ymate.platform.persistence.jdbc.IDatabaseDataSourceConfig;
import net.ymate.platform.persistence.jdbc.JDBC;
import net.ymate.platform.persistence.jdbc.impl.*;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Author: 徐建鹏.
 * @create: 2021-02-23 16:57
 * @Description:
 */
public class DataSourceHelper {

    private static final Log LOG = LogFactory.getLog(DataSourceHelper.class);

    private static IDatabaseDataSourceConfig dataSourceConfig = JDBC.get().getConfig().getDefaultDataSourceConfig();

    private static InputStream getDataSourceConfigFileAsStream(String dsAdapterType, String dataSourceName) {
        if (StringUtils.isBlank(dataSourceName)) {
            throw new NullArgumentException("dataSourceName");
        }
        InputStream inputStream = null;
        File configFile = dataSourceConfig.getConfigFile();
        if (configFile != null) {
            try {
                inputStream = new FileInputStream(configFile);
            } catch (FileNotFoundException ignored) {
            }
        }
        if (inputStream == null) {
            if (StringUtils.isBlank(dsAdapterType)) {
                throw new NullArgumentException("dsAdapterType");
            }
            List<String> filePaths = new ArrayList<>();
            filePaths.add(RuntimeUtils.replaceEnvVariable(String.format("${root}/cfgs/%s_%s.properties", dsAdapterType, dataSourceName)));
            filePaths.add(RuntimeUtils.replaceEnvVariable(String.format("${root}/cfgs/%s.properties", dsAdapterType)));
            inputStream = FileUtils.loadFileAsStream(filePaths.toArray(new String[0]));
            //
            if (inputStream == null) {
                filePaths.clear();
                filePaths.add(String.format("%s_%s.properties", dsAdapterType, dataSourceName));
                filePaths.add(String.format("%s.properties", dsAdapterType));
                //
                inputStream = ResourceUtils.getResourceAsStream(AbstractDatabaseDataSourceAdapter.class, filePaths.toArray(new String[0]));
            }
        }
        return inputStream;
    }

    public static Properties doCreateConfigProperties(InputStream inputStream, boolean forHikari) throws Exception {
        Properties properties = new Properties();
        if (inputStream != null) {
            properties.load(inputStream);
        }
        //
        if (StringUtils.isNotBlank(dataSourceConfig.getDriverClass())) {
            properties.put("driverClassName", dataSourceConfig.getDriverClass());
        }
        properties.put(forHikari ? "jdbcUrl" : "url", dataSourceConfig.getConnectionUrl());
        if (StringUtils.isNotBlank(dataSourceConfig.getUsername())) {
            properties.put("username", StringUtils.trimToEmpty(dataSourceConfig.getUsername()));
        }
        if (StringUtils.isNotBlank(dataSourceConfig.getPassword())) {
            properties.put("password", decryptPasswordIfNeed(dataSourceConfig.getPassword()));
        }
        //
        return properties;
    }

    private static String decryptPasswordIfNeed(String password) throws Exception {
        if (StringUtils.isNotBlank(password) && dataSourceConfig.isPasswordEncrypted()) {
            if (dataSourceConfig.getPasswordClass() != null) {
                return (dataSourceConfig.getPasswordClass().newInstance()).decrypt(password);
            }
            IPasswordProcessor passwordProcessor = JDBC.get().getOwner().getConfigureFactory().getConfigurer().getPasswordProcessor();
            if (passwordProcessor == null) {
                passwordProcessor = new DefaultPasswordProcessor();
            }
            return passwordProcessor.decrypt(password);
        }
        return password;
    }

    private static boolean doCreateDataSourceConfigFile(String dsAdapterType) {
        if (StringUtils.isNotBlank(dsAdapterType)) {
            File configFile = new File(String.format("%s/%s.properties", RuntimeUtils.replaceEnvVariable("${root}/cfgs"), dsAdapterType));
            if (configFile.isAbsolute() && !configFile.exists()) {
                try (InputStream inputStream = AbstractDatabaseDataSourceAdapter.class.getClassLoader().getResourceAsStream(String.format("META-INF/default-%s.properties", dsAdapterType))) {
                    if (inputStream != null) {
                        if (FileUtils.createFileIfNotExists(configFile, inputStream)) {
                            return true;
                        } else if (LOG.isWarnEnabled()) {
                            LOG.warn(String.format("Failed to create default %s config file: %s", dsAdapterType, configFile.getPath()));
                        }
                    }
                } catch (IOException e) {
                    if (LOG.isWarnEnabled()) {
                        LOG.warn(String.format("An exception occurred while trying to generate the default %s config file: %s", dsAdapterType, configFile.getPath()), RuntimeUtils.unwrapThrow(e));
                    }
                }
            }
        }
        return false;
    }


    public static DataSource getDataSource(String className) throws MybatisException {
        DataSource dataSource = null;
        if (DBCPDataSourceAdapter.class.getName().equals(className)) {
            try {
                dataSource = dbcp();
            } catch (Exception e) {
                throw new MybatisException("初始化DBCP失败");
            }
        } else if (C3P0DataSourceAdapter.class.getName().equals(className)) {
            try {
                dataSource = c3p0();
            } catch (Exception e) {
                throw new MybatisException("初始化C3P0失败");
            }
        } else if (DefaultDataSourceAdapter.class.getName().equals(className)) {
            throw new MybatisException("不支持的适配" + DefaultDataSourceAdapter.class.getName());
        } else if (DruidDataSourceAdapter.class.getName().equals(className)) {
            try {
                dataSource = druid();
            } catch (Exception e) {
                throw new MybatisException("初始化Druid失败");
            }
        } else if (HikariCPDataSourceAdapter.class.getName().equals(className)) {
            try {
                dataSource = hikariCP();
            } catch (Exception e) {
                throw new MybatisException("初始化HikariCP失败");
            }
        } else if (JNDIDataSourceAdapter.class.getName().equals(className)) {
            try {
                dataSource = jndid();
            } catch (Exception e) {
                throw new MybatisException("初始化JNDID失败");
            }
        } else {
            throw new MybatisException("不支持的适配");
        }
        return dataSource;
    }

    private static BasicDataSource dbcp() throws Exception {
        BasicDataSource dataSource = null;
        try (InputStream inputStream = getDataSourceConfigFileAsStream(Type.DS_ADAPTER.DBCP, dataSourceConfig.getName())) {
            if (inputStream != null) {
                dataSource = BasicDataSourceFactory.createDataSource(doCreateConfigProperties(inputStream, false));
            } else if (doCreateDataSourceConfigFile(Type.DS_ADAPTER.DBCP)) {
                try (InputStream newInputStream = getDataSourceConfigFileAsStream(Type.DS_ADAPTER.DBCP, dataSourceConfig.getName())) {
                    dataSource = BasicDataSourceFactory.createDataSource(doCreateConfigProperties(newInputStream, false));
                }
            }
        }
        return dataSource;
    }

    private static ComboPooledDataSource c3p0() throws Exception {
        String path = RuntimeUtils.getRootPath();
        if (StringUtils.endsWith(path, "/WEB-INF")) {
            path += "/classes";
        }
        if (StringUtils.endsWith(path, "/classes")) {
            File configFile = new File(path, "c3p0.properties");
            if (!configFile.exists()) {
                try (InputStream inputStream = C3P0DataSourceAdapter.class.getClassLoader().getResourceAsStream("META-INF/default-c3p0.properties")) {
                    if (!FileUtils.createFileIfNotExists(configFile, inputStream) && LOG.isWarnEnabled()) {
                        LOG.warn(String.format("Failed to create default c3p0 config file: %s", configFile.getPath()));
                    }
                } catch (IOException e) {
                    if (LOG.isWarnEnabled()) {
                        LOG.warn(String.format("An exception occurred while trying to generate the default c3p0 config file: %s", configFile.getPath()), RuntimeUtils.unwrapThrow(e));
                    }
                }
            }
        }
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(dataSourceConfig.getDriverClass());
        dataSource.setJdbcUrl(dataSourceConfig.getConnectionUrl());
        dataSource.setUser(dataSourceConfig.getUsername());
        dataSource.setPassword(decryptPasswordIfNeed(dataSourceConfig.getPassword()));
        return dataSource;
    }

    private static InputStream openInputStream() {
        InputStream inputStream = getDataSourceConfigFileAsStream(Type.DS_ADAPTER.DRUID,dataSourceConfig.getName());
        if (inputStream == null) {
            inputStream = getDataSourceConfigFileAsStream(Type.DS_ADAPTER.DBCP,dataSourceConfig.getName());
        }
        return inputStream;
    }

    private static DruidDataSource druid() throws Exception {
        DruidDataSource dataSource = null;
        try (InputStream inputStream = openInputStream()) {
            if (inputStream != null) {
                dataSource = new DruidDataSource();
                DruidDataSourceFactory.config(dataSource, doCreateConfigProperties(inputStream, false));
            } else if (doCreateDataSourceConfigFile(Type.DS_ADAPTER.DRUID)) {
                try (InputStream newInputStream = getDataSourceConfigFileAsStream(Type.DS_ADAPTER.DRUID,dataSourceConfig.getName())) {
                    dataSource = new DruidDataSource();
                    DruidDataSourceFactory.config(dataSource, doCreateConfigProperties(newInputStream, false));
                }
            }
        }
        return dataSource;
    }

    private static DataSource jndid() throws Exception {
        Context initialContext = new InitialContext();
        Context envContext = (Context) initialContext.lookup("java:/comp/env");
        // 从JNDI获取数据库源
        DataSource dataSource = (DataSource) envContext.lookup(dataSourceConfig.getConnectionUrl());
        return dataSource;
    }

    private static HikariDataSource hikariCP() throws Exception {
        HikariDataSource dataSource = null;
        try (InputStream inputStream = getDataSourceConfigFileAsStream(Type.DS_ADAPTER.HIKARICP,dataSourceConfig.getName())) {
            dataSource = new HikariDataSource(new HikariConfig(doCreateConfigProperties(inputStream, true)));
        }
        try (InputStream inputStream = getDataSourceConfigFileAsStream(Type.DS_ADAPTER.HIKARICP,dataSourceConfig.getName())) {
            if (inputStream != null) {
                dataSource = new HikariDataSource(new HikariConfig(doCreateConfigProperties(inputStream, true)));
            } else if (doCreateDataSourceConfigFile(Type.DS_ADAPTER.HIKARICP)) {
                try (InputStream newInputStream = getDataSourceConfigFileAsStream(Type.DS_ADAPTER.HIKARICP,dataSourceConfig.getName())) {
                    dataSource = new HikariDataSource(new HikariConfig(doCreateConfigProperties(newInputStream, true)));
                }
            }
        }
        return dataSource;
    }
}
