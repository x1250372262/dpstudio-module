package com.dpstudio.module.security.core;


/**
 * 系统设置错误码 62xxx
 */
public enum Code {

    /**
     * 管理员错误码
     */

    SECURITY_ADMIN_PASSWORD_NO_SAME(65000, "两次密码不一致"),
    SECURITY_ADMIN_NOT_EXIST(65001, "用户不存在"),
    SECURITY_ADMIN_PASSWORD_OLD_ERROR(65002, "原密码错误"),
    SECURITY_ADMIN_USERNAME_NOT_EXIST(65003, "用户名不存在"),
    SECURITY_ADMIN_USERNAME_OR_PASSWORD_ERROR(65004, "用户名或密码错误"),
    SECURITY_ADMIN_DISABLED(65005, "帐号被禁用"),
    SECURITY_ADMIN_EXIST(65006, "用户已存在"),
    SECURITY_IS_LOGIN(65007, "账号已被登录"),
    SECURITY_ADMIN_IS_LOCKED(65008, "账号已被冻结"),
    SECURITY_ADMIN_OUT(65009, "该帐号已在别处登录"),
    SECURITY_ADMIN_PASSWORD_EMPTY(65010, "密码不能为空"),
    SECURITY_ADMIN_CLENT_NAME_EMPTY(65011, "客户端名称不能为空"),
    SECURITY_ADMIN_USER_NAME_EMPTY(65012, "用户名不能为空"),

    SECURITY_SYSTEM_ROLE_NAME_EXIST(65100, "角色名称已存在"),
    SECURITY_SYSTEM_ADMIN_ROLE_EXIST(65101, "管理员角色已存在"),
    SECURITY_ADMIN_INVALID_OR_TIMEOUT(65102, "用户未授权登录或会话已过期，请重新登录");


    /**
     * 产品
     */

    private int code;
    private String msg;

    Code(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}