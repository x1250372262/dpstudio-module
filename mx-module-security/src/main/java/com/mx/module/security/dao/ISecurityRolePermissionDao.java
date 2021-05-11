package com.mx.module.security.dao;

import com.mx.module.security.model.SecurityRolePermission;
import net.ymate.platform.core.persistence.IResultSet;

import java.util.List;

/**
 * @Author: mengxiang.
 * @create: 2021-03-15 11:56
 * @Description:
 */
public interface ISecurityRolePermissionDao {

    /**
     * 根据roleId查询
     *
     * @param roleIdList
     * @param fields
     * @return
     * @throws Exception
     */
    IResultSet<SecurityRolePermission> findByRoleId(List<String> roleIdList, String... fields) throws Exception;

    /**
     * 根据roleid查询
     * @param roleId
     * @param fields
     * @return
     * @throws Exception
     */
    IResultSet<SecurityRolePermission> findByRoleId(String roleId, String... fields) throws Exception;

    /**
     * 删除
     * @param securityRolePermissions
     * @throws Exception
     */
    void delete(List<SecurityRolePermission> securityRolePermissions) throws Exception;

    void createAll(List<SecurityRolePermission> securityRolePermissions) throws Exception;
}
