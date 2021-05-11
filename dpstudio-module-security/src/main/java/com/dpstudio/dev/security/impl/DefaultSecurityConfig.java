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
package com.dpstudio.dev.security.impl;

import com.dpstudio.dev.security.IAuthenticator;
import com.dpstudio.dev.security.ISecurity;
import com.dpstudio.dev.security.ISecurityConfig;
import com.dpstudio.dev.security.annotation.SecurityConf;
import net.ymate.platform.commons.util.ClassUtils;
import net.ymate.platform.core.configuration.IConfigReader;
import net.ymate.platform.core.module.IModuleConfigurer;
import org.apache.commons.lang3.StringUtils;

/**
 * DefaultSecurityConfig generated By ModuleMojo on 2020/06/30 17:49
 *
 * @author YMP (https://www.ymate.net/)
 */
public final class DefaultSecurityConfig implements ISecurityConfig {

    private boolean enabled = true;
    private boolean hotLoading = false;
    private String packageName;
    private String clientName;
    private String clientTitle;
    private String projectName;
    private String menuFilePath;
    private IAuthenticator authenticatorClass;
    private int verifyTime;
    private String secret;
    private String headerName;
    private String paramName;
    private String headerClientName;
    private String paramClientName;
    private boolean autoResponse = true;
    private boolean unpackFileLogin = true;


    private boolean initialized;

    public static DefaultSecurityConfig defaultConfig() {
        return builder().build();
    }

    public static DefaultSecurityConfig create(IModuleConfigurer moduleConfigurer) {
        return new DefaultSecurityConfig(null, moduleConfigurer);
    }

    public static DefaultSecurityConfig create(Class<?> mainClass, IModuleConfigurer moduleConfigurer) {
        return new DefaultSecurityConfig(mainClass, moduleConfigurer);
    }

    public static Builder builder() {
        return new Builder();
    }

    private DefaultSecurityConfig() {
    }

    private DefaultSecurityConfig(Class<?> mainClass, IModuleConfigurer moduleConfigurer) {
        IConfigReader configReader = moduleConfigurer.getConfigReader();
        SecurityConf confAnn = mainClass == null ? null : mainClass.getAnnotation(SecurityConf.class);
        enabled = configReader.getBoolean(ENABLED, confAnn == null || confAnn.enabled());
        hotLoading = configReader.getBoolean(HOT_LOADING, confAnn == null || confAnn.hotLoading());
        packageName = configReader.getString(PACKAGE_NAME, confAnn != null ? confAnn.packageName() : "");
        clientName = configReader.getString(CLIENT_NAME, confAnn != null ? confAnn.clientName() : "admin");
        clientTitle = configReader.getString(CLIENT_TITLE, confAnn != null ? confAnn.clientTitle() : "后台管理系统");
        projectName = configReader.getString(PROJECT_NAME, confAnn != null ? confAnn.projectName() : "");
        menuFilePath = configReader.getString(MENU_FILE_PATH, confAnn != null ? confAnn.menuFilePath() : "${root}/menu/");
        String authenticatorClassName = configReader.getString(AUTHENTICATOR_CLASS, confAnn != null ? confAnn.authenticatorClass().getName() : null);
        authenticatorClass = ClassUtils.impl(authenticatorClassName, IAuthenticator.class, this.getClass());
        verifyTime = configReader.getInt(VERIFY_TIME, confAnn != null ? confAnn.verifyTime() : 0);
        secret = configReader.getString(SECRET, confAnn != null ? confAnn.secret() : "dpstudioJwt");
        headerName = configReader.getString(HEADER_NAME, confAnn != null ? confAnn.headerName() : "dpstudioJwt");
        paramName = configReader.getString(PARAM_NAME, confAnn != null ? confAnn.paramName() : "dpstudioJwt");
        headerClientName = configReader.getString(HEADER_CLIENT_NAME, confAnn != null ? confAnn.paramName() : "dpstudioJwtClientName");
        paramClientName = configReader.getString(PARAM_CLIENT_NAME, confAnn != null ? confAnn.paramName() : "dpstudioJwtClientName");
        autoResponse = configReader.getBoolean(AUTO_RESPONSE, confAnn == null || confAnn.autoResponse());
        unpackFileLogin = configReader.getBoolean(UNPACK_FILE_LOGIN, confAnn == null || confAnn.unpackFileLogin());
    }

    @Override
    public void initialize(ISecurity owner) {
        if (!initialized) {
            if (enabled) {
                packageName = StringUtils.trimToEmpty(packageName);
                clientName = StringUtils.trimToEmpty(clientName);
                menuFilePath = StringUtils.trimToEmpty(menuFilePath);
                if (authenticatorClass == null) {
                    authenticatorClass = new IAuthenticator.DefaultAuthenticator();
                }
            }
            initialized = true;
        }
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String packageName() {
        return packageName;
    }

    @Override
    public String clientName() {
        return clientName;
    }

    @Override
    public String clientTitle() {
        return clientTitle;
    }

    @Override
    public String projectName() {
        return projectName;
    }

    @Override
    public String menuFilePath() {
        return menuFilePath;
    }

    @Override
    public IAuthenticator authenticatorClass() {
        return authenticatorClass;
    }

    @Override
    public boolean isHotLoading() {
        return hotLoading;
    }

    @Override
    public int verifyTime() {
        return verifyTime;
    }

    @Override
    public String secret() {
        return secret;
    }

    @Override
    public String headerName() {
        return headerName;
    }

    @Override
    public String paramName() {
        return paramName;
    }

    @Override
    public String headerClientName() {
        return headerClientName;
    }

    @Override
    public String paramClientName() {
        return paramClientName;
    }

    @Override
    public boolean autoResponse() {
        return autoResponse;
    }

    @Override
    public boolean unpackFileLogin() {
        return unpackFileLogin;
    }

    public void setEnabled(boolean enabled) {
        if (!initialized) {
            this.enabled = enabled;
        }
    }

    public void setPackageName(String packageName) {
        if (!initialized) {
            this.packageName = packageName;
        }
    }

    public void setClientName(String clientName) {
        if (!initialized) {
            this.clientName = clientName;
        }
    }

    public void setClientTitle(String clientTitle) {
        if (!initialized) {
            this.clientTitle = clientTitle;
        }
    }

    public void setProjectName(String projectName) {
        if (!initialized) {
            this.projectName = projectName;
        }
    }

    public void setMenuFilePath(String menuFilePath) {
        if (!initialized) {
            this.menuFilePath = menuFilePath;
        }
    }

    public void setAuthenticatorClass(IAuthenticator authenticatorClass) {
        if (!initialized) {
            this.authenticatorClass = authenticatorClass;
        }
    }
    public void setHotLoading(boolean hotLoading) {
        if (!initialized) {
            this.hotLoading = hotLoading;
        }
    }
    public void setVerifyTime(int verifyTime) {
        if (!initialized) {
            this.verifyTime = verifyTime;
        }
    }
    public void setSecret(String secret) {
        if (!initialized) {
            this.secret = secret;
        }
    }
    public void setHeaderName(String headerName) {
        if (!initialized) {
            this.headerName = headerName;
        }
    }
    public void setParamName(String paramName) {
        if (!initialized) {
            this.paramName = paramName;
        }
    }

    public void setHeaderClientName(String headerClientName) {
        if (!initialized) {
            this.headerClientName = headerClientName;
        }
    }
    public void setParamClientName(String paramClientName) {
        if (!initialized) {
            this.paramClientName = paramClientName;
        }
    }

    public void setAutoResponse(boolean autoResponse) {
        if (!initialized) {
            this.autoResponse = autoResponse;
        }
    }

    public void setUnpackFileLogin(boolean unpackFileLogin) {
        if (!initialized) {
            this.unpackFileLogin = unpackFileLogin;
        }
    }

    public static final class Builder {

        private final DefaultSecurityConfig config = new DefaultSecurityConfig();

        private Builder() {
        }

        public Builder enabled(boolean enabled) {
            config.setEnabled(enabled);
            return this;
        }

        public Builder packageName(String packageName) {
            config.setPackageName(packageName);
            return this;
        }

        public Builder clientName(String clientName) {
            config.setClientName(clientName);
            return this;
        }

        public Builder clientTitle(String clientTitle) {
            config.setClientTitle(clientTitle);
            return this;
        }

        public Builder projectName(String projectName) {
            config.setProjectName(projectName);
            return this;
        }

        public Builder menuFilePath(String menuFilePath) {
            config.setMenuFilePath(menuFilePath);
            return this;
        }

        public Builder authenticatorClass(IAuthenticator authenticatorClass) {
            config.setAuthenticatorClass(authenticatorClass);
            return this;
        }

        public Builder hotLoading(boolean hotLoading) {
            config.setHotLoading(hotLoading);
            return this;
        }

        public Builder verifyTime(int verifyTime) {
            config.setVerifyTime(verifyTime);
            return this;
        }

        public Builder secret(String secret) {
            config.setSecret(secret);
            return this;
        }

        public Builder headerName(String headerName) {
            config.setHeaderName(headerName);
            return this;
        }

        public Builder paramName(String paramName) {
            config.setParamName(paramName);
            return this;
        }

        public Builder headerClientName(String headerClientName) {
            config.setHeaderClientName(headerClientName);
            return this;
        }

        public Builder paramClientName(String paramClientName) {
            config.setParamClientName(paramClientName);
            return this;
        }

        public Builder autoResponse(boolean autoResponse) {
            config.setAutoResponse(autoResponse);
            return this;
        }

        public Builder unpackFileLogin(boolean unpackFileLogin) {
            config.setUnpackFileLogin(unpackFileLogin);
            return this;
        }

        public DefaultSecurityConfig build() {
            return config;
        }
    }
}