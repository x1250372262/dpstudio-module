package com.mx.module.security.vo.list;

import com.alibaba.fastjson.annotation.JSONField;
import com.mx.module.security.model.SecurityRole;

public class SecurityRoleListVO {

    private String id;

    private String name;

    private String remark;

    @JSONField(name = SecurityRole.FIELDS.CREATE_TIME)
    private Long createTime;

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
