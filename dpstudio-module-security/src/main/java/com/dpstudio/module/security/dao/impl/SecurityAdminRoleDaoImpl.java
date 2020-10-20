package com.dpstudio.module.security.dao.impl;

import com.dpstudio.module.security.model.SecurityAdmin;
import com.dpstudio.module.security.model.SecurityAdminRole;
import com.dpstudio.module.security.model.SecurityRole;
import com.dpstudio.module.security.dao.ISecurityAdminRoleDao;
import com.dpstudio.module.security.vo.SecurityAdminRoleListVO;
import net.ymate.platform.core.beans.annotation.Bean;
import net.ymate.platform.persistence.Fields;
import net.ymate.platform.persistence.IResultSet;
import net.ymate.platform.persistence.Page;
import net.ymate.platform.persistence.Params;
import net.ymate.platform.persistence.jdbc.ISessionExecutor;
import net.ymate.platform.persistence.jdbc.JDBC;
import net.ymate.platform.persistence.jdbc.base.impl.BeanResultSetHandler;
import net.ymate.platform.persistence.jdbc.query.*;

import java.util.List;

/**
 * @Author: 刘玉奇.
 * @Date: 2020/10/15.
 * @Time: 22:29.
 * @Description:
 */
@Bean
public class SecurityAdminRoleDaoImpl implements ISecurityAdminRoleDao {

    @Override
    public IResultSet<SecurityAdminRoleListVO> findAll(String adminId, int page, int pageSize) throws Exception {

        Cond cond = Cond.create().eq("sa", SecurityAdmin.FIELDS.ID).param(adminId);

        return JDBC.get().openSession(session -> {
            String prefix = session.getConnectionHolder().getDataSourceCfgMeta().getTablePrefix();

            Join adminRoleJoin = Join.inner(prefix, SecurityAdmin.TABLE_NAME).alias("sa")
                    .on(Cond.create().opt("sar", SecurityAdminRole.FIELDS.ADMIN_ID, Cond.OPT.EQ, "sa", SecurityAdmin.FIELDS.ID));

            Join roleJoin = Join.inner(prefix, SecurityRole.TABLE_NAME).alias("sr")
                    .on(Cond.create().opt("sr", SecurityRole.FIELDS.ID, Cond.OPT.EQ, "sar", SecurityAdminRole.FIELDS.ROLE_ID));

            Select select = Select.create(prefix, SecurityAdminRole.TABLE_NAME, "sar")
                    .join(adminRoleJoin).join(roleJoin)
                    .field("sar", SecurityAdminRole.FIELDS.ID)
                    .field("sar", SecurityAdminRole.FIELDS.CREATE_TIME)
                    .field("sar", SecurityAdminRole.FIELDS.ADMIN_ID)
                    .field("sar", SecurityAdminRole.FIELDS.ROLE_ID)
                    .field("sr", SecurityRole.FIELDS.NAME, "role_name")
                    .where(Where.create(cond).orderDesc("sa", SecurityAdmin.FIELDS.CREATE_TIME));
            return session.find(SQL.create(select),
                    new BeanResultSetHandler<>(SecurityAdminRoleListVO.class), Page.create(page).pageSize(pageSize));
        });
    }

    @Override
    public IResultSet<SecurityAdminRoleListVO> findByAdminIds(Params adminIds, int page, int pageSize) throws Exception {

        Cond cond = Cond.create().eqOne();
        if (adminIds.params().size() > 0) {
            cond.and().in("sa", SecurityAdmin.FIELDS.ID, adminIds);
        }
        return JDBC.get().openSession(session -> {
            String prefix = session.getConnectionHolder().getDataSourceCfgMeta().getTablePrefix();

            Join adminRoleJoin = Join.inner(prefix, SecurityAdmin.TABLE_NAME).alias("sa")
                    .on(Cond.create().opt("sar", SecurityAdminRole.FIELDS.ADMIN_ID, Cond.OPT.EQ, "sa", SecurityAdmin.FIELDS.ID));

            Join roleJoin = Join.inner(prefix, SecurityRole.TABLE_NAME).alias("sr")
                    .on(Cond.create().opt("sr", SecurityRole.FIELDS.ID, Cond.OPT.EQ, "sar", SecurityAdminRole.FIELDS.ROLE_ID));

            Select select = Select.create(prefix, SecurityAdminRole.TABLE_NAME, "sar")
                    .join(adminRoleJoin).join(roleJoin)
                    .field("sar", SecurityAdminRole.FIELDS.ID)
                    .field("sar", SecurityAdminRole.FIELDS.CREATE_TIME)
                    .field("sar", SecurityAdminRole.FIELDS.ADMIN_ID)
                    .field("sar", SecurityAdminRole.FIELDS.ROLE_ID)
                    .field("sr", SecurityRole.FIELDS.NAME, "role_name")
                    .where(Where.create(cond).orderDesc("sa", SecurityAdmin.FIELDS.CREATE_TIME));
            return session.find(SQL.create(select),
                    new BeanResultSetHandler<>(SecurityAdminRoleListVO.class), Page.create(page).pageSize(pageSize));
        });
    }

    @Override
    public SecurityAdminRole create(SecurityAdminRole securityAdminRole) throws Exception {
        return securityAdminRole.save();
    }

    @Override
    public void delete(List<SecurityAdminRole> list) throws Exception {
        JDBC.get().openSession((ISessionExecutor<Object>) session -> session.delete(list));
    }

    @Override
    public SecurityAdminRole findByRoleIdAndAdminId(String roleId, String adminId, String... fields) throws Exception {
        return SecurityAdminRole.builder().roleId(roleId).adminId(adminId).build().findFirst(Fields.create(fields));
    }

}
