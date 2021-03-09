package com.dpstudio.module.security.dao.impl;

import com.dpstudio.module.security.core.SecurityConstants;
import com.dpstudio.module.security.dao.ISecurityAdminDao;
import com.dpstudio.module.security.model.SecurityAdmin;
import com.dpstudio.module.security.vo.list.SecurityAdminListVO;
import com.sun.xml.internal.bind.v2.model.core.ID;
import net.ymate.platform.core.beans.annotation.Bean;
import net.ymate.platform.core.persistence.Fields;
import net.ymate.platform.core.persistence.IResultSet;
import net.ymate.platform.core.persistence.Page;
import net.ymate.platform.core.persistence.Params;
import net.ymate.platform.persistence.jdbc.IDBLocker;
import net.ymate.platform.persistence.jdbc.IDatabaseSession;
import net.ymate.platform.persistence.jdbc.IDatabaseSessionExecutor;
import net.ymate.platform.persistence.jdbc.JDBC;
import net.ymate.platform.persistence.jdbc.base.impl.BeanResultSetHandler;
import net.ymate.platform.persistence.jdbc.query.Cond;
import net.ymate.platform.persistence.jdbc.query.SQL;
import net.ymate.platform.persistence.jdbc.query.Select;
import net.ymate.platform.persistence.jdbc.query.Where;

import java.util.List;

@Bean
public class SecurityAdminDaoImpl implements ISecurityAdminDao {

    @Override
    public SecurityAdmin findById(String id, IDBLocker idbLocker, String... fields) throws Exception {
        return SecurityAdmin.builder().id(id).build().load(Fields.create(fields), idbLocker);
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
    public void updateAll(List<SecurityAdmin> securityAdminList, String... fields) throws Exception {
        JDBC.get().openSession((IDatabaseSessionExecutor<Object>) session -> session.update(securityAdminList, Fields.create(fields)));
    }

    @Override
    public IResultSet<SecurityAdminListVO> list(String userName, String realName, Integer disableStatus, Integer page, Integer pageSize) throws Exception {
        Cond cond = Cond.create().eqWrap(Fields.field("sa", SecurityAdmin.FIELDS.FOUNDER)).param(SecurityConstants.BOOL_FALSE)
                .and().eqWrap(Fields.field("sa", SecurityAdmin.FIELDS.DELETE_STATUS)).param(SecurityConstants.BOOL_FALSE)
                .exprNotEmpty(userName, c -> c.and().likeWrap(Fields.field("sa", SecurityAdmin.FIELDS.USER_NAME)).param("%" + userName + "%"))
                .exprNotEmpty(realName, c -> c.and().likeWrap(Fields.field("sa", SecurityAdmin.FIELDS.REAL_NAME)).param("%" + realName + "%"))
                .exprNotEmpty(disableStatus, c -> c.and().eqWrap(Fields.field("sa", SecurityAdmin.FIELDS.DISABLE_STATUS)).param(disableStatus));
        return JDBC.get().openSession(session -> {
            String prefix = session.getConnectionHolder().getDataSourceConfig().getTablePrefix();

            Select select = Select.create(prefix, SecurityAdmin.TABLE_NAME, "sa")
                    .field("sa", SecurityAdmin.FIELDS.ID)
                    .field("sa", SecurityAdmin.FIELDS.CREATE_TIME)
                    .field("sa", SecurityAdmin.FIELDS.DISABLE_STATUS)
                    .field("sa", SecurityAdmin.FIELDS.LOGIN_LOCK_STATUS)
                    .field("sa", SecurityAdmin.FIELDS.USER_NAME)
                    .field("sa", SecurityAdmin.FIELDS.REAL_NAME)
                    .field("sa", SecurityAdmin.FIELDS.PHOTO_URI)
                    .where(Where.create(cond).orderByDesc("sa", SecurityAdmin.FIELDS.CREATE_TIME));
            return session.find(SQL.create(select),
                    new BeanResultSetHandler<>(SecurityAdminListVO.class), Page.createIfNeed(page, pageSize));
        });

    }

    @Override
    public SecurityAdmin create(SecurityAdmin securityAdmin) throws Exception {
        return securityAdmin.save();
    }

    @Override
    public IResultSet<SecurityAdmin> findAllByIds(List<String> params, IDBLocker idbLocker) throws Exception {
        return SecurityAdmin.builder().build().find(Where.create(Cond.create().in(SecurityAdmin.FIELDS.ID, Params.create(params))), idbLocker);
    }

    @Override
    public void delete(List<SecurityAdmin> list) throws Exception {
        JDBC.get().openSession((IDatabaseSessionExecutor<Object>) session -> session.delete(list));
    }

}
