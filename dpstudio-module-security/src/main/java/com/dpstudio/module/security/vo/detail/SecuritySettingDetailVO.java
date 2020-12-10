package com.dpstudio.module.security.vo.detail;

import com.alibaba.fastjson.annotation.JSONField;
import com.dpstudio.module.security.model.SecuritySetting;

public class SecuritySettingDetailVO {

    private String id;

    @JSONField(name = SecuritySetting.FIELDS.LOGIN_LOG_STATUS)
    private Integer loginLogStatus;

    @JSONField(name = SecuritySetting.FIELDS.LOGIN_ERROR_COUNT)
    private Integer loginErrorCount;

    @JSONField(name = SecuritySetting.FIELDS.LOGIN_ERROR_TIME)
    private Integer loginErrorTime;

    @JSONField(name = SecuritySetting.FIELDS.LOGIN_ERROR_STATUS)
    private Integer loginErrorStatus;

    @JSONField(name = SecuritySetting.FIELDS.LOGIN_NOT_IP_STATUS)
    private Integer loginNotIpStatus;

    @JSONField(name = SecuritySetting.FIELDS.LOGIN_NOT_IP_NOTICE)
    private Integer loginNotIpNotice;

    @JSONField(name = SecuritySetting.FIELDS.LAST_MODIFY_TIME)
    private Long lastModifyTime;

    public Integer getLoginLogStatus() {
        return loginLogStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLoginLogStatus(Integer loginLogStatus) {
        this.loginLogStatus = loginLogStatus;
    }

    public Integer getLoginErrorCount() {
        return loginErrorCount;
    }

    public void setLoginErrorCount(Integer loginErrorCount) {
        this.loginErrorCount = loginErrorCount;
    }

    public Integer getLoginErrorTime() {
        return loginErrorTime;
    }

    public void setLoginErrorTime(Integer loginErrorTime) {
        this.loginErrorTime = loginErrorTime;
    }

    public Integer getLoginErrorStatus() {
        return loginErrorStatus;
    }

    public void setLoginErrorStatus(Integer loginErrorStatus) {
        this.loginErrorStatus = loginErrorStatus;
    }

    public Integer getLoginNotIpStatus() {
        return loginNotIpStatus;
    }

    public void setLoginNotIpStatus(Integer loginNotIpStatus) {
        this.loginNotIpStatus = loginNotIpStatus;
    }

    public Integer getLoginNotIpNotice() {
        return loginNotIpNotice;
    }

    public void setLoginNotIpNotice(Integer loginNotIpNotice) {
        this.loginNotIpNotice = loginNotIpNotice;
    }

    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}
