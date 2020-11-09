package com.dpstudio.module.security.dao.impl;

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
    public IResultSet<SecurityAdminLog> findAll(String adminId, String content, Long startTime, Long endTime, Integer page, Integer pageSize) throws Exception {

        Cond cond = Cond.create().eqOne()
                .exprNotEmpty(adminId, c -> c.and().eq(SecurityAdminLog.FIELDS.ADMIN_ID).param(adminId))
                .exprNotEmpty(content, c -> c.and().like(SecurityAdminLog.FIELDS.CONTENT).param("%" + content + "%"))
                .exprNotEmpty(startTime, c -> c.and().gtEq(SecurityAdminLog.FIELDS.CREATE_TIME).param(startTime))
                .exprNotEmpty(endTime, c -> c.and().ltEq(SecurityAdminLog.FIELDS.CREATE_TIME).param(endTime));
        return SecurityAdminLog.builder().build().find(Where.create(cond).orderByDesc(SecurityAdminLog.FIELDS.CREATE_TIME), Page.createIfNeed(page, pageSize));
    }

    @Override
    public SecurityAdminLog create(SecurityAdminLog securityAdminLog) throws Exception {
        return securityAdminLog.save();
    }

    @Override
    public void delete(List<SecurityAdminLog> list) throws Exception {
        JDBC.get().openSession(session -> session.delete(list));
    }
}
