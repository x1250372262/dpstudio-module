package com.dpstudio.dev.security.init;

import cn.hutool.core.io.FileUtil;
import com.dpstudio.dev.security.ISecurityConfig;
import com.dpstudio.dev.security.Security;
import com.dpstudio.dev.security.bean.MenuBean;
import com.dpstudio.dev.security.utils.Objects;
import net.ymate.platform.commons.util.RuntimeUtils;
import net.ymate.platform.configuration.impl.XMLConfigFileParser;
import net.ymate.platform.core.configuration.IConfigFileParser;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: mengxiang.
 * @Date: 2019-01-17.
 * @Time: 08:24.
 * @Description:
 */
public class MenuMeta {


    public static class Store {

        public static Map<String, List<MenuBean>> MENU_BEANS = new HashMap<>();

        public static void set(String clientName, List<MenuBean> menuBeans) {
            MENU_BEANS.put(StringUtils.defaultIfBlank(clientName, "menu"), menuBeans);
        }

        public static void setAll(Map<String, List<MenuBean>> menuBeans) {
            MENU_BEANS.putAll(menuBeans);
        }

        public static List<MenuBean> get(String clientName) {
            return MENU_BEANS.get(StringUtils.defaultIfBlank(clientName, "menu"));
        }
    }

    private static final Log LOG = LogFactory.getLog(MenuMeta.class);

    private MenuMeta() {
    }

    /**
     * 创建权限列表
     */
    public static void init(ISecurityConfig securityConfig) {
        try {
            Map<String, List<MenuBean>> menuList = menuList(securityConfig);
            Store.setAll(menuList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOG.info("菜单收集成功");
    }

    private static List<MenuBean> securityMenu(String clientName) throws Exception {
        List<MenuBean> menuList = new ArrayList<>();
        InputStream inputStream = Security.class.getResourceAsStream("/META-INF/menu/security.xml");
        if (inputStream == null) {
            return menuList;
        }
        IConfigFileParser handler = new XMLConfigFileParser(inputStream).load(true);
        Map<String, IConfigFileParser.Category> categoryMap = handler.getCategories();
        categoryMap.forEach((k, v) -> {
            Map<String, IConfigFileParser.Property> propertyMap = handler.getCategory(v.getName()).getProperties();
            propertyMap.forEach((k1, v1) -> {
                MenuBean menuBean = MenuBean.builder()
                        .id(Objects.get(v1.getAttribute("id"), ""))
                        .name(StringUtils.defaultIfBlank(v1.getName(), ""))
                        .value(StringUtils.defaultIfBlank(v1.getContent(), ""))
                        .icon(Objects.get(v1.getAttribute("icon"), ""))
                        .url(Objects.get(v1.getAttribute("url"), ""))
                        .pid(Objects.get(v1.getAttribute("pid"), ""))
                        .permissions(Objects.get(v1.getAttribute("permissions"), ""))
                        .path(Objects.get(v1.getAttribute("path"), ""));
                if (StringUtils.isNoneBlank(menuBean.getUrl())) {
                    menuBean.setUrl("/" + clientName + menuBean.getUrl());
                }
                menuList.add(menuBean);
            });
        });
        return menuList;
    }

    public static Map<String, List<MenuBean>> menuList(ISecurityConfig securityConfig) throws Exception {
        Map<String, List<MenuBean>> menuMap = new HashMap<>();
        File menuFilePath = new File(RuntimeUtils.replaceEnvVariable(securityConfig.menuFilePath()));
        if (!menuFilePath.exists()) {
            return menuMap;
        }
        File[] menuFiles = menuFilePath.listFiles();
        if (menuFiles == null || menuFiles.length <= 0) {
            return menuMap;
        }
        for (File file : menuFiles) {
            IConfigFileParser handler = new XMLConfigFileParser(file).load(true);
            Map<String, IConfigFileParser.Category> categoryMap = handler.getCategories();
            List<MenuBean> menuList = new ArrayList<>();
            categoryMap.forEach((k, v) -> {
                Map<String, IConfigFileParser.Property> propertyMap = handler.getCategory(v.getName()).getProperties();
                propertyMap.forEach((k1, v1) -> {
                    MenuBean menuBean = MenuBean.builder()
                            .id(Objects.get(v1.getAttribute("id"), ""))
                            .name(StringUtils.defaultIfBlank(v1.getName(), ""))
                            .value(StringUtils.defaultIfBlank(v1.getContent(), ""))
                            .icon(Objects.get(v1.getAttribute("icon"), ""))
                            .url(Objects.get(v1.getAttribute("url"), ""))
                            .pid(Objects.get(v1.getAttribute("pid"), ""))
                            .permissions(Objects.get(v1.getAttribute("permissions"), ""))
                            .path(Objects.get(v1.getAttribute("path"), ""));
                    menuList.add(menuBean);
                });
            });
            String clientName = file.getName().substring(0, file.getName().lastIndexOf("."));
            List<MenuBean> securityMenuList = securityMenu(clientName);
            if (!securityMenuList.isEmpty()) {
                menuList.addAll(securityMenuList);
            }
            menuMap.put(clientName, menuList);
        }
        return menuMap;
    }


    public static List<MenuBean> menuListByClientName(ISecurityConfig securityConfig, String clientName) throws Exception {
        List<MenuBean> menuList = new ArrayList<>();
        File file = new File(RuntimeUtils.replaceEnvVariable(securityConfig.menuFilePath()), clientName + ".xml");
        if (!file.exists()) {
            return menuList;
        }
        IConfigFileParser handler = new XMLConfigFileParser(file).load(true);
        Map<String, IConfigFileParser.Category> categoryMap = handler.getCategories();
        categoryMap.forEach((k, v) -> {
            Map<String, IConfigFileParser.Property> propertyMap = handler.getCategory(v.getName()).getProperties();
            propertyMap.forEach((k1, v1) -> {
                MenuBean menuBean = MenuBean.builder()
                        .id(Objects.get(v1.getAttribute("id"), ""))
                        .name(StringUtils.defaultIfBlank(v1.getName(), ""))
                        .value(StringUtils.defaultIfBlank(v1.getContent(), ""))
                        .icon(Objects.get(v1.getAttribute("icon"), ""))
                        .url(Objects.get(v1.getAttribute("url"), ""))
                        .pid(Objects.get(v1.getAttribute("pid"), ""))
                        .permissions(Objects.get(v1.getAttribute("permissions"), ""))
                        .path(Objects.get(v1.getAttribute("path"), ""));
                menuList.add(menuBean);
            });
        });
        List<MenuBean> securityMenuList = securityMenu(clientName);
        if (!securityMenuList.isEmpty()) {
            menuList.addAll(securityMenuList);
        }
        return menuList;
    }

}
