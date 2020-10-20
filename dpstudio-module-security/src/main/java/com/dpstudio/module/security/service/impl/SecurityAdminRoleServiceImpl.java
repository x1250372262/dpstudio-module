package com.dpstudio.module.security.service.impl;

import com.dpstudio.dev.core.R;
import com.dpstudio.dev.utils.BeanUtils;
import com.dpstudio.module.security.core.Code;
import com.dpstudio.module.security.core.CommonMethod;
import com.dpstudio.module.security.dao.ISecurityAdminRoleDao;
import com.dpstudio.module.security.model.SecurityAdminRole;
import com.dpstudio.module.security.service.ISecurityAdminRoleService;
import com.dpstudio.module.security.vo.SecurityAdminRoleListVO;
import com.dpstudio.module.security.vo.SecurityAdminRoleOPVO;
import net.ymate.platform.core.beans.annotation.Bean;
import net.ymate.platform.core.beans.annotation.Inject;
import net.ymate.platform.core.util.DateTimeUtils;
import net.ymate.platform.core.util.UUIDUtils;
import net.ymate.platform.persistence.IResultSet;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 刘玉奇.
 * @Date: 2020/10/15.
 * @Time: 22:31.
 * @Description:
 */
@Bean
public class SecurityAdminRoleServiceImpl implements ISecurityAdminRoleService {

    @Inject
    private ISecurityAdminRoleDao iSecurityAdminRoleDao;

    @Override
    public IResultSet<SecurityAdminRoleListVO> list(String adminId, int page, int pageSize) throws Exception {
        return iSecurityAdminRoleDao.findAll(adminId, page, pageSize);
    }

    @Override
    public R create(SecurityAdminRoleOPVO adminRoleOPVO) throws Exception {
        SecurityAdminRole securityAdminRole = iSecurityAdminRoleDao.findByRoleIdAndAdminId(adminRoleOPVO.getRoleId(), adminRoleOPVO.getAdminId());
        if (securityAdminRole != null) {
            return R.create(Code.SECURITY_SYSTEM_ADMIN_ROLE_EXIST.getCode()).msg(Code.SECURITY_SYSTEM_ADMIN_ROLE_EXIST.getMsg());
        }
        securityAdminRole = BeanUtils.copy(adminRoleOPVO, SecurityAdminRole::new, (adminRoleOPVOCopy, securityAdminRoleCopy) -> {
            securityAdminRoleCopy.setId(UUIDUtils.UUID());
            securityAdminRoleCopy.setCreateTime(DateTimeUtils.currentTimeMillis());
            securityAdminRoleCopy.setCreateUser(CommonMethod.userId());
            securityAdminRoleCopy.setLastModifyTime(DateTimeUtils.currentTimeMillis());
            securityAdminRoleCopy.setLastModifyUser(CommonMethod.userId());
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
