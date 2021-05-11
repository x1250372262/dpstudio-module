package com.mx.module.security.vo.list;

import com.alibaba.fastjson.annotation.JSONField;
import com.mx.module.security.model.SecurityAdminLog;

public class SecurityAdminLogListVO {

    private String id;

    @JSONField(name = SecurityAdminLog.FIELDS.ADMIN_ID)
    private String adminId;

    private String content;

    @JSONField(name = SecurityAdminLog.FIELDS.CREATE_TIME)
    private Long createTime;

    public String getId() {
        return id;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
