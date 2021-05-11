package com.mx.dev.security;

import com.mx.dev.core.Constants;
import com.mx.module.security.SecurityCache;
import com.mx.module.security.dao.ISecurityAdminDao;
import com.mx.module.security.dao.ISecurityAdminRoleDao;
import com.mx.module.security.dao.ISecurityRolePermissionDao;
import com.mx.module.security.model.SecurityAdmin;
import com.mx.module.security.model.SecurityAdminRole;
import com.mx.module.security.model.SecurityRolePermission;
import net.ymate.platform.core.YMP;
import net.ymate.platform.core.persistence.IResultSet;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: mengxiang.
 * @Date: 2019-05-06.
 * @Time: 09:00.
 * @Description: 认证接口
 */
public interface IAuthenticator {

    /**
     * 返回当前用户是不是总管理员
     * 
     * @return 返回当前用户是不是总管理员
     * @throws Exception
     */
    boolean isFounder(String token) throws Exception;

    /**
     * 返回用户拥有的权限码
     * 
     * @return 返回用户拥有的权限码
     * @throws Exception
     */
    List<String> userPermissions(String token) throws Exception;

    class DefaultAuthenticator implements IAuthenticator {

        @Override
        public boolean isFounder(String token) throws Exception {
            String adminId;
            if(StringUtils.isNoneBlank(token)){
                adminId = SecurityCache.userId(token);
            }else{
                adminId = SecurityCache.userId();
            }
            if(StringUtils.isBlank(adminId)){
                return false;
            }
            SecurityAdmin securityAdmin = YMP.get().getBeanFactory().getBean(ISecurityAdminDao.class).findById(adminId,null);
            if(securityAdmin != null && Objects.equals(Constants.BOOL_TRUE,securityAdmin.getFounder())){
                return true;
            }
            return false;
        }

        @Override
        public List<String> userPermissions(String token) throws Exception {
            List<String> userPermissions = new ArrayList<>();
            String adminId;
            if(StringUtils.isNoneBlank(token)){
                adminId = SecurityCache.userId(token);
            }else{
                adminId = SecurityCache.userId();
            }
            IResultSet<SecurityAdminRole> securityAdminRoleResultSet = YMP.get().getBeanFactory().getBean(ISecurityAdminRoleDao.class).findAll(adminId,SecurityAdminRole.FIELDS.ROLE_ID);
            if(!securityAdminRoleResultSet.isResultsAvailable()){
                return userPermissions;
            }
            List<String> roleIdList = new ArrayList<>();
            securityAdminRoleResultSet.getResultData().forEach(securityAdminRole -> roleIdList.add(securityAdminRole.getRoleId()));
            IResultSet<SecurityRolePermission> securityRolePermissionResultSet  = YMP.get().getBeanFactory().getBean(ISecurityRolePermissionDao.class).findByRoleId(roleIdList,SecurityRolePermission.FIELDS.PERMISSION_CODE,SecurityRolePermission.FIELDS.PERMISSION_NAME);
            if(!securityRolePermissionResultSet.isResultsAvailable()){
                return userPermissions;
            }
            securityRolePermissionResultSet.getResultData().forEach(securityRolePermission -> userPermissions.add(securityRolePermission.getPermissionCode()));
            return userPermissions;
        }
    }
}
