package com.mx.module.wxminiprogram.service;


import com.mx.dev.core.R;
import com.mx.module.wxminiprogram.dto.MobileDTO;
import com.mx.module.wxminiprogram.dto.UserDTO;
import com.mx.module.wxminiprogram.dto.UserInfoDTO;

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
     * 修改用户信息
     *
     * @param userInfoDTO
     * @return
     * @throws Exception
     */
    R update(UserInfoDTO userInfoDTO) throws Exception;

    /**
     * 手机号信息
     *
     * @param mobileDTO
     * @return
     * @throws Exception
     */
    R mobileInfo(MobileDTO mobileDTO) throws Exception;
}
