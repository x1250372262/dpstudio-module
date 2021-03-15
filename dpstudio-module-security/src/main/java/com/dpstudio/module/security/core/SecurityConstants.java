package com.dpstudio.module.security.core;

/**
 * @Author: mengxiang.
 * @Date: 2020/10/19.
 * @Time: 10:27 上午.
 * @Description:
 */
public interface SecurityConstants {

    Integer BOOL_TRUE = 1;
    Integer BOOL_FALSE = 0;

    //权限客户端名称
    String PERMISSION_CLIENT_NAME = "security";

    /**
     * 日志类型
     */
    Integer ADMIN_LOG_TYPE = 0;
    String ADMIN_LOG_ACTION = "登录";

    String DEFAULT_ADMIN_USER_NAME = "admin";
    String DEFAULT_ADMIN_PASSWORD = "admin";
    String DEFAULT_ADMIN_REAL_NAME = "管理员";

    //客户端标题
    String DEFAULT_CLIENT_TITLE = "隽铖后台管理系统";

    //jwt中adminid的key
    String JWT_ADMIN_ID_KEY = "uid";
    //jwt缓存名称
    String JWT_CACHE_NAME = "jwtCache";
    //jwt缓存名称
    String JWT_CACHE_ADIN_NAME = "jwtCacheByAdminId";


}
