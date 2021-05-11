package com.mx.module.security.dto;

import com.mx.module.security.model.SecurityAdmin;
import net.ymate.platform.validation.validate.VRequired;
import net.ymate.platform.webmvc.annotation.RequestParam;

/**
 * @Author: mengxiang.
 * @Date: 2020/10/15.
 * @Time: 4:11 下午.
 * @Description:
 */
public class SecurityAdminDTO {

    @RequestParam(value = SecurityAdmin.FIELDS.REAL_NAME)
    private String realName;

    @RequestParam
    private Integer gender;

    @RequestParam
    private String thumb;

    @RequestParam
    private String mobile;

    @VRequired(msg = "用户名称不能为空")
    @RequestParam(value = SecurityAdmin.FIELDS.USER_NAME)
    private String userName;

    @RequestParam(value = SecurityAdmin.FIELDS.PHOTO_URI)
    private String photoUri;


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

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getMobile() {
        return mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
