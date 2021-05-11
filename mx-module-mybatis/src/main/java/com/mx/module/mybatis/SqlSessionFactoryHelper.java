package com.mx.module.mybatis;

import com.mx.dev.utils.ListUtils;
import com.mx.module.mybatis.exception.MybatisException;
import net.ymate.platform.commons.util.RuntimeUtils;
import net.ymate.platform.core.YMP;
import net.ymate.platform.persistence.jdbc.JDBC;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.type.TypeHandler;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author: mengxiang.
 * @create: 2021-02-23 10:49
 * @Description:
 */
public class SqlSessionFactoryHelper {

    private static SqlSessionFactoryBuilder SQL_SESSION_FACTORY_BUILDER = new SqlSessionFactoryBuilder();


    public static SqlSessionFactory initialize(IMybatisConfig iMybatisConfig) throws Exception {

        DataSource dataSource = JDBC.get().getDefaultDataSourceAdapter().getDataSource();
        Configuration configuration;
        XMLConfigBuilder xmlConfigBuilder = null;
        if (StringUtils.isNotBlank(iMybatisConfig.configLocation())) {
            xmlConfigBuilder = new XMLConfigBuilder(new FileInputStream(iMybatisConfig.configLocation()), null);
            configuration = xmlConfigBuilder.getConfiguration();
        } else {
            configuration = new Configuration();
        }
        if (StringUtils.isNotBlank(iMybatisConfig.configurationProperties())) {
            Properties properties = new Properties();
            properties.load(new FileInputStream(iMybatisConfig.configurationProperties()));
            configuration.setVariables(properties);
        }

        if (iMybatisConfig.objectFactory() != null) {
            configuration.setObjectFactory(iMybatisConfig.objectFactory());
        }

        if (iMybatisConfig.objectWrapperFactory() != null) {
            configuration.setObjectWrapperFactory(iMybatisConfig.objectWrapperFactory());
        }

        if (iMybatisConfig.vfs() != null) {
            configuration.setVfsImpl(iMybatisConfig.vfs());
        }

        if (ListUtils.isNotEmpty(iMybatisConfig.typeAliasesPackage())) {
            for (String packageToScan : iMybatisConfig.typeAliasesPackage()) {
                configuration.getTypeAliasRegistry().registerAliases(packageToScan, iMybatisConfig.typeAliasesSuperType());
            }
        }

        if (ListUtils.isNotEmpty(iMybatisConfig.typeAliases())) {
            for (Class<?> typeAlias : iMybatisConfig.typeAliases()) {
                configuration.getTypeAliasRegistry().registerAlias(typeAlias);
            }
        }

        if (ListUtils.isNotEmpty(iMybatisConfig.plugins())) {
            for (Interceptor plugin : iMybatisConfig.plugins()) {
                configuration.addInterceptor(plugin);
            }
        }

        if (ListUtils.isNotEmpty(iMybatisConfig.typeHandlersPackage())) {
            for (String packageToScan : iMybatisConfig.typeHandlersPackage()) {
                configuration.getTypeHandlerRegistry().register(packageToScan);
            }
        }

        if (ListUtils.isNotEmpty(iMybatisConfig.typeHandlers())) {
            for (TypeHandler<?> typeHandler : iMybatisConfig.typeHandlers()) {
                configuration.getTypeHandlerRegistry().register(typeHandler);
            }
        }

        if (iMybatisConfig.databaseIdProvider() != null) {
            try {
                configuration.setDatabaseId(iMybatisConfig.databaseIdProvider().getDatabaseId(dataSource));
            } catch (Exception e) {
                throw new MybatisException("databaseId获取失败", e);
            }
        }

        if (iMybatisConfig.cache() != null) {
            configuration.addCache(iMybatisConfig.cache());
        }

        if (xmlConfigBuilder != null) {
            try {
                xmlConfigBuilder.parse();
            } catch (Exception ex) {
                throw new MybatisException("xml文件转换失败: " + iMybatisConfig.configLocation(), ex);
            } finally {
                ErrorContext.instance().reset();
            }
        }

        configuration.setEnvironment(new Environment(iMybatisConfig.environment(), iMybatisConfig.transactionFactory(), dataSource));

        if (StringUtils.isNotBlank(iMybatisConfig.mapperLocations())) {

            File mapperLocationsFile = new File(RuntimeUtils.replaceEnvVariable(YMP.get().getParam("mapperLocations")));
            File[] mapperLocationsFiles = mapperLocationsFile.listFiles();
            if (mapperLocationsFiles != null && mapperLocationsFiles.length > 0) {
                for (File mapperFile : mapperLocationsFiles) {
                    InputStream initialStream = new FileInputStream(mapperFile);
                    try {
                        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(initialStream,
                                configuration, mapperFile.getPath(), configuration.getSqlFragments());
                        xmlMapperBuilder.parse();
                    } catch (Exception e) {
                        throw new MybatisException("xml文件转换失败: '" + iMybatisConfig.mapperLocations() + "'", e);
                    } finally {
                        ErrorContext.instance().reset();
                    }
                }
            }
        }

        return SQL_SESSION_FACTORY_BUILDER.build(configuration);
    }

}
