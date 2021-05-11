package com.mx.dev.security.init;

import cn.hutool.core.util.ClassUtil;
import com.mx.dev.security.ISecurity;
import com.mx.dev.security.ISecurityConfig;
import com.mx.dev.security.annotation.Group;
import com.mx.dev.security.annotation.Permission;
import com.mx.dev.security.annotation.Security;
import com.mx.dev.security.bean.GroupBean;
import com.mx.dev.security.bean.PermissionBean;
import com.mx.module.security.core.SecurityConstants;
import com.mx.module.security.core.SecurityPermission;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @Author: mengxiang.
 * @Date: 2019-01-17.
 * @Time: 08:24.
 * @Description:
 */
public class PermissionMeta {


    private static final Log LOG = LogFactory.getLog(PermissionMeta.class);

    /**
     * 权限组
     */
    private static final Map<String, List<GroupBean>> GROUP_CACHES;

    /**
     * 权限
     */
    private static final Map<String, List<PermissionBean>> PERMISSIONS_CACHES;

    static {
        GROUP_CACHES = new ConcurrentHashMap<>();
        PERMISSIONS_CACHES = new ConcurrentHashMap<>();
    }

    private PermissionMeta() {
    }

    /**
     * 创建权限列表
     */
    public static void init(ISecurityConfig securityConfig) {
        create(securityConfig);
        LOG.info("权限列表收集成功");
    }

    /**
     * 销毁权限列表
     */
    public static void destroy() {
        GROUP_CACHES.clear();
        PERMISSIONS_CACHES.clear();
        LOG.info("权限列表销毁成功");
    }


    /**
     * 获取权限列表
     */
    public static List<PermissionBean> getPermissions(String groupId, String clientName) {
        if (StringUtils.isNotBlank(groupId)) {
            return PERMISSIONS_CACHES.get(ISecurity.CacheKey.PERMISSIONS_CACHE_KEY.name())
                    .stream()
                    .filter(permissionBean -> groupId.equals(permissionBean.getGroupId()))
                    .filter(permissionBean -> clientName.equals(permissionBean.getClientName()))
                    .collect(Collectors.toList());
        }
        return PERMISSIONS_CACHES.get(ISecurity.CacheKey.PERMISSIONS_CACHE_KEY.name());
    }


    /**
     * 根据code获取权限
     */
    public static PermissionBean findByCode(List<PermissionBean> permissionBeans, String clientName, String code) {
        if (permissionBeans == null) {
            permissionBeans = getPermissions(null, clientName);
        }
        if (StringUtils.isBlank(code)) {
            return null;
        }
        if (permissionBeans == null) {
            return null;
        }
        return permissionBeans.stream().filter(permissionBean -> code.equals(permissionBean.getCode())).findFirst().get();
    }


    /**
     * 获取权限组列表
     *
     * @return
     */
    public static List<GroupBean> getGroups(String clientName) {
        if (StringUtils.isNotBlank(clientName)) {
            return GROUP_CACHES.get(ISecurity.CacheKey.GROUP_CACHE_KEY.name())
                    .stream().filter(groupBean -> clientName.equals(groupBean.getClientName())).collect(Collectors.toList());
        }
        return GROUP_CACHES.get(ISecurity.CacheKey.GROUP_CACHE_KEY.name());
    }

    /**
     * 获取权限信息
     *
     * @return
     */
    private static void create(ISecurityConfig securityConfig) {
        String packageName = securityConfig.packageName();
        Set<Class<?>> classesList = new HashSet<>();
        if (StringUtils.isBlank(packageName)) {
            classesList = ClassUtil.scanPackageByAnnotation(packageName, Security.class);
        } else {
            if (!packageName.contains("|")) {
                classesList = ClassUtil.scanPackageByAnnotation(packageName, Security.class);
            } else {
                String[] packageNameArray = packageName.split("\\|");
                for (String packName : packageNameArray) {
                    Set<Class<?>> packClassesList = ClassUtil.scanPackageByAnnotation(packName, Security.class);
                    if (packClassesList != null && !packClassesList.isEmpty()) {
                        classesList.addAll(packClassesList);
                    }
                }
            }
        }
        if (classesList == null) {
            return;
        }
        List<PermissionBean> permissionBeans = new ArrayList<>();
        List<GroupBean> groupBeans = new ArrayList<>();
        for (Class<?> classes : classesList) {
            //得到该类下面的所有方法
            Method[] methods = classes.getDeclaredMethods();
            for (Method method : methods) {
                //得到该类下面的Group注解
                Group group = method.getAnnotation(Group.class);
                if (null == group) {
                    continue;
                }
                Permission[] permissions = group.permissions();
                if (permissions.length <= 0) {
                    continue;
                }
                for (Permission permission : permissions) {
//添加权限列表
                    Optional<PermissionBean> permissionBean = permissionBeans.stream().filter(p -> p.getCode().equals(permission.code())).findFirst();
                    if (!permissionBean.isPresent()) {
                        permissionBeans.add(new PermissionBean(permission.name(), permission.code(), permission.groupId(), permission.groupName(), group.clientName()));
                    }
                    //添加组列表
                    Optional<GroupBean> groupBean = groupBeans.stream().filter(gb -> gb.getId().equals(permission.groupId())).findFirst();
                    if (!groupBean.isPresent()) {
                        groupBeans.add(new GroupBean(permission.groupName(), permission.groupId(), group.clientName()));
                    }
                }
            }
        }
        permissionBeans.add(new PermissionBean(SecurityPermission.PERMISSION_NAME_SECURITY,
                SecurityPermission.PERMISSION_CODE_SECURITY,
                SecurityPermission.GROUP_ID_SECURITY,
                SecurityPermission.GROUP_NAME_SECURITY,
                SecurityConstants.PERMISSION_CLIENT_NAME));
        groupBeans.add(new GroupBean(SecurityPermission.GROUP_NAME_SECURITY, SecurityPermission.GROUP_ID_SECURITY, SecurityConstants.PERMISSION_CLIENT_NAME));
        GROUP_CACHES.put(ISecurity.CacheKey.GROUP_CACHE_KEY.name(), groupBeans);
        PERMISSIONS_CACHES.put(ISecurity.CacheKey.PERMISSIONS_CACHE_KEY.name(), permissionBeans);
    }
}
