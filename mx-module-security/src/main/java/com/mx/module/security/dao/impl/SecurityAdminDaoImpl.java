package com.mx.module.security.dao.impl;

import com.mx.dev.dto.PageDTO;
import com.mx.module.security.core.SecurityConstants;
import com.mx.module.security.dao.ISecurityAdminDao;
import com.mx.module.security.model.SecurityAdmin;
import com.mx.module.security.vo.list.SecurityAdminListVO;
import net.ymate.platform.core.beans.annotation.Bean;
import net.ymate.platform.core.persistence.Fields;
import net.ymate.platform.core.persistence.IResultSet;
import net.ymate.platform.core.persistence.Params;
import net.ymate.platform.persistence.jdbc.IDBLocker;
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
    public SecurityAdmin findByUserNameAndClientName(String userName, String clientName, String... fields) throws Exception {
        return SecurityAdmin.builder().userName(userName).clientName(clientName).deleteStatus(SecurityConstants.BOOL_FALSE).build().findFirst(Fields.create(fields));
    }

    @Override
    public SecurityAdmin update(SecurityAdmin securityAdmin, String... fields) throws Exception {
        return securityAdmin.update(Fields.create(fields));
    }

    @Override
    public List<SecurityAdmin> updateAll(List<SecurityAdmin> securityAdminList, String... fields) throws Exception {
        return JDBC.get().openSession(session -> session.update(securityAdminList, Fields.create(fields)));
    }

    @Override
    public List<SecurityAdmin> createAll(List<SecurityAdmin> securityAdminList) throws Exception {
        return JDBC.get().openSession(session -> session.insert(securityAdminList));
    }

    @Override
    public SecurityAdmin findByClientNameAndFounder(String clientName, Integer founder) throws Exception {
        return SecurityAdmin.builder().clientName(clientName).founder(founder).build().findFirst();
    }

    @Override
    public IResultSet<SecurityAdminListVO> list(String clientName,String userName, String realName, Integer disableStatus, PageDTO pageDTO) throws Exception {
        Cond cond = Cond.create().eqWrap(Fields.field("sa", SecurityAdmin.FIELDS.FOUNDER)).param(SecurityConstants.BOOL_FALSE)
                .and().eqWrap(Fields.field("sa", SecurityAdmin.FIELDS.DELETE_STATUS)).param(SecurityConstants.BOOL_FALSE)
                .and().eqWrap(Fields.field("sa",SecurityAdmin.FIELDS.CLIENT_NAME)).param(clientName)
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
                    new BeanResultSetHandler<>(SecurityAdminListVO.class), pageDTO.toPage());
        });

    }

    @Override
    public SecurityAdmin create(SecurityAdmin securityAdmin) throws Exception {
        return securityAdmin.save();
    }

    @Override
    public IResultSet<SecurityAdmin> findAllByIds(List<String> params, IDBLocker idbLocker) throws Exception {
        return SecurityAdmin.builder().build().find(Where.create(Cond.create().inWrap(SecurityAdmin.FIELDS.ID, Params.create(params))), idbLocker);
    }

    @Override
    public int[] delete(String[] ids) throws Exception {
        return JDBC.get().openSession(session -> session.delete(SecurityAdmin.class, ids));
    }

}
