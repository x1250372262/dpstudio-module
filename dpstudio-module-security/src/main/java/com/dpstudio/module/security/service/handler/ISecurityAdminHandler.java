package com.dpstudio.module.security.service.handler;

import com.dpstudio.dev.core.R;
import com.dpstudio.module.security.model.SecurityAdmin;

/**
 * @Author: 徐建鹏.
 * @create: 2021-02-09 20:58
 * @Description:
 */
public interface ISecurityAdminHandler {

    R loginBefore(String userName, String password) throws Exception;

    R loginAfter(SecurityAdmin securityAdmin) throws Exception;

     class SecurityAdminHandler implements ISecurityAdminHandler{

         @Override
         public R loginBefore(String userName, String password) throws Exception {
             return R.ok();
         }

         @Override
         public R loginAfter(SecurityAdmin securityAdmin) throws Exception {
             return R.ok();
         }
     }
}
