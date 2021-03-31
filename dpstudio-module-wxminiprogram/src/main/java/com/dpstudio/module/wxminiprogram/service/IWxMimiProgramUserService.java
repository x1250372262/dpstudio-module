package com.dpstudio.module.wxminiprogram.service;


import com.dpstudio.dev.core.R;
import com.dpstudio.module.wxminiprogram.dto.MobileDTO;
import com.dpstudio.module.wxminiprogram.dto.UserDTO;

/**
 * @Author: mengxiang.
 * @Date: 2020/4/2.
 * @Time: 4:51 下午.
 * @Description:
 */
public interface IWxMimiProgramUserService {

    /**
     * 获取session
     *
     * @param code
     * @return
     * @throws Exception
     */
    R getSession(String code) throws Exception;

    /**
     * 用户信息
     *
     * @param userDTO
     * @return
     * @throws Exception
     */
    R userInfo(UserDTO userDTO) throws Exception;

    /**
     * 手机号信息
     *
     * @param mobileDTO
     * @return
     * @throws Exception
     */
    R mobileInfo(MobileDTO mobileDTO) throws Exception;
}
