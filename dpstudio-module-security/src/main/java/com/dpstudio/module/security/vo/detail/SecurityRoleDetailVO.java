package com.dpstudio.module.security.vo.detail;

import com.alibaba.fastjson.annotation.JSONField;
import com.dpstudio.module.security.model.SecurityRole;

public class SecurityRoleDetailVO {

    private String id;

    private String name;

    private String remark;

    @JSONField(name = SecurityRole.FIELDS.CREATE_TIME)
    private Long createTime;

    @JSONField(name = SecurityRole.FIELDS.LAST_MODIFY_TIME)
    private Long lastModifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
