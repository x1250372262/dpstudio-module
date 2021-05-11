package com.mx.module.security.vo.list;

import com.alibaba.fastjson.annotation.JSONField;
import com.mx.module.security.model.SecurityAdminRole;

public class SecurityAdminRoleListVO {

    private String id;

    @JSONField(name = SecurityAdminRole.FIELDS.ADMIN_ID)
    private String adminId;

    @JSONField(name = SecurityAdminRole.FIELDS.ROLE_ID)
    private String roleId;

    @JSONField(name = "role_name")
    private String roleName;

    @JSONField(name = SecurityAdminRole.FIELDS.CREATE_TIME)
    private Long createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdminId() {
        return adminId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
