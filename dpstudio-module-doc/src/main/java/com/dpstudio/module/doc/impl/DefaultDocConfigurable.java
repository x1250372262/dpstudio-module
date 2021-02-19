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
package com.dpstudio.module.doc.impl;

import com.dpstudio.module.doc.IDoc;
import com.dpstudio.module.doc.IDocConfig;
import net.ymate.platform.core.module.IModuleConfigurer;
import net.ymate.platform.core.module.impl.DefaultModuleConfigurable;

/**
 * DefaultDocConfig generated By ModuleMojo on 2020/07/07 15:44
 *
 * @author YMP (https://www.ymate.net/)
 */
public final class DefaultDocConfigurable extends DefaultModuleConfigurable {

    public static Builder builder() {
        return new Builder();
    }

    private DefaultDocConfigurable() {
        super(IDoc.MODULE_NAME);
    }

    public static final class Builder {

        private final DefaultDocConfigurable configurable = new DefaultDocConfigurable();

        private Builder() {
        }

        public Builder enabled(boolean enabled) {
            configurable.addConfig(IDocConfig.ENABLED, String.valueOf(enabled));
            return this;
        }

        public Builder mockEnabled(boolean mockEnabled) {
            configurable.addConfig(IDocConfig.MOCK_ENABLED, String.valueOf(mockEnabled));
            return this;
        }

        public Builder sdkEnabled(boolean sdkEnabled) {
            configurable.addConfig(IDocConfig.SDK_ENABLED, String.valueOf(sdkEnabled));
            return this;
        }

        public Builder title(String title) {
            configurable.addConfig(IDocConfig.TITLE, title);
            return this;
        }

        public Builder sourcePath(String sourcePath) {
            configurable.addConfig(IDocConfig.SOURCE_PATH, sourcePath);
            return this;
        }

        public Builder fileName(String fileName) {
            configurable.addConfig(IDocConfig.FILE_NAME, fileName);
            return this;
        }

        public Builder version(String version) {
            configurable.addConfig(IDocConfig.VERSION, version);
            return this;
        }

        public Builder host(String host) {
            configurable.addConfig(IDocConfig.HOST, host);
            return this;
        }

        public IModuleConfigurer build() {
            return configurable.toModuleConfigurer();
        }
    }
}