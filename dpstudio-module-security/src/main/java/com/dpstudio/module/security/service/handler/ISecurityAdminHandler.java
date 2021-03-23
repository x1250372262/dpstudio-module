package com.dpstudio.module.security.service.handler;

import com.dpstudio.dev.core.R;
import com.dpstudio.dev.support.spi.annotation.SpiBean;
import com.dpstudio.module.security.dto.SecurityAdminDTO;
import com.dpstudio.module.security.model.SecurityAdmin;

/**
 * @Author: mengxiang.
 * @create: 2021-02-09 20:58
 * @Description:
 */
public interface ISecurityAdminHandler {

    /**
     * 登录之前
     * @param userName
     * @param password
     * @return
     * @throws Exception
     */
    R loginBefore(String userName, String password) throws Exception;

    /**
     * 登录之后
     * @param securityAdmin
     * @return
     * @throws Exception
     */
    R loginAfter(SecurityAdmin securityAdmin) throws Exception;

    /**
     * 添加之前
     * @param securityAdminDTO
     * @param password
     * @return
     * @throws Exception
     */
    R createBefore(SecurityAdminDTO securityAdminDTO, String password) throws Exception;


    /**
     * 添加之后
     * @param securityAdmin
     * @return
     * @throws Exception
     */
    R createAfter(SecurityAdmin securityAdmin) throws Exception;

    @SpiBean
     class SecurityAdminHandler implements ISecurityAdminHandler{

         @Override
         public R loginBefore(String userName, String password) throws Exception {
             return R.ok();
         }

         @Override
         public R loginAfter(SecurityAdmin securityAdmin) throws Exception {
             return R.ok();
         }

         @Override
         public R createBefore(SecurityAdminDTO securityAdminDTO, String password) throws Exception {
             return R.ok();
         }

         @Override
         public R createAfter(SecurityAdmin securityAdmin) throws Exception {
             return R.ok();
         }
     }
}
