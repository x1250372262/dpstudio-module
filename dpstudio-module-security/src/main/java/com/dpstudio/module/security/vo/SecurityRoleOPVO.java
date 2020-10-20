package com.dpstudio.module.security.vo;

import net.ymate.platform.validation.validate.VRequired;
import net.ymate.platform.webmvc.annotation.RequestParam;

/**
 * @Author: 刘玉奇.
 * @Date: 2020/10/15.
 * @Time: 16:55.
 * @Description:
 */
public class SecurityRoleOPVO {

    @VRequired(msg = "角色名称不能为空")
    @RequestParam
    private String name;
    @RequestParam
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
