package com.dpstudio.module.security.vo.op;

import com.dpstudio.module.security.model.SecuritySetting;
import net.ymate.platform.webmvc.annotation.RequestParam;

public class SecuritySettingVO {

    @RequestParam(value = SecuritySetting.FIELDS.LOGIN_LOG_STATUS)
    private Integer loginLogStatus;

    @RequestParam(value = SecuritySetting.FIELDS.LOGIN_ERROR_COUNT)
    private Integer loginErrorCount;

    @RequestParam(value = SecuritySetting.FIELDS.LOGIN_ERROR_TIME)
    private Integer loginErrorTime;

    @RequestParam(value = SecuritySetting.FIELDS.LOGIN_ERROR_STATUS)
    private Integer loginErrorStatus;

    @RequestParam(value = SecuritySetting.FIELDS.LOGIN_UNLOCK_FOUNDER)
    private Integer loginUnlockFounder;

    @RequestParam(value = SecuritySetting.FIELDS.LOGIN_NOT_IP_STATUS)
    private Integer loginNotIpStatus;

    @RequestParam(value = SecuritySetting.FIELDS.LOGIN_NOT_IP_NOTICE)
    private Integer loginNotIpNotice;

    @RequestParam(value = SecuritySetting.FIELDS.LOGIN_CLIENT_STATUS)
    private Integer loginClientStatus;

    @RequestParam(value = SecuritySetting.FIELDS.LOGIN_CLIENT_TYPE)
    private Integer loginClientType;

    public Integer getLoginLogStatus() {
        return loginLogStatus;
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

    public Integer getLoginUnlockFounder() {
        return loginUnlockFounder;
    }

    public void setLoginUnlockFounder(Integer loginUnlockFounder) {
        this.loginUnlockFounder = loginUnlockFounder;
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

    public Integer getLoginClientStatus() {
        return loginClientStatus;
    }

    public void setLoginClientStatus(Integer loginClientStatus) {
        this.loginClientStatus = loginClientStatus;
    }

    public Integer getLoginClientType() {
        return loginClientType;
    }

    public void setLoginClientType(Integer loginClientType) {
        this.loginClientType = loginClientType;
    }
}
