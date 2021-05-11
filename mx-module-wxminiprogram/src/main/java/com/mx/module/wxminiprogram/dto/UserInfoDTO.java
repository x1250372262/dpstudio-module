package com.mx.module.wxminiprogram.dto;

import net.ymate.platform.validation.validate.VRequired;
import net.ymate.platform.webmvc.annotation.RequestParam;

/**
 * @Author: mengxiang.
 * @create: 2021-03-31 09:22
 * @Description:
 */
public class UserInfoDTO {

    /**
     * openId|Y|xxxxx
     */
    @VRequired(msg = "openId不能为空")
    @RequestParam
    private String openId;

    /**
     * 头像|N|xxxxx
     */
    @RequestParam
    private String avatarUrl;

    /**
     * 城市|N|xxxxx
     */
    @RequestParam
    private String city;

    /**
     * 国家|N|xxxxx
     */
    @RequestParam
    private String country;

    /**
     * 性别|N|xxxxx
     */
    @RequestParam
    private Integer gender;

    /**
     * 昵称|N|xxxxx
     */
    @RequestParam
    private String nickName;

    /**
     * 省|N|xxxxx
     */
    @RequestParam
    private String province;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
