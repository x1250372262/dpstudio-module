package com.mx.module.security.dao.impl;

import com.mx.dev.dto.PageDTO;
import com.mx.module.security.dao.ISecurityAdminRoleDao;
import com.mx.module.security.model.SecurityAdmin;
import com.mx.module.security.model.SecurityAdminRole;
import com.mx.module.security.model.SecurityRole;
import com.mx.module.security.vo.list.SecurityAdminRoleListVO;
import net.ymate.platform.core.beans.annotation.Bean;
import net.ymate.platform.core.persistence.Fields;
import net.ymate.platform.core.persistence.IResultSet;
import net.ymate.platform.core.persistence.Params;
import net.ymate.platform.persistence.jdbc.JDBC;
import net.ymate.platform.persistence.jdbc.base.impl.BeanResultSetHandler;
import net.ymate.platform.persistence.jdbc.query.*;

@Bean
public class SecurityAdminRoleDaoImpl implements ISecurityAdminRoleDao {

    @Override
    public IResultSet<SecurityAdminRoleListVO> findAll(String adminId, PageDTO pageDTO) throws Exception {

        Cond cond = Cond.create().eqWrap(Fields.field("sa", SecurityAdmin.FIELDS.ID)).param(adminId);

        return JDBC.get().openSession(session -> {
            String prefix = session.getConnectionHolder().getDataSourceConfig().getTablePrefix();

            Join adminRoleJoin = Join.inner(prefix, SecurityAdmin.TABLE_NAME).alias("sa")
                    .on(Cond.create().optWrap(Fields.field("sar", SecurityAdminRole.FIELDS.ADMIN_ID), Cond.OPT.EQ, Fields.field("sa", SecurityAdmin.FIELDS.ID)));

            Join roleJoin = Join.inner(prefix, SecurityRole.TABLE_NAME).alias("sr")
                    .on(Cond.create().optWrap(Fields.field("sr", SecurityRole.FIELDS.ID), Cond.OPT.EQ, Fields.field("sar", SecurityAdminRole.FIELDS.ROLE_ID)));

            Select select = Select.create(prefix, SecurityAdminRole.TABLE_NAME, "sar")
                    .join(adminRoleJoin).join(roleJoin)
                    .field("sar", SecurityAdminRole.FIELDS.ID)
                    .field("sar", SecurityAdminRole.FIELDS.CREATE_TIME)
                    .field("sar", SecurityAdminRole.FIELDS.ADMIN_ID)
                    .field("sar", SecurityAdminRole.FIELDS.ROLE_ID)
                    .field("sr", SecurityRole.FIELDS.NAME, "role_name")
                    .where(Where.create(cond).orderByDesc("sa", SecurityAdmin.FIELDS.CREATE_TIME));
            return session.find(SQL.create(select),
                    new BeanResultSetHandler<>(SecurityAdminRoleListVO.class), pageDTO.toPage());
        });
    }

    @Override
    public IResultSet<SecurityAdminRole> findAll(String adminId,String...fields) throws Exception {
        return SecurityAdminRole.builder().adminId(adminId).build().find(Fields.create(fields));
    }

    @Override
    public IResultSet<SecurityAdminRoleListVO> findByAdminIds(Params adminIds, PageDTO pageDTO) throws Exception {

        Cond cond = Cond.create().eqOne();
        if (adminIds.params().size() > 0) {
            cond.and().inWrap("sa", SecurityAdmin.FIELDS.ID, adminIds);
        }
        return JDBC.get().openSession(session -> {
            String prefix = session.getConnectionHolder().getDataSourceConfig().getTablePrefix();
            ;

            Join adminRoleJoin = Join.inner(prefix, SecurityAdmin.TABLE_NAME).alias("sa")
                    .on(Cond.create().optWrap(Fields.field("sar", SecurityAdminRole.FIELDS.ADMIN_ID), Cond.OPT.EQ, Fields.field("sa", SecurityAdmin.FIELDS.ID)));

            Join roleJoin = Join.inner(prefix, SecurityRole.TABLE_NAME).alias("sr")
                    .on(Cond.create().optWrap(Fields.field("sr", SecurityRole.FIELDS.ID), Cond.OPT.EQ, Fields.field("sar", SecurityAdminRole.FIELDS.ROLE_ID)));

            Select select = Select.create(prefix, SecurityAdminRole.TABLE_NAME, "sar")
                    .join(adminRoleJoin).join(roleJoin)
                    .field("sar", SecurityAdminRole.FIELDS.ID)
                    .field("sar", SecurityAdminRole.FIELDS.CREATE_TIME)
                    .field("sar", SecurityAdminRole.FIELDS.ADMIN_ID)
                    .field("sar", SecurityAdminRole.FIELDS.ROLE_ID)
                    .field("sr", SecurityRole.FIELDS.NAME, "role_name")
                    .where(Where.create(cond).orderByDesc("sa", SecurityAdmin.FIELDS.CREATE_TIME));
            return session.find(SQL.create(select),
                    new BeanResultSetHandler<>(SecurityAdminRoleListVO.class), pageDTO.toPage());
        });
    }

    @Override
    public SecurityAdminRole create(SecurityAdminRole securityAdminRole) throws Exception {
        return securityAdminRole.save();
    }

    @Override
    public int[] delete(String[] ids) throws Exception {
        return JDBC.get().openSession(session -> session.delete(SecurityAdminRole.class, ids));
    }

    @Override
    public SecurityAdminRole findByRoleIdAndAdminId(String roleId, String adminId, String... fields) throws Exception {
        return SecurityAdminRole.builder().roleId(roleId).adminId(adminId).build().findFirst(Fields.create(fields));
    }

}