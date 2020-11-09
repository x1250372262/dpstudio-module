/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dpstudio.dev.security;

import com.dpstudio.dev.security.bean.GroupBean;
import com.dpstudio.dev.security.bean.MenuBean;
import com.dpstudio.dev.security.bean.PermissionBean;
import com.dpstudio.dev.security.impl.DefaultSecurityConfig;
import com.dpstudio.dev.security.init.MenuMeta;
import com.dpstudio.dev.security.init.PermissionMeta;
import com.dpstudio.dev.utils.ListUtils;
import net.ymate.platform.core.*;
import net.ymate.platform.core.module.IModule;
import net.ymate.platform.core.module.IModuleConfigurer;
import net.ymate.platform.core.module.impl.DefaultModuleConfigurer;
import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Security generated By ModuleMojo on 2020/06/30 17:49
 *
 * @author YMP (https://www.ymate.net/)
 */
public final class Security implements IModule, ISecurity {


    private static volatile ISecurity instance;

    private IApplication owner;

    private ISecurityConfig config;

    private boolean initialized;

    public static ISecurity get() {
        ISecurity inst = instance;
        if (inst == null) {
            synchronized (Security.class) {
                inst = instance;
                if (inst == null) {
                    instance = inst = YMP.get().getModuleManager().getModule(Security.class);
                }
            }
        }
        return inst;
    }

    public Security() {
    }

    public Security(ISecurityConfig config) {
        this.config = config;
    }

    @Override
    public String getName() {
        return MODULE_NAME;
    }

    @Override
    public void initialize(IApplication owner) throws Exception {
        if (!initialized) {
            //
            YMP.showVersion("Initializing dpstudio-dev-security-security-${version}", new Version(1, 0, 0, Security.class, Version.VersionType.Alpha));
            //
            this.owner = owner;
            if (config == null) {
                IApplicationConfigureFactory configureFactory = owner.getConfigureFactory();
                if (configureFactory != null) {
                    IApplicationConfigurer configurer = configureFactory.getConfigurer();
                    IModuleConfigurer moduleConfigurer = configurer == null ? null : configurer.getModuleConfigurer(MODULE_NAME);
                    if (moduleConfigurer != null) {
                        config = DefaultSecurityConfig.create(configureFactory.getMainClass(), moduleConfigurer);
                    } else {
                        config = DefaultSecurityConfig.create(configureFactory.getMainClass(), DefaultModuleConfigurer.createEmpty(MODULE_NAME));
                    }
                }
                if (config == null) {
                    config = DefaultSecurityConfig.defaultConfig();
                }
            }
            if (!config.isInitialized()) {
                config.initialize(this);
            }
            if (config.isEnabled()) {
                PermissionMeta.init(config);
                MenuMeta.init(config);
            }
            initialized = true;
        }
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    @Override
    public void close() throws Exception {
        if (initialized) {
            initialized = false;
            if (config.isEnabled()) {
                PermissionMeta.init(config);
                MenuMeta.init(config);
            }
            config = null;
            owner = null;
        }
    }

    @Override
    public IApplication getOwner() {
        return owner;
    }

    @Override
    public ISecurityConfig getConfig() {
        return config;
    }

    @Override
    public List<GroupBean> groupList() {
        return PermissionMeta.getGroups();
    }

    @Override
    public List<GroupBean> groupList(String level) {
        return PermissionMeta.getGroups(level);
    }

    @Override
    public List<PermissionBean> permissionList(String groupId) {
        return PermissionMeta.getPermissions(groupId);
    }

    @Override
    public List<PermissionBean> permissionList() {
        return permissionList(null);
    }

    @Override
    public PermissionBean findByCode(List<PermissionBean> permissionBeans, String code) {
        return PermissionMeta.findByCode(permissionBeans, code);
    }

    @Override
    public List<MenuBean> menuList() {
        //热加载
        if (config.isHotLoading()) {
            try {
                return MenuMeta.menuList(config);
            } catch (Exception e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }
        return MenuMeta.Store.get();
    }

    @Override
    public List<MenuBean> permissionMenu() {
        List<MenuBean> menuBeanList = menuList();
        List<String> userPermissions = new ArrayList<>();
        boolean isFounder = false;
        IAuthenticator iAuthenticator = config.authenticatorClass();
        if (iAuthenticator == null) {
            throw new NullArgumentException("authenticator_class");
        }
        try {
            userPermissions = iAuthenticator.userPermissions();
            isFounder = iAuthenticator.isFounder();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isFounder) {
            return menuBeanList;
        }
        if (ObjectUtils.isEmpty(menuBeanList)) {
            return menuBeanList;
        }
        List<MenuBean> newMenuList = new ArrayList<>();
        for (MenuBean menuBean : menuBeanList) {
            if (StringUtils.isBlank(menuBean.permissions())) {
                newMenuList.add(menuBean);
                continue;
            }
            List<String> permissons = Arrays.asList(menuBean.getPermissions().split(","));
            if (ObjectUtils.isEmpty(permissons)) {
                newMenuList.add(menuBean);
                continue;
            }
            if (ObjectUtils.isEmpty(userPermissions)) {
                continue;
            }
            if (ListUtils.checkSame(userPermissions, permissons)) {
                newMenuList.add(menuBean);
            }
        }
        return newMenuList;
    }

    @Override
    public List<MenuBean> permissionMenu(boolean isFounder, List<String> userPermissions) {
        List<MenuBean> menuBeanList = menuList();
        if (isFounder) {
            return menuBeanList;
        }
        if (ObjectUtils.isEmpty(menuBeanList)) {
            return menuBeanList;
        }
        List<MenuBean> newMenuList = new ArrayList<>();
        for (MenuBean menuBean : menuBeanList) {
            if (StringUtils.isBlank(menuBean.permissions())) {
                newMenuList.add(menuBean);
                continue;
            }
            List<String> permissons = Arrays.asList(menuBean.getPermissions().split(","));
            if (ObjectUtils.isEmpty(permissons)) {
                newMenuList.add(menuBean);
                continue;
            }
            if (ObjectUtils.isEmpty(userPermissions)) {
                continue;
            }
            if (ListUtils.checkSame(userPermissions, permissons)) {
                newMenuList.add(menuBean);
            }
        }
        return newMenuList;
    }
}
