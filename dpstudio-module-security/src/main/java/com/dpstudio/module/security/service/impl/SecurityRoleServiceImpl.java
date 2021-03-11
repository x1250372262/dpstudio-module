package com.dpstudio.module.security.service.impl;

import com.dpstudio.dev.code.C;
import com.dpstudio.dev.core.R;
import com.dpstudio.dev.dto.PageDTO;
import com.dpstudio.dev.utils.BeanUtils;
import com.dpstudio.dev.utils.ResultSetUtils;
import com.dpstudio.module.security.SecurityCache;
import com.dpstudio.module.security.core.Code;
import com.dpstudio.module.security.dao.ISecurityRoleDao;
import com.dpstudio.module.security.model.SecurityRole;
import com.dpstudio.module.security.service.ISecurityRoleService;
import com.dpstudio.module.security.vo.detail.SecurityRoleDetailVO;
import com.dpstudio.module.security.vo.detail.SecurityRoleListVO;
import com.dpstudio.module.security.vo.op.SecurityRoleVO;
import com.dpstudio.module.security.vo.select.SecurityRoleSelectVO;
import net.ymate.platform.commons.util.DateTimeUtils;
import net.ymate.platform.commons.util.UUIDUtils;
import net.ymate.platform.core.beans.annotation.Bean;
import net.ymate.platform.core.beans.annotation.Inject;
import net.ymate.platform.core.persistence.IResultSet;

import java.util.List;

@Bean
public class SecurityRoleServiceImpl implements ISecurityRoleService {

    @Inject
    private ISecurityRoleDao iSecurityRoleDao;

    @Override
    public IResultSet<SecurityRoleListVO> list(String name, PageDTO pageDTO) throws Exception {
        IResultSet<SecurityRole> securityRoleResultSet = iSecurityRoleDao.findAll(name, pageDTO);
        return ResultSetUtils.copy(securityRoleResultSet, SecurityRoleListVO::new);
    }

    @Override
    public List<SecurityRoleSelectVO> select() throws Exception {
        IResultSet<SecurityRole> securityRoleResultSet = iSecurityRoleDao.findAll(null, PageDTO.get());
        return BeanUtils.copyList(securityRoleResultSet.getResultData(), SecurityRoleSelectVO::new);
    }

    @Override
    public R create(SecurityRoleVO securityRoleVO) throws Exception {
        SecurityRole securityRole = iSecurityRoleDao.findByName(securityRoleVO.getName());
        if (securityRole != null) {
            return R.create(Code.SECURITY_SYSTEM_ROLE_NAME_EXIST.getCode()).msg(Code.SECURITY_SYSTEM_ROLE_NAME_EXIST.getMsg());
        }
        securityRole = BeanUtils.copy(securityRoleVO, SecurityRole::new, (s, t) -> {
            t.setId(UUIDUtils.UUID());
            t.setCreateTime(DateTimeUtils.currentTimeMillis());
            t.setCreateUser(SecurityCache.userId());
            t.setLastModifyTime(DateTimeUtils.currentTimeMillis());
            t.setLastModifyUser(SecurityCache.userId());
        });
        securityRole = iSecurityRoleDao.create(securityRole);
        return R.result(securityRole);
    }

    @Override
    public SecurityRoleDetailVO detail(String id, String... fields) throws Exception {
        SecurityRole securityRole = iSecurityRoleDao.findById(id);
        return BeanUtils.copy(securityRole, SecurityRoleDetailVO::new);
    }

    @Override
    public R update(String id, Long lastModifyTime, SecurityRoleVO securityRoleVO) throws Exception {
        SecurityRole securityRole = iSecurityRoleDao.findByNameNotId(securityRoleVO.getName(), id);
        if (securityRole != null) {
            return R.create(Code.SECURITY_SYSTEM_ROLE_NAME_EXIST.getCode()).msg(Code.SECURITY_SYSTEM_ROLE_NAME_EXIST.getMsg());
        }
        securityRole = iSecurityRoleDao.findById(id);
        if (securityRole == null) {
            return R.create(C.NO_DATA.getCode()).msg(C.NO_DATA.getMsg());
        }
        if (!R.checkVersion(lastModifyTime, securityRole.getLastModifyTime())) {
            return R.create(C.VERSION_NOT_SAME.getCode()).msg(C.VERSION_NOT_SAME.getMsg());
        }
        securityRole = BeanUtils.duplicate(securityRoleVO, securityRole, (s, t) -> {
            t.setLastModifyTime(DateTimeUtils.currentTimeMillis());
            t.setLastModifyUser(SecurityCache.userId());
        });
        securityRole = iSecurityRoleDao.update(securityRole, SecurityRole.FIELDS.NAME, SecurityRole.FIELDS.REMARK,
                SecurityRole.FIELDS.LAST_MODIFY_TIME, SecurityRole.FIELDS.LAST_MODIFY_USER);
        return R.result(securityRole);
    }

    @Override
    public R delete(String[] ids) throws Exception {
        int[] result = iSecurityRoleDao.delete(ids);
        return R.result(result);
    }
}
