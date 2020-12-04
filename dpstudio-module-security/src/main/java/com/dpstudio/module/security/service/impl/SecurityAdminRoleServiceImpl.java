package com.dpstudio.module.security.service.impl;

import com.dpstudio.dev.core.R;
import com.dpstudio.dev.utils.BeanUtils;
import com.dpstudio.module.security.SecurityCache;
import com.dpstudio.module.security.core.Code;
import com.dpstudio.module.security.dao.ISecurityAdminRoleDao;
import com.dpstudio.module.security.model.SecurityAdminRole;
import com.dpstudio.module.security.service.ISecurityAdminRoleService;
import com.dpstudio.module.security.vo.list.SecurityAdminRoleListVO;
import com.dpstudio.module.security.vo.op.SecurityAdminRoleVO;
import net.ymate.platform.commons.util.DateTimeUtils;
import net.ymate.platform.commons.util.UUIDUtils;
import net.ymate.platform.core.beans.annotation.Bean;
import net.ymate.platform.core.beans.annotation.Inject;
import net.ymate.platform.core.persistence.IResultSet;

import java.util.ArrayList;
import java.util.List;

@Bean
public class SecurityAdminRoleServiceImpl implements ISecurityAdminRoleService {

    @Inject
    private ISecurityAdminRoleDao iSecurityAdminRoleDao;

    @Override
    public IResultSet<SecurityAdminRoleListVO> list(String adminId, Integer page, Integer pageSize) throws Exception {
        return iSecurityAdminRoleDao.findAll(adminId, page, pageSize);
    }

    @Override
    public R create(SecurityAdminRoleVO adminRoleVO) throws Exception {
        SecurityAdminRole securityAdminRole = iSecurityAdminRoleDao.findByRoleIdAndAdminId(adminRoleVO.getRoleId(), adminRoleVO.getAdminId());
        if (securityAdminRole != null) {
            return R.create(Code.SECURITY_SYSTEM_ADMIN_ROLE_EXIST.getCode()).msg(Code.SECURITY_SYSTEM_ADMIN_ROLE_EXIST.getMsg());
        }
        securityAdminRole = BeanUtils.copy(adminRoleVO, SecurityAdminRole::new, (adminRoleOPVOCopy, securityAdminRoleCopy) -> {
            securityAdminRoleCopy.setId(UUIDUtils.UUID());
            securityAdminRoleCopy.setCreateTime(DateTimeUtils.currentTimeMillis());
            securityAdminRoleCopy.setCreateUser(SecurityCache.userId());
            securityAdminRoleCopy.setLastModifyTime(DateTimeUtils.currentTimeMillis());
            securityAdminRoleCopy.setLastModifyUser(SecurityCache.userId());
        });
        securityAdminRole = iSecurityAdminRoleDao.create(securityAdminRole);
        return R.result(securityAdminRole);
    }

    @Override
    public R delete(String[] ids) throws Exception {
        List<SecurityAdminRole> list = new ArrayList<>();
        for (String id : ids) {
            list.add(SecurityAdminRole.builder().id(id).build());
        }
        iSecurityAdminRoleDao.delete(list);
        return R.ok();
    }

}
