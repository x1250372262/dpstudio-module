package com.dpstudio.dev.security.utils;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.StrUtil;
import com.dpstudio.dev.security.ISecurityConfig;
import com.dpstudio.dev.security.Security;
import com.dpstudio.module.security.core.SecurityConstants;
import com.dpstudio.module.security.service.ISecurityAdminService;
import com.dpstudio.module.security.service.ISecuritySettingService;
import net.ymate.platform.commons.util.FileUtils;
import net.ymate.platform.commons.util.RuntimeUtils;
import net.ymate.platform.core.YMP;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @Author: mengxiang.
 * @create: 2021-03-15 11:07
 * @Description:
 */
public class UnpackUtils {


    public static void unpackErrorFile(ISecurityConfig config) {
        String rootPath = RuntimeUtils.getRootPath();
        File targetPath = new File(RuntimeUtils.getRootPath(false));
        File locker = new File(rootPath, String.format(".unpack%s%s", File.separator, "dpstudio.error"));
        if(StringUtils.isNotBlank(config.projectName())){
            targetPath = new File(RuntimeUtils.getRootPath(true),config.projectName());
        }
        if (!locker.exists()) {
            try {
                if (FileUtils.unpackJarFile("dpstudio.error", targetPath, Security.class)) {
                    locker.getParentFile().mkdirs();
                    locker.createNewFile();
                }
            } catch (IOException e) {
                throw new RuntimeException("解压文件失败");
            }
        }
    }

    public static void initDatabase(ISecurityConfig config) {
        File locker = new File(RuntimeUtils.getRootPath(), String.format(".unpack%s%s", File.separator, "dpstudio.database"));
        if (!locker.exists()) {
            try {
                //初始化管理员
                YMP.get().getBeanFactory().getBean(ISecurityAdminService.class).init(config.clientName());
                //初始化setting
                YMP.get().getBeanFactory().getBean(ISecuritySettingService.class).init(config.clientName());
                locker.getParentFile().mkdirs();
                locker.createNewFile();
            } catch (Exception e) {
                throw new RuntimeException("初始化管理员数据库失败", e);
            }
        }
    }

    private static void replaceFile(File targetPath, String templateFile, String outFilePath, String clientName) {
        String htmlText = IoUtil.read(java.util.Objects.requireNonNull(Security.class.getResourceAsStream("/META-INF/dpstudio.templates/" + templateFile)), StandardCharsets.UTF_8);
        htmlText = StrUtil.replace(htmlText, "{{clientName}}", clientName);
        File outFile = new File(targetPath, outFilePath);
        FileWriter fileWriter = FileWriter.create(outFile, StandardCharsets.UTF_8);
        fileWriter.write(htmlText);
    }

    private static void unpack(ISecurityConfig config,String clientName, String title) {
        File targetPath = new File(RuntimeUtils.getRootPath(false));
        File locker;
        if(StringUtils.isNotBlank(config.projectName())){
            targetPath = new File(RuntimeUtils.getRootPath(true),config.projectName());
        }
        String rootPath = RuntimeUtils.getRootPath();
        if (StringUtils.isBlank(clientName)) {
//            targetPath = new File(RuntimeUtils.getRootPath(false));
            locker = new File(rootPath, String.format(".unpack%s%s", File.separator, "dpstudio.security"));
        } else {
            targetPath = new File(targetPath, clientName);
            if (!targetPath.exists()) {
                targetPath.mkdirs();
            }
            locker = new File(rootPath, String.format(".unpack%s%s", File.separator, "dpstudio.security_" + clientName));
        }
        if (!locker.exists()) {
            try {
                String outFilePrefix = "dpstudio" + File.separator + "security" + File.separator;
                //输出loginView.html
                String loginHtmlText = IoUtil.read(Objects.requireNonNull(Security.class.getResourceAsStream("/META-INF/dpstudio.templates/login_view.html")), StandardCharsets.UTF_8);
                loginHtmlText = StrUtil.replace(loginHtmlText, "{{title}}", title);
                loginHtmlText = StrUtil.replace(loginHtmlText, "{{clientName}}", clientName);
                loginHtmlText = StrUtil.replace(loginHtmlText, "{{baseDir}}", StringUtils.defaultIfBlank(clientName, "admin"));
                File outFile = new File(targetPath, outFilePrefix + "login_view.html");
                FileWriter fileWriter = FileWriter.create(outFile, StandardCharsets.UTF_8);
                fileWriter.write(loginHtmlText);
                //输出administrator/admin_log.html
                replaceFile(targetPath,"administrator/admin_log.html", outFilePrefix + "administrator" + File.separator + "admin_log.html",clientName);
                //输出administrator/admin_role.html
                replaceFile(targetPath,"administrator/admin_role.html", outFilePrefix + "administrator" + File.separator + "admin_role.html",clientName);
                //输出administrator/info.html
                replaceFile(targetPath,"administrator/info.html", outFilePrefix + "administrator" + File.separator + "info.html",clientName);
                //输出administrator/list.html
                replaceFile(targetPath,"administrator/list.html", outFilePrefix + "administrator" + File.separator + "list.html",clientName);
                //输出administrator/password.html
                replaceFile(targetPath,"administrator/password.html", outFilePrefix + "administrator" + File.separator + "password.html",clientName);
                //输出log/operation_log.html
                replaceFile(targetPath,"log/operation_log.html", outFilePrefix + "log" + File.separator + "operation_log.html",clientName);
                //输出permission/permission.html
                replaceFile(targetPath,"permission/permission.html", outFilePrefix + "permission" + File.separator + "permission.html",clientName);
                //输出role/list.html
                replaceFile(targetPath,"role/list.html", outFilePrefix + "role" + File.separator + "list.html",clientName);
                //输出setting/setting.html
                replaceFile(targetPath,"setting/setting.html", outFilePrefix + "setting" + File.separator + "setting.html",clientName);
                locker.getParentFile().mkdirs();
                locker.createNewFile();
//                if (FileUtils.unpackJarFile("dpstudio.security", targetPath, this.getClass())) {
//
//                }
            } catch (IOException e) {
                throw new RuntimeException("解压文件失败", e);
            }
        }
    }

    public static synchronized void unpackFile(ISecurityConfig config) {

        String clientName = config.clientName();
        String clientTitle = config.clientTitle();
        List<String> titleList = new ArrayList<>();
        if (StringUtils.isBlank(clientTitle)) {
            titleList.add(SecurityConstants.DEFAULT_CLIENT_TITLE);
        } else if (!clientTitle.contains("|")) {
            titleList.add(clientTitle);
        } else {
            String[] clientTitleArray = clientTitle.split("\\|");
            if (clientTitleArray.length <= 0) {
                titleList.add(SecurityConstants.DEFAULT_CLIENT_TITLE);
            } else {
                titleList.addAll(Arrays.asList(clientTitleArray));
            }
        }
        try {
            if (StringUtils.isBlank(clientName) || !clientName.contains("|")) {
                unpack(config,"", titleList.get(0));
            }
            String[] clientNameArray = clientName.split("\\|");
            if (clientNameArray.length <= 0) {
                unpack(config,"", titleList.get(0));
            }
            for (int i = 0; i < clientNameArray.length; i++) {
                if (titleList.size() == clientNameArray.length) {
                    unpack(config,clientNameArray[i], titleList.get(i));
                } else {
                    unpack(config,clientNameArray[i], titleList.get(0));
                }

            }
        } catch (Exception e) {
            throw new RuntimeException("解压文件失败", e);
        }

    }
}
