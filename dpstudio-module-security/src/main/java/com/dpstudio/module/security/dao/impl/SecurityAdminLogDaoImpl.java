package com.dpstudio.module.security.dao.impl;

import com.dpstudio.dev.dto.PageDTO;
import com.dpstudio.module.security.dao.ISecurityAdminLogDao;
import com.dpstudio.module.security.model.SecurityAdminLog;
import net.ymate.platform.core.beans.annotation.Bean;
import net.ymate.platform.core.persistence.IResultSet;
import net.ymate.platform.core.persistence.Page;
import net.ymate.platform.persistence.jdbc.JDBC;
import net.ymate.platform.persistence.jdbc.query.Cond;
import net.ymate.platform.persistence.jdbc.query.Where;

import java.util.List;

@Bean
public class SecurityAdminLogDaoImpl implements ISecurityAdminLogDao {


    @Override
    public IResultSet<SecurityAdminLog> findAll(String adminId,String clientName, String content, Long startTime, Long endTime, PageDTO pageDTO) throws Exception {

        Cond cond = Cond.create().eqWrap(SecurityAdminLog.FIELDS.CLIENT_NAME).param(clientName)
                .exprNotEmpty(adminId, c -> c.and().eqWrap(SecurityAdminLog.FIELDS.ADMIN_ID).param(adminId))
                .exprNotEmpty(content, c -> c.and().likeWrap(SecurityAdminLog.FIELDS.CONTENT).param("%" + content + "%"))
                .exprNotEmpty(startTime, c -> c.and().gtEqWrap(SecurityAdminLog.FIELDS.CREATE_TIME).param(startTime))
                .exprNotEmpty(endTime, c -> c.and().ltEqWrap(SecurityAdminLog.FIELDS.CREATE_TIME).param(endTime));
        return SecurityAdminLog.builder().build().find(Where.create(cond).orderByDesc(SecurityAdminLog.FIELDS.CREATE_TIME), pageDTO.toPage());
    }

    @Override
    public SecurityAdminLog create(SecurityAdminLog securityAdminLog) throws Exception {
        return securityAdminLog.save();
    }

    @Override
    public int[] delete(String[] ids) throws Exception {
        return JDBC.get().openSession(session -> session.delete(SecurityAdminLog.class, ids));
    }
}
