package com.dpstudio.module.security.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.dpstudio.module.security.model.SecurityAdmin;

/**
 * @Author: 刘玉奇.
 * @Date: 2020/10/15.
 * @Time: 18:26.
 * @Description:
 */
public class SecurityAdminListVO {

    private String id;
    @JSONField(name = SecurityAdmin.FIELDS.USER_NAME)
    private String userName;
    @JSONField(name = SecurityAdmin.FIELDS.PHOTO_URI)
    private String photoUri;
    @JSONField(name = SecurityAdmin.FIELDS.REAL_NAME)
    private String realName;
    @JSONField(name = "role_name")
    private String roleName;
    @JSONField(name = SecurityAdmin.FIELDS.CREATE_TIME)
    private Long createTime;
    @JSONField(name = SecurityAdmin.FIELDS.DISABLE_STATUS)
    private Integer disableStatus;
    @JSONField(name = SecurityAdmin.FIELDS.LOGIN_LOCK_STATUS)
    private Integer loginLockStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getDisableStatus() {
        return disableStatus;
    }

    public void setDisableStatus(Integer disableStatus) {
        this.disableStatus = disableStatus;
    }

    public Integer getLoginLockStatus() {
        return loginLockStatus;
    }

    public void setLoginLockStatus(Integer loginLockStatus) {
        this.loginLockStatus = loginLockStatus;
    }
}
