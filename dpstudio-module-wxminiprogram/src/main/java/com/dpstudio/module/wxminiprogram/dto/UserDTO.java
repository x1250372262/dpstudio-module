package com.dpstudio.module.wxminiprogram.dto;

import net.ymate.platform.validation.validate.VRequired;
import net.ymate.platform.webmvc.annotation.RequestParam;

/**
 * @Author: mengxiang.
 * @create: 2021-03-31 09:22
 * @Description:
 */
public class UserDTO {

    /**
     * sessionKey|Y|xxxxx
     */
    @VRequired(msg = "sessionKey不能为空")
    @RequestParam
    private String sessionKey;

    /**
     * 用户非敏感信息|Y|xxxxx
     */
    @VRequired(msg = "用户非敏感信息不能为空")
    @RequestParam(value = "rawData")
    private String rawData;

    /**
     * 签名|Y|xxxxx
     */
    @VRequired(msg = "签名不能为空")
    @RequestParam(value = "signature")
    private String signature;

    /**
     * 用户敏感信息|Y|xxxxx
     */
    @VRequired(msg = "用户敏感信息不能为空")
    @RequestParam(value = "encrypteData")
    private String encrypteData;

    /**
     * 解密算法的向量|Y|xxxxx
     */
    @VRequired(msg = "解密算法的向量不能为空")
    @RequestParam(value = "iv")
    private String iv;

    /**
     * 其他信息|N|xxxxx
     */
    @RequestParam
    private String attach;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getEncrypteData() {
        return encrypteData;
    }

    public void setEncrypteData(String encrypteData) {
        this.encrypteData = encrypteData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }
}
