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

import net.ymate.platform.core.beans.annotation.Ignored;
import net.ymate.platform.core.support.IInitialization;

/**
 * ISecurityConfig generated By ModuleMojo on 2020/06/30 17:49
 *
 * @author YMP (https://www.ymate.net/)
 */
@Ignored
public interface ISecurityConfig extends IInitialization<ISecurity> {

    String ENABLED = "enabled";
    String HOT_LOADING = "hot_loading";
    String PACKAGE_NAME = "package_name";
    String AUTHENTICATOR_CLASS = "authenticator_class";
    String MENU_FILE_PATH = "menu_file_path";
    String VERIFY_TIME = "jwt_verify_time";
    String SECRET = "jwt_secret";
    String HEADER_NAME = "jwt_header_name";
    String PARAM_NAME = "jwt_param_name";
    String AUTO_RESPONSE = "jwt_auto_response";

    /**
     * 模块是否已启用, 默认值: true
     *
     * @return 返回false表示禁用
     */
    boolean isEnabled();

    /**
     * 权限包名
     *
     * @return 返回权限包名
     */
    String packageName();

    /**
     * 菜单文件位置
     *
     * @return 返回菜单文件位置
     */
    String menuFilePath();

    /**
     * 授权实现类
     *
     * @return 返回授权实现类
     */
    IAuthenticator authenticatorClass();

    /**
     * 菜单文件是否支持热加载 正式环境中不建议使用 默认false
     * @return 返回false标识不开启
     */
    boolean isHotLoading();

    /**
     * 有效期，单位豪秒， 默认0  永久有效
     * @return 有效期
     */
    int verifyTime();

    /**
     * 密钥
     * @return 密钥
     */
    String secret();

    /**
     * header参数名
     * @return header参数名
     */
    String headerName();

    /**
     * 参数名
     * @return 参数名
     */
    String paramName();

    /**
     * 是否自动设置到response
     * @return 是否自动设置到response
     */
    boolean autoResponse();

}