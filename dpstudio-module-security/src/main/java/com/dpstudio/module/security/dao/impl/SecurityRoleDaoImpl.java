package com.dpstudio.module.security.dao.impl;

import com.dpstudio.module.security.model.SecurityRole;
import com.dpstudio.module.security.dao.ISecurityRoleDao;
import net.ymate.platform.core.beans.annotation.Bean;
import net.ymate.platform.persistence.Fields;
import net.ymate.platform.persistence.IResultSet;
import net.ymate.platform.persistence.Page;
import net.ymate.platform.persistence.jdbc.ISessionExecutor;
import net.ymate.platform.persistence.jdbc.JDBC;
import net.ymate.platform.persistence.jdbc.query.Cond;
import net.ymate.platform.persistence.jdbc.query.Where;

import java.util.List;

/**
 * @Author: 刘玉奇.
 * @Date: 2020/10/15.
 * @Time: 15:43.
 * @Description: 角色管理
 */
@Bean
public class SecurityRoleDaoImpl implements ISecurityRoleDao {

    @Override
    public SecurityRole findById(String id, String... fields) throws Exception {
        return SecurityRole.builder().id(id).build().load(Fields.create(fields));
    }

    @Override
    public SecurityRole findByName(String name, String... fields) throws Exception {
        return SecurityRole.builder().name(name).build().findFirst(Fields.create(fields));
    }

    @Override
    public SecurityRole findByNameNotId(String name, String id, String... fields) throws Exception {
        return SecurityRole.builder().build().findFirst(Where.create(Cond.create().eq(SecurityRole.FIELDS.NAME).param(name)
                .and().notEq(SecurityRole.FIELDS.ID).param(id)));
    }

    @Override
    public SecurityRole create(SecurityRole securityRole) throws Exception {
        return securityRole.save();
    }

    @Override
    public SecurityRole update(SecurityRole securityRole, String... fields) throws Exception {
        return securityRole.update(Fields.create(fields));
    }

    @Override
    public void delete(List<SecurityRole> list) throws Exception {
        JDBC.get().openSession((ISessionExecutor<Object>) session -> session.delete(list));
    }

    @Override
    public IResultSet<SecurityRole> findAll(String name, int page, int pageSize) throws Exception {

        Cond cond = Cond.create().eqOne();
        cond.exprNotEmpty(name, Cond.create().and().like(SecurityRole.FIELDS.NAME).param("%" + name + "%"));
        if (page > 0 && pageSize > 0) {
            SecurityRole.builder().build().find(Where.create(cond), Page.create(page).pageSize(pageSize));
        }
        return SecurityRole.builder().build().find(Where.create(cond));
    }
}
