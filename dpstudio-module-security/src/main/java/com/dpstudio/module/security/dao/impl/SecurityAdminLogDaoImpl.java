package com.dpstudio.module.security.dao.impl;

import com.dpstudio.module.security.model.SecurityAdminLog;
import com.dpstudio.module.security.dao.ISecurityAdminLogDao;
import net.ymate.platform.core.beans.annotation.Bean;
import net.ymate.platform.persistence.IResultSet;
import net.ymate.platform.persistence.Page;
import net.ymate.platform.persistence.jdbc.ISessionExecutor;
import net.ymate.platform.persistence.jdbc.JDBC;
import net.ymate.platform.persistence.jdbc.query.Cond;
import net.ymate.platform.persistence.jdbc.query.Where;

import java.util.List;

/**
 * @Author: 刘玉奇.
 * @Date: 2020/10/16.
 * @Time: 22:02.
 * @Description:
 */
@Bean
public class SecurityAdminLogDaoImpl implements ISecurityAdminLogDao {


    @Override
    public IResultSet<SecurityAdminLog> findAll(String adminId, String content, Long startTime, Long endTime, int page, int pageSize) throws Exception {

        Cond cond = Cond.create().eqOne();
        cond.exprNotEmpty(adminId, Cond.create().and().eq(SecurityAdminLog.FIELDS.ADMIN_ID).param(adminId));
        cond.exprNotEmpty(content, Cond.create().and().like(SecurityAdminLog.FIELDS.CONTENT).param("%" + content + "%"));
        cond.exprNotEmpty(startTime, Cond.create().and().gtEq(SecurityAdminLog.FIELDS.CREATE_TIME).param(startTime));
        cond.exprNotEmpty(endTime, Cond.create().and().ltEq(SecurityAdminLog.FIELDS.CREATE_TIME).param(endTime));
        if (page > 0 && pageSize > 0) {
            SecurityAdminLog.builder().build().find(Where.create(cond).orderDesc(SecurityAdminLog.FIELDS.CREATE_TIME), Page.create(page).pageSize(pageSize));
        }
        return SecurityAdminLog.builder().build().find(Where.create(cond).orderDesc(SecurityAdminLog.FIELDS.CREATE_TIME));
    }

    @Override
    public SecurityAdminLog create(SecurityAdminLog securityAdminLog) throws Exception {
        return securityAdminLog.save();
    }

    @Override
    public void delete(List<SecurityAdminLog> list) throws Exception {
        JDBC.get().openSession((ISessionExecutor<Object>) session -> session.delete(list));
    }
}
