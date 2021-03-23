package com.dpstudio.module.security.core;

/**
 * @Author: mengxiang.
 * @create: 2021-03-15 11:24
 * @Description:
 */
public class SecurityPermission {

    /**
     * 安全模块
     */
    //groupId
    public final static String GROUP_ID_SECURITY = "security";
    //groupName
    public final static String GROUP_NAME_SECURITY = "安全模块";

    public final static String PERMISSION_NAME_SECURITY = "安全模块";
    public final static String PERMISSION_CODE_SECURITY = "SECURITY";

    /**
     * 管理员
     */
    //groupId
    public final static String GROUP_ID_ADMIN = "admin";
    //groupName
    public final static String GROUP_NAME_ADMIN = "管理员管理";

    public final static String PERMISSION_NAME_ADMIN_LIST = "管理员列表";
    public final static String PERMISSION_CODE_ADMIN_LIST = "SECURITY_ADMIN_LIST";

    public final static String PERMISSION_NAME_ADMIN_CREATE = "管理员添加";
    public final static String PERMISSION_CODE_ADMIN_CREATE = "SECURITY_ADMIN_CREATE";

    public final static String PERMISSION_NAME_ADMIN_DISABLED = "管理员禁用启用";
    public final static String PERMISSION_CODE_ADMIN_DISABLED = "SECURITY_ADMIN_DISABLED";

    public final static String PERMISSION_NAME_ADMIN_PASSWORD = "管理员重置密码";
    public final static String PERMISSION_CODE_ADMIN_PASSWORD = "SECURITY_ADMIN_PASSWORD";

    public final static String PERMISSION_NAME_ADMIN_UNLOCK = "管理员解锁";
    public final static String PERMISSION_CODE_ADMIN_UNLOCK = "SECURITY_ADMIN_UNLOCK";

    public final static String PERMISSION_NAME_ADMIN_DELETE = "管理员删除";
    public final static String PERMISSION_CODE_ADMIN_DELETE = "SECURITY_ADMIN_DELETE";

    public final static String PERMISSION_NAME_ADMIN_LOG_LIST = "管理员日志列表";
    public final static String PERMISSION_CODE_ADMIN_LOG_LIST = "SECURITY_ADMIN_LOG_LIST";

    public final static String PERMISSION_NAME_ADMIN_LOG_DELETE = "管理员日志删除";
    public final static String PERMISSION_CODE_ADMIN_LOG_DELETE = "SECURITY_ADMIN_LOG_DELETE";

    public final static String PERMISSION_NAME_ADMIN_ROLE_LIST = "管理员角色列表";
    public final static String PERMISSION_CODE_ADMIN_ROLE_LIST = "SECURITY_ADMIN_ROLE_LIST";

    public final static String PERMISSION_NAME_ADMIN_ROLE_CREATE = "管理员角色添加";
    public final static String PERMISSION_CODE_ADMIN_ROLE_CREATE = "SECURITY_ADMIN_ROLE_CREATE";

    public final static String PERMISSION_NAME_ADMIN_ROLE_DELETE = "管理员角色删除";
    public final static String PERMISSION_CODE_ADMIN_ROLE_DELETE = "SECURITY_ADMIN_ROLE_DELETE";



    /**
     * 安全设置
     */
    //groupId
    public final static String GROUP_ID_SETTING = "setting";
    //groupName
    public final static String GROUP_NAME_SETTING = "安全设置";

    public final static String PERMISSION_NAME_SETTING_DETAIL = "安全设置";
    public final static String PERMISSION_CODE_SETTING_DETAIL = "SECURITY_SETTING_DETAIL";

    public final static String PERMISSION_NAME_SETTING_UPDATE = "安全设置修改";
    public final static String PERMISSION_CODE_SETTING_UPDATE = "SECURITY_SETTING_UPDATE";


    /**
     * 安全设置
     */
    //groupId
    public final static String GROUP_ID_ROLE = "role";
    //groupName
    public final static String GROUP_NAME_ROLE = "角色管理";

    public final static String PERMISSION_NAME_ROLE_LIST = "角色列表";
    public final static String PERMISSION_CODE_ROLE_LIST = "SECURITY_ROLE_LIST";

    public final static String PERMISSION_NAME_ROLE_SELECT = "角色下拉选";
    public final static String PERMISSION_CODE_ROLE_SELECT = "SECURITY_ROLE_SELECT";

    public final static String PERMISSION_NAME_ROLE_CREATE = "角色添加";
    public final static String PERMISSION_CODE_ROLE_CREATE = "SECURITY_ROLE_CREATE";

    public final static String PERMISSION_NAME_ROLE_DETAIL = "角色详情";
    public final static String PERMISSION_CODE_ROLE_DETAIL = "SECURITY_ROLE_DETAIL";

    public final static String PERMISSION_NAME_ROLE_UPDATE = "角色修改";
    public final static String PERMISSION_CODE_ROLE_UPDATE = "SECURITY_ROLE_UPDATE";

    public final static String PERMISSION_NAME_ROLE_DELETE = "角色删除";
    public final static String PERMISSION_CODE_ROLE_DELETE = "SECURITY_ROLE_DELETE";


}
