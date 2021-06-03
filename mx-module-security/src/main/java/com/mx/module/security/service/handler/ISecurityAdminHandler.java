package com.mx.module.security.service.handler;

import com.mx.dev.core.R;
import com.mx.dev.support.spi.annotation.SpiBean;
import com.mx.module.security.dao.ISecurityAdminDao;
import com.mx.module.security.dto.SecurityAdminDTO;
import com.mx.module.security.model.SecurityAdmin;
import net.ymate.platform.core.YMP;

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
    R loginBefore(String userName, String password, String clientName) throws Exception;

    /**
     * 登录之后
     * @param securityAdmin
     * @param params
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
         public R loginBefore(String userName, String password, String clientName) throws Exception {
             SecurityAdmin securityAdmin = YMP.get().getBeanFactory().getBean(ISecurityAdminDao.class).findByUserNameAndClientName(userName, clientName, SecurityAdmin.FIELDS.ID,
                     SecurityAdmin.FIELDS.GENDER, SecurityAdmin.FIELDS.PHOTO_URI, SecurityAdmin.FIELDS.PASSWORD,
                     SecurityAdmin.FIELDS.USER_NAME, SecurityAdmin.FIELDS.SALT, SecurityAdmin.FIELDS.CLIENT_NAME,
                     SecurityAdmin.FIELDS.REAL_NAME, SecurityAdmin.FIELDS.LOGIN_ERROR_COUNT,
                     SecurityAdmin.FIELDS.FOUNDER, SecurityAdmin.FIELDS.DISABLE_STATUS,
                     SecurityAdmin.FIELDS.LOGIN_LOCK_END_TIME, SecurityAdmin.FIELDS.SESSION_TOKEN);
             return R.ok().attr("securityAdmin",securityAdmin);
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
