package com.mx.module.security.vo.detail;

import com.alibaba.fastjson.annotation.JSONField;
import com.mx.module.security.model.SecurityAdmin;

/**
 * @Author: mengxiang.
 * @Date: 2020/10/15.
 * @Time: 4:11 下午.
 * @Description:
 */
public class SecurityAdminDetailVO {

    @JSONField(name = SecurityAdmin.FIELDS.REAL_NAME)
    private String realName;

    private Integer gender;

    private String thumb;

    private String mobile;

    @JSONField(name = SecurityAdmin.FIELDS.USER_NAME)
    private String userName;

    private Integer onLineStatus;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getOnLineStatus() {
        return onLineStatus;
    }

    public void setOnLineStatus(Integer onLineStatus) {
        this.onLineStatus = onLineStatus;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
