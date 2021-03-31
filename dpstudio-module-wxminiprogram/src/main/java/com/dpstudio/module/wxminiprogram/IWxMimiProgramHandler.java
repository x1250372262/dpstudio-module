package com.dpstudio.module.wxminiprogram;

import com.dpstudio.dev.core.R;
import com.dpstudio.module.wxminiprogram.bean.WxPhoneInfo;
import com.dpstudio.module.wxminiprogram.bean.WxUserInfo;

/**
 * @Author: mengxiang.
 * @Date: 2020/4/2.
 * @Time: 5:31 下午.
 * @Description: 微信小程序数据处理
 */
public interface IWxMimiProgramHandler {

    /**
     * 处理用户信息
     *
     * @param wxUserInfo
     * @return
     * @throws Exception
     */
    R handlerUserData(WxUserInfo wxUserInfo, String attach) throws Exception;

    /**
     * 处理手机号信息
     *
     * @param token
     * @param wxPhoneInfo
     * @return
     * @throws Exception
     */
    R handlerMobileData(String attach, String token, WxPhoneInfo wxPhoneInfo) throws Exception;
}
