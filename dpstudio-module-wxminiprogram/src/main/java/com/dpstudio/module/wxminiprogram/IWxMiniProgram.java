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
package com.dpstudio.module.wxminiprogram;

import com.dpstudio.module.wxminiprogram.bean.WxCodeSession;
import com.dpstudio.module.wxminiprogram.bean.WxPhoneInfo;
import com.dpstudio.module.wxminiprogram.bean.WxUserInfo;
import net.ymate.platform.core.IApplication;
import net.ymate.platform.core.beans.annotation.Ignored;
import net.ymate.platform.core.support.IDestroyable;
import net.ymate.platform.core.support.IInitialization;

/**
 * ISecurity generated By ModuleMojo on 2020/06/30 17:49
 *
 * @author YMP (https://www.ymate.net/)
 */
@Ignored
public interface IWxMiniProgram extends IInitialization<IApplication>, IDestroyable {


    String MODULE_NAME = "dpstudio.wxminiprogram";

    /**
     * 获取所属应用容器
     *
     * @return 返回所属应用容器实例
     */
    IApplication getOwner();

    /**
     * 获取配置
     *
     * @return 返回配置对象
     */
    IWxMiniProgramConfig getConfig();


    /**
     * 获取session信息
     *
     * @param jsCode
     * @return
     * @throws Exception
     */
    WxCodeSession getSessionInfo(String jsCode) throws Exception;

    /**
     * 获取用户信息
     *
     * @param sessionKey
     * @param encryptedData 用户敏感信息
     * @param ivStr         解密算法的向量
     * @return
     */
    WxUserInfo getUserInfo(String sessionKey, String encryptedData, String ivStr) throws Exception;


    /**
     * 检查用户信息
     *
     * @param sessionKey
     * @param rawData    用户非敏感信息
     * @param signature  签名
     * @return
     */
    boolean checkUserInfo(String sessionKey, String rawData, String signature) throws Exception;

    /**
     * 获取手机信息
     *
     * @param sessionKey
     * @param encryptedData 用户敏感信息
     * @param ivStr         解密算法的向量
     * @return
     */
    WxPhoneInfo getPhoneNoInfo(String sessionKey, String encryptedData, String ivStr) throws Exception;

    /**
     * 获取数据处理信息
     *
     * @return
     * @throws Exception
     */
    IWxMimiProgramHandler getHandler() throws Exception;

    /**
     * api接口
     */
    interface WX_API {
        /**
         * code获取session信息
         */
        String JSCODE_TO_SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session";
    }
}
