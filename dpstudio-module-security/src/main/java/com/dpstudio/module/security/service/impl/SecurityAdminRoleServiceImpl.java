package com.dpstudio.module.security.service.impl;

import com.dpstudio.dev.core.R;
import com.dpstudio.dev.dto.PageDTO;
import com.dpstudio.dev.utils.BeanUtils;
import com.dpstudio.module.security.SecurityCache;
import com.dpstudio.module.security.core.Code;
import com.dpstudio.module.security.dao.ISecurityAdminRoleDao;
import com.dpstudio.module.security.dto.SecurityAdminRoleDTO;
import com.dpstudio.module.security.model.SecurityAdminRole;
import com.dpstudio.module.security.service.ISecurityAdminRoleService;
import com.dpstudio.module.security.vo.list.SecurityAdminRoleListVO;
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
    public IResultSet<SecurityAdminRoleListVO> list(String adminId, PageDTO pageDTO) throws Exception {
        return iSecurityAdminRoleDao.findAll(adminId, pageDTO);
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
