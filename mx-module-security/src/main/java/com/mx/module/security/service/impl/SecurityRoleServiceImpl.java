package com.mx.module.security.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mx.dev.bean.PageBean;
import com.mx.dev.code.C;
import com.mx.dev.core.R;
import com.mx.dev.security.Security;
import com.mx.dev.security.bean.PermissionBean;
import com.mx.dev.utils.BeanUtils;
import com.mx.dev.utils.ResultSetUtils;
import com.mx.module.security.SecurityCache;
import com.mx.module.security.core.Code;
import com.mx.module.security.dao.ISecurityRoleDao;
import com.mx.module.security.dao.ISecurityRolePermissionDao;
import com.mx.module.security.dto.SecurityRoleDTO;
import com.mx.module.security.model.SecurityAdmin;
import com.mx.module.security.model.SecurityRole;
import com.mx.module.security.model.SecurityRolePermission;
import com.mx.module.security.service.ISecurityRoleService;
import com.mx.module.security.vo.detail.SecurityRoleDetailVO;
import com.mx.module.security.vo.list.SecurityRoleListVO;
import com.mx.module.security.vo.select.SecurityRoleSelectVO;
import net.ymate.platform.commons.util.DateTimeUtils;
import net.ymate.platform.commons.util.UUIDUtils;
import net.ymate.platform.core.beans.annotation.Bean;
import net.ymate.platform.core.beans.annotation.Inject;
import net.ymate.platform.core.persistence.IResultSet;
import net.ymate.platform.core.persistence.impl.DefaultResultSet;

import java.util.ArrayList;
import java.util.List;

@Bean
public class SecurityRoleServiceImpl implements ISecurityRoleService {

    @Inject
    private ISecurityRoleDao iSecurityRoleDao;
    @Inject
    private ISecurityRolePermissionDao iSecurityRolePermissionDao;

    @Override
    public IResultSet<SecurityRoleListVO> list(String name, PageBean pageBean) throws Exception {
        SecurityAdmin loginAdmin = SecurityCache.AdminCache.getPara(SecurityCache.userId());
        if (loginAdmin == null) {
            return new DefaultResultSet<>(new ArrayList<>());
        }
        IResultSet<SecurityRole> securityRoleResultSet = iSecurityRoleDao.findAll(name, loginAdmin.getClientName(), pageBean);
        return ResultSetUtils.copy(securityRoleResultSet, SecurityRoleListVO::new);
    }

    @Override
    public List<SecurityRoleSelectVO> select() throws Exception {
        SecurityAdmin loginAdmin = SecurityCache.AdminCache.getPara(SecurityCache.userId());
        if (loginAdmin == null) {
            return new ArrayList<>();
        }
        IResultSet<SecurityRole> securityRoleResultSet = iSecurityRoleDao.findAll(null, loginAdmin.getClientName(), PageBean.noPage());
        return BeanUtils.copyList(securityRoleResultSet.getResultData(), SecurityRoleSelectVO::new);
    }

    @Override
    public R create(SecurityRoleDTO securityRoleDTO) throws Exception {
        SecurityAdmin loginAdmin = SecurityCache.AdminCache.getPara(SecurityCache.userId());
        if (loginAdmin == null) {
            return R.create(Code.SECURITY_ADMIN_INVALID_OR_TIMEOUT.getCode()).msg(Code.SECURITY_ADMIN_INVALID_OR_TIMEOUT.getMsg());
        }
        SecurityRole securityRole = iSecurityRoleDao.findByName(securityRoleDTO.getName());
        if (securityRole != null) {
            return R.create(Code.SECURITY_SYSTEM_ROLE_NAME_EXIST.getCode()).msg(Code.SECURITY_SYSTEM_ROLE_NAME_EXIST.getMsg());
        }
        securityRole = BeanUtils.copy(securityRoleDTO, SecurityRole::new, (s, t) -> {
            t.setId(UUIDUtils.UUID());
            t.setClientName(loginAdmin.getClientName());
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
    public R update(String id, Long lastModifyTime, SecurityRoleDTO securityRoleDTO) throws Exception {
        SecurityAdmin loginAdmin = SecurityCache.AdminCache.getPara(SecurityCache.userId());
        SecurityRole securityRole = iSecurityRoleDao.findByNameNotId(securityRoleDTO.getName(), id);
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
        securityRole = BeanUtils.duplicate(securityRoleDTO, securityRole, (s, t) -> {
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

    @Override
    public R permissionDetail(String id) throws Exception {
        JSONObject jsonObject = new JSONObject();
        SecurityRole securityRole = iSecurityRoleDao.findById(id);
        if (securityRole == null) {
            return R.noData();
        }
        IResultSet<SecurityRolePermission> rolePermission = iSecurityRolePermissionDao.findByRoleId(id);
        if (rolePermission.isResultsAvailable()) {
            List<SecurityRolePermission> rolePermissionList = rolePermission.getResultData();
            String[] selectRoles = new String[rolePermissionList.size()];
            for (int i = 0; i < rolePermissionList.size(); i++) {
                selectRoles[i] = rolePermissionList.get(i).getPermissionCode();
            }
            jsonObject.put("permissions", selectRoles);
        }
        return R.ok().data(jsonObject);
    }

    @Override
    public R permissionSet(String id, String[] permissions) throws Exception {
        SecurityAdmin loginAdmin = SecurityCache.AdminCache.getPara(SecurityCache.userId());
        if (loginAdmin == null) {
            return R.fail();
        }
        SecurityRole securityRole = iSecurityRoleDao.findById(id);
        if (securityRole == null) {
            return R.noData();
        }
        IResultSet<SecurityRolePermission> rolePermission = iSecurityRolePermissionDao.findByRoleId(id);
        if (rolePermission.isResultsAvailable()) {
            iSecurityRolePermissionDao.delete(rolePermission.getResultData());
        }
        if (permissions != null && permissions.length > 0) {
            List<SecurityRolePermission> rolePermissions = new ArrayList<>();
            for (String permissionCode : permissions) {
                PermissionBean permissionBean = Security.get().findByCode(null, permissionCode, loginAdmin.getClientName());
                if (permissionBean == null) {
                    continue;
                }
                SecurityRolePermission securityRolePermission = SecurityRolePermission.builder()
                        .id(UUIDUtils.UUID())
                        .roleId(id)
                        .groupId(permissionBean.getGroupId())
                        .groupName(permissionBean.getGroupName())
                        .permissionName(permissionBean.getName())
                        .permissionCode(permissionBean.getCode())
                        .createUser(SecurityCache.userId())
                        .createTime(DateTimeUtils.currentTimeMillis())
                        .lastModifyUser(SecurityCache.userId())
                        .lastModifyTime(DateTimeUtils.currentTimeMillis())
                        .build();
                rolePermissions.add(securityRolePermission);
            }
            if (!rolePermissions.isEmpty()) {
                iSecurityRolePermissionDao.createAll(rolePermissions);
            }
        }
        return R.ok();
    }
}
