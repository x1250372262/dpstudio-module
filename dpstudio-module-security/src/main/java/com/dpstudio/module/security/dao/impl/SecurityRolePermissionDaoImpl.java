package com.dpstudio.module.security.dao.impl;

import com.dpstudio.module.security.dao.ISecurityRolePermissionDao;
import com.dpstudio.module.security.model.SecurityRolePermission;
import net.ymate.platform.core.beans.annotation.Bean;
import net.ymate.platform.core.persistence.Fields;
import net.ymate.platform.core.persistence.IResultSet;
import net.ymate.platform.core.persistence.Params;
import net.ymate.platform.persistence.jdbc.IDatabaseSession;
import net.ymate.platform.persistence.jdbc.IDatabaseSessionExecutor;
import net.ymate.platform.persistence.jdbc.JDBC;
import net.ymate.platform.persistence.jdbc.query.Cond;
import net.ymate.platform.persistence.jdbc.query.Where;

import java.util.List;

/**
 * @Author: mengxiang.
 * @create: 2021-03-15 11:57
 * @Description:
 */
@Bean
public class SecurityRolePermissionDaoImpl implements ISecurityRolePermissionDao {
    @Override
    public IResultSet<SecurityRolePermission> findByRoleId(List<String> roleIdList, String... fields) throws Exception {
        return SecurityRolePermission.builder().build().find(Where.create(Cond.create().inWrap(SecurityRolePermission.FIELDS.ROLE_ID, Params.create(roleIdList))),Fields.create(fields));
    }

    @Override
    public IResultSet<SecurityRolePermission> findByRoleId(String roleId, String... fields) throws Exception {
        return SecurityRolePermission.builder().roleId(roleId).build().find(Fields.create(fields));
    }

    @Override
    public void delete(List<SecurityRolePermission> securityRolePermissions) throws Exception {
        JDBC.get().openSession((IDatabaseSessionExecutor<Object>) session -> session.delete(securityRolePermissions));
    }

    @Override
    public void createAll(List<SecurityRolePermission> securityRolePermissions) throws Exception {
        JDBC.get().openSession((IDatabaseSessionExecutor<Object>) session -> session.insert(securityRolePermissions));
    }
}
