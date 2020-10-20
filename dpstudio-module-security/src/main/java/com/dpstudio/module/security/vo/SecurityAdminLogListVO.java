package com.dpstudio.module.security.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.dpstudio.module.security.model.SecurityAdminLog;

/**
 * @Author: 刘玉奇.
 * @Date: 2020/10/16.
 * @Time: 22:09.
 * @Description:
 */
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
