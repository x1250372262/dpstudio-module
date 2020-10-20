package com.dpstudio.module.security.vo;

import com.dpstudio.module.security.model.SecurityAdminRole;
import net.ymate.platform.validation.validate.VRequired;
import net.ymate.platform.webmvc.annotation.RequestParam;

/**
 * @Author: 刘玉奇.
 * @Date: 2020/10/15.
 * @Time: 23:19.
 * @Description:
 */
public class SecurityAdminRoleOPVO {

    @VRequired(msg = "管理员id不能为空")
    @RequestParam(value = SecurityAdminRole.FIELDS.ADMIN_ID)
    private String adminId;
    @VRequired(msg = "角色id不能为空")
    @RequestParam(value = SecurityAdminRole.FIELDS.ROLE_ID)
    private String roleId;

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
