package com.dpstudio.module.security.dao.impl;

import com.dpstudio.dev.dto.PageDTO;
import com.dpstudio.module.security.dao.ISecurityRoleDao;
import com.dpstudio.module.security.model.SecurityRole;
import net.ymate.platform.core.beans.annotation.Bean;
import net.ymate.platform.core.persistence.Fields;
import net.ymate.platform.core.persistence.IResultSet;
import net.ymate.platform.core.persistence.Page;
import net.ymate.platform.persistence.jdbc.JDBC;
import net.ymate.platform.persistence.jdbc.query.Cond;
import net.ymate.platform.persistence.jdbc.query.Where;

import java.util.List;

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
        return SecurityRole.builder().build().findFirst(Where.create(Cond.create().eqWrap(SecurityRole.FIELDS.NAME).param(name)
                .and().notEqWrap(SecurityRole.FIELDS.ID).param(id)));
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
    public int[] delete(String[] ids) throws Exception {
        return JDBC.get().openSession(session -> session.delete(SecurityRole.class,ids));
    }

    @Override
    public IResultSet<SecurityRole> findAll(String name,String clientName, PageDTO pageDTO) throws Exception {

        Cond cond = Cond.create().eqWrap(SecurityRole.FIELDS.CLIENT_NAME).param(clientName)
                .exprNotEmpty(name, c -> c.and().likeWrap(SecurityRole.FIELDS.NAME).param("%" + name + "%"));
        return SecurityRole.builder().build().find(Where.create(cond), pageDTO.toPage());
    }
}
