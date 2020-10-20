package com.dpstudio.module.security.service.impl;

import com.dpstudio.dev.core.R;
import com.dpstudio.dev.core.code.C;
import com.dpstudio.dev.utils.BeanUtils;
import com.dpstudio.module.security.core.CommonMethod;
import com.dpstudio.module.security.core.ResultSetUtils;
import com.dpstudio.module.security.model.SecurityRole;
import com.dpstudio.module.security.core.Code;
import com.dpstudio.module.security.dao.ISecurityRoleDao;
import com.dpstudio.module.security.service.ISecurityRoleService;
import com.dpstudio.module.security.vo.SecurityRoleDetailVO;
import com.dpstudio.module.security.vo.SecurityRoleListVO;
import com.dpstudio.module.security.vo.SecurityRoleOPVO;
import com.dpstudio.module.security.vo.SecurityRoleSelectVO;
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
 * @Time: 15:44.
 * @Description: 角色管理
 */
@Bean
public class SecurityRoleServiceImpl implements ISecurityRoleService {

    @Inject
    private ISecurityRoleDao iSecurityRoleDao;

    @Override
    public IResultSet<SecurityRoleListVO> list(String name, int page, int pageSize) throws Exception {
        IResultSet<SecurityRole> securityRoleIResultSet = iSecurityRoleDao.findAll(name, page, pageSize);
        return ResultSetUtils.copy(securityRoleIResultSet, SecurityRoleListVO::new);
    }

    @Override
    public List<SecurityRoleSelectVO> select() throws Exception {
        IResultSet<SecurityRole> securityRoleIResultSet = iSecurityRoleDao.findAll(null, 0, 0);
        return BeanUtils.copyList(securityRoleIResultSet.getResultData(), SecurityRoleSelectVO::new);
    }

    @Override
    public R create(SecurityRoleOPVO securityRoleOPVO) throws Exception {
        SecurityRole securityRole = iSecurityRoleDao.findByName(securityRoleOPVO.getName());
        if (securityRole != null) {
            return R.create(Code.SECURITY_SYSTEM_ROLE_NAME_EXIST.getCode()).msg(Code.SECURITY_SYSTEM_ROLE_NAME_EXIST.getMsg());
        }
        securityRole = BeanUtils.copy(securityRoleOPVO, SecurityRole::new, (securityRoleOPVCopy, securityRoleCopy) -> {
            securityRoleCopy.setId(UUIDUtils.UUID());
            securityRoleCopy.setCreateTime(DateTimeUtils.currentTimeMillis());
            securityRoleCopy.setCreateUser(CommonMethod.userId());
            securityRoleCopy.setLastModifyTime(DateTimeUtils.currentTimeMillis());
            securityRoleCopy.setLastModifyUser(CommonMethod.userId());
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
    public R update(String id, Long lastModifyTime, SecurityRoleOPVO securityRoleOPVO) throws Exception {
        SecurityRole securityRole = iSecurityRoleDao.findByNameNotId(securityRoleOPVO.getName(), id);
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
        securityRole = BeanUtils.copy(securityRoleOPVO, SecurityRole::new, (securityRoleOPVOCopy, securityRoleCopy) -> {
            securityRoleCopy.setId(id);
            securityRoleCopy.setLastModifyTime(DateTimeUtils.currentTimeMillis());
            securityRoleCopy.setLastModifyUser(CommonMethod.userId());
        });
        securityRole = iSecurityRoleDao.update(securityRole, SecurityRole.FIELDS.NAME, SecurityRole.FIELDS.REMARK,
                SecurityRole.FIELDS.LAST_MODIFY_TIME, SecurityRole.FIELDS.LAST_MODIFY_USER);
        return R.result(securityRole);
    }

    @Override
    public R delete(String[] ids) throws Exception {
        List<SecurityRole> list = new ArrayList<>();
        for (String id : ids) {
            list.add(SecurityRole.builder().id(id).build());
        }
        iSecurityRoleDao.delete(list);
        return R.ok();
    }
}
