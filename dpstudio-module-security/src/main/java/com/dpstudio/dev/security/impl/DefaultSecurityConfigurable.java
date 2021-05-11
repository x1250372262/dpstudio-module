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
import net.ymate.platform.core.module.IModuleConfigurer;
import net.ymate.platform.core.module.impl.DefaultModuleConfigurable;

/**
 * DefaultSecurityConfig generated By ModuleMojo on 2020/06/30 17:49
 *
 * @author YMP (https://www.ymate.net/)
 */
public final class DefaultSecurityConfigurable extends DefaultModuleConfigurable {

    public static Builder builder() {
        return new Builder();
    }

    private DefaultSecurityConfigurable() {
        super(ISecurity.MODULE_NAME);
    }

    public static final class Builder {

        private final DefaultSecurityConfigurable configurable = new DefaultSecurityConfigurable();

        private Builder() {
        }

        public Builder enabled(boolean enabled) {
            configurable.addConfig(ISecurityConfig.ENABLED, String.valueOf(enabled));
            return this;
        }

        public Builder packageName(String packageName) {
            configurable.addConfig(ISecurityConfig.PACKAGE_NAME, packageName);
            return this;
        }

        public Builder clientName(String clientName) {
            configurable.addConfig(ISecurityConfig.CLIENT_NAME, clientName);
            return this;
        }

        public Builder clientTitle(String clientTitle) {
            configurable.addConfig(ISecurityConfig.CLIENT_TITLE, clientTitle);
            return this;
        }

        public Builder projectName(String projectName) {
            configurable.addConfig(ISecurityConfig.PROJECT_NAME, projectName);
            return this;
        }

        public Builder menuFilePath(String menuFilePath) {
            configurable.addConfig(ISecurityConfig.MENU_FILE_PATH, menuFilePath);
            return this;
        }

        public Builder authenticatorClass(Class<? extends IAuthenticator> authenticatorClass) {
            configurable.addConfig(ISecurityConfig.AUTHENTICATOR_CLASS, authenticatorClass.getName());
            return this;
        }

        public Builder hotLoading(boolean hotLoading) {
            configurable.addConfig(ISecurityConfig.HOT_LOADING, String.valueOf(hotLoading));
            return this;
        }

        public Builder verifyTime(long verifyTime) {
            configurable.addConfig(ISecurityConfig.VERIFY_TIME, String.valueOf(verifyTime));
            return this;
        }

        public Builder secret(String secret) {
            configurable.addConfig(ISecurityConfig.SECRET, secret);
            return this;
        }

        public Builder headerName(String headerName) {
            configurable.addConfig(ISecurityConfig.HEADER_NAME, headerName);
            return this;
        }

        public Builder paramName(String paramName) {
            configurable.addConfig(ISecurityConfig.PARAM_NAME, paramName);
            return this;
        }

        public Builder headerClientName(String headerClientName) {
            configurable.addConfig(ISecurityConfig.HEADER_CLIENT_NAME, headerClientName);
            return this;
        }

        public Builder paramClientName(String paramClientName) {
            configurable.addConfig(ISecurityConfig.PARAM_CLIENT_NAME, paramClientName);
            return this;
        }

        public Builder autoResponse(boolean autoResponse) {
            configurable.addConfig(ISecurityConfig.AUTO_RESPONSE, String.valueOf(autoResponse));
            return this;
        }

        public Builder unpackFileLogin(boolean unpackFileLogin) {
            configurable.addConfig(ISecurityConfig.UNPACK_FILE_LOGIN, String.valueOf(unpackFileLogin));
            return this;
        }

        public IModuleConfigurer build() {
            return configurable.toModuleConfigurer();
        }
    }
}