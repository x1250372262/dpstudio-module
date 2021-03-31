package com.dpstudio.module.wxminiprogram.service.impl;

import com.dpstudio.dev.core.R;
import com.dpstudio.module.wxminiprogram.ErrorCode;
import com.dpstudio.module.wxminiprogram.IWxMiniProgram;
import com.dpstudio.module.wxminiprogram.WxMiniProgram;
import com.dpstudio.module.wxminiprogram.bean.WxCodeSession;
import com.dpstudio.module.wxminiprogram.bean.WxPhoneInfo;
import com.dpstudio.module.wxminiprogram.bean.WxUserInfo;
import com.dpstudio.module.wxminiprogram.dto.MobileDTO;
import com.dpstudio.module.wxminiprogram.dto.UserDTO;
import com.dpstudio.module.wxminiprogram.service.IWxMimiProgramUserService;
import net.ymate.platform.core.beans.annotation.Bean;

/**
 * @Author: mengxiang.
 * @Date: 2020/4/2.
 * @Time: 4:51 下午.
 * @Description:
 */
@Bean
public class IWxMimiProgramUserServiceImpl implements IWxMimiProgramUserService {

    @Override
    public R getSession(String code) throws Exception {
        IWxMiniProgram iWxMimiProgram = WxMiniProgram.get();
        if (iWxMimiProgram == null) {
            return R.create(ErrorCode.INIT_ERROR.getCode()).msg(ErrorCode.INIT_ERROR.getMsg());
        }
        WxCodeSession wxCodeSession = iWxMimiProgram.getSessionInfo(code);
        if (wxCodeSession == null) {
            return R.create(ErrorCode.SESSION_ERROR.getCode()).msg(ErrorCode.SESSION_ERROR.getMsg());
        }
        return R.ok().data(wxCodeSession);
    }

    @Override
    public R userInfo(UserDTO userDTO) throws Exception {

        IWxMiniProgram iWxMimiProgram = WxMiniProgram.get();
        if (iWxMimiProgram == null) {
            return R.create(ErrorCode.INIT_ERROR.getCode()).msg(ErrorCode.INIT_ERROR.getMsg());
        }
        // 用户信息校验
        if (!iWxMimiProgram.checkUserInfo(userDTO.getSessionKey(), userDTO.getRawData(), userDTO.getSignature())) {
            return R.create(ErrorCode.CHECK_USER_INFO_ERROR.getCode()).msg(ErrorCode.CHECK_USER_INFO_ERROR.getMsg());
        }
        WxUserInfo wxUserInfo = iWxMimiProgram.getUserInfo(userDTO.getSessionKey(), userDTO.getEncrypteData(), userDTO.getIv());
        if (wxUserInfo == null) {
            return R.create(ErrorCode.USER_INFO_ERROR.getCode()).msg(ErrorCode.USER_INFO_ERROR.getMsg());
        }

        R r = iWxMimiProgram.getHandler().handlerUserData(wxUserInfo, userDTO.getAttach());
        return R.create(r.code()).msg(r.msg()).attrs(r.attrs());

    }

    @Override
    public R mobileInfo(MobileDTO mobileDTO) throws Exception {
        IWxMiniProgram iWxMimiProgram = WxMiniProgram.get();
        if (iWxMimiProgram == null) {
            return R.create(ErrorCode.INIT_ERROR.getCode()).msg(ErrorCode.INIT_ERROR.getMsg());
        }
        WxPhoneInfo wxPhoneInfo = iWxMimiProgram.getPhoneNoInfo(mobileDTO.getSessionKey(), mobileDTO.getEncrypteData(), mobileDTO.getIv());
        if (wxPhoneInfo == null) {
            return R.create(ErrorCode.USER_INFO_ERROR.getCode()).msg(ErrorCode.USER_INFO_ERROR.getMsg());
        }
        R r = iWxMimiProgram.getHandler().handlerMobileData(mobileDTO.getAttach(), mobileDTO.getToken(), wxPhoneInfo);
        return R.create(r.code()).msg(r.msg()).attrs(r.attrs());
    }
}
