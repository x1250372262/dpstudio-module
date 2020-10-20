package com.dpstudio.module.security.dao.impl;

import com.dpstudio.module.security.core.SecurityConstants;
import com.dpstudio.module.security.dao.ISecurityAdminDao;
import com.dpstudio.module.security.model.SecurityAdmin;
import com.dpstudio.module.security.vo.SecurityAdminListVO;
import net.ymate.platform.core.beans.annotation.Bean;
import net.ymate.platform.persistence.Fields;
import net.ymate.platform.persistence.IResultSet;
import net.ymate.platform.persistence.Page;
import net.ymate.platform.persistence.jdbc.JDBC;
import net.ymate.platform.persistence.jdbc.base.impl.BeanResultSetHandler;
import net.ymate.platform.persistence.jdbc.query.Cond;
import net.ymate.platform.persistence.jdbc.query.SQL;
import net.ymate.platform.persistence.jdbc.query.Select;
import net.ymate.platform.persistence.jdbc.query.Where;

@Bean
public class SecurityAdminDaoImpl implements ISecurityAdminDao {

    @Override
    public SecurityAdmin findById(String id, String... fields) throws Exception {
        return SecurityAdmin.builder().id(id).build().load(Fields.create(fields));
    }

    @Override
    public SecurityAdmin findByUserName(String userName, String... fields) throws Exception {
        return SecurityAdmin.builder().userName(userName).deleteStatus(SecurityConstants.BOOL_FALSE).build().findFirst(Fields.create(fields));
    }

    @Override
    public SecurityAdmin update(SecurityAdmin securityAdmin, String... fields) throws Exception {
        return securityAdmin.update(Fields.create(fields));
    }

    @Override
    public IResultSet<SecurityAdminListVO> list(String userName, String realName, Integer disableStatus, int page, int pageSize) throws Exception {
        Cond cond = Cond.create().eq("sa", SecurityAdmin.FIELDS.FOUNDER).param(SecurityConstants.BOOL_FALSE)
                .and().eq("sa", SecurityAdmin.FIELDS.DELETE_STATUS).param(SecurityConstants.BOOL_FALSE);
        cond.exprNotEmpty(userName, Cond.create().and().like("sa", SecurityAdmin.FIELDS.USER_NAME).param("%" + userName + "%"));
        cond.exprNotEmpty(realName, Cond.create().and().like("sa", SecurityAdmin.FIELDS.REAL_NAME).param("%" + realName + "%"));
        cond.exprNotEmpty(disableStatus, Cond.create().and().eq("sa", SecurityAdmin.FIELDS.DISABLE_STATUS).param(disableStatus));
        return JDBC.get().openSession(session -> {
            String prefix = session.getConnectionHolder().getDataSourceCfgMeta().getTablePrefix();

            Select select = Select.create(prefix, SecurityAdmin.TABLE_NAME, "sa")
                    .field("sa", SecurityAdmin.FIELDS.ID)
                    .field("sa", SecurityAdmin.FIELDS.CREATE_TIME)
                    .field("sa", SecurityAdmin.FIELDS.DISABLE_STATUS)
                    .field("sa", SecurityAdmin.FIELDS.LOGIN_LOCK_STATUS)
                    .field("sa", SecurityAdmin.FIELDS.USER_NAME)
                    .field("sa", SecurityAdmin.FIELDS.REAL_NAME)
                    .field("sa", SecurityAdmin.FIELDS.PHOTO_URI)
                    .where(Where.create(cond).orderDesc("sa", SecurityAdmin.FIELDS.CREATE_TIME));
            if (page > 0 && pageSize > 0) {
                return session.find(SQL.create(select),
                        new BeanResultSetHandler<>(SecurityAdminListVO.class), Page.create(page).pageSize(pageSize));
            }
            return session.find(SQL.create(select),
                    new BeanResultSetHandler<>(SecurityAdminListVO.class));
        });

    }

    @Override
    public SecurityAdmin create(SecurityAdmin securityAdmin) throws Exception {
        return securityAdmin.save();
    }

}
