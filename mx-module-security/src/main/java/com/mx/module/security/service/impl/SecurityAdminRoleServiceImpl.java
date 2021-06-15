package com.mx.module.security.service.impl;

import com.mx.dev.bean.PageBean;
import com.mx.dev.core.R;
import com.mx.dev.utils.BeanUtils;
import com.mx.module.security.SecurityCache;
import com.mx.module.security.core.Code;
import com.mx.module.security.dao.ISecurityAdminRoleDao;
import com.mx.module.security.dto.SecurityAdminRoleDTO;
import com.mx.module.security.model.SecurityAdminRole;
import com.mx.module.security.service.ISecurityAdminRoleService;
import com.mx.module.security.vo.list.SecurityAdminRoleListVO;
import net.ymate.platform.commons.util.DateTimeUtils;
import net.ymate.platform.commons.util.UUIDUtils;
import net.ymate.platform.core.beans.annotation.Bean;
import net.ymate.platform.core.beans.annotation.Inject;
import net.ymate.platform.core.persistence.IResultSet;

@Bean
public class SecurityAdminRoleServiceImpl implements ISecurityAdminRoleService {

    @Inject
    private ISecurityAdminRoleDao iSecurityAdminRoleDao;

    @Override
    public IResultSet<SecurityAdminRoleListVO> list(String adminId, PageBean pageBean) throws Exception {
        return iSecurityAdminRoleDao.findAll(adminId, pageBean);
    }

    @Override
    public R create(SecurityAdminRoleDTO securityAdminRoleDTO) throws Exception {
        SecurityAdminRole securityAdminRole = iSecurityAdminRoleDao.findByRoleIdAndAdminId(securityAdminRoleDTO.getRoleId(), securityAdminRoleDTO.getAdminId());
        if (securityAdminRole != null) {
            return R.create(Code.SECURITY_SYSTEM_ADMIN_ROLE_EXIST.getCode()).msg(Code.SECURITY_SYSTEM_ADMIN_ROLE_EXIST.getMsg());
        }
        securityAdminRole = BeanUtils.copy(securityAdminRoleDTO, SecurityAdminRole::new, (s, t) -> {
            t.setId(UUIDUtils.UUID());
            t.setCreateTime(DateTimeUtils.currentTimeMillis());
            t.setCreateUser(SecurityCache.userId());
            t.setLastModifyTime(DateTimeUtils.currentTimeMillis());
            t.setLastModifyUser(SecurityCache.userId());
        });
        securityAdminRole = iSecurityAdminRoleDao.create(securityAdminRole);
        return R.result(securityAdminRole);
    }

    @Override
    public R delete(String[] ids) throws Exception {
        int[] result = iSecurityAdminRoleDao.delete(ids);
        return R.result(result);
    }

}
