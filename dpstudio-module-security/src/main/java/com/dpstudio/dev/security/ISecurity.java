/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dpstudio.dev.security;

import com.dpstudio.dev.security.bean.GroupBean;
import com.dpstudio.dev.security.bean.MenuBean;
import com.dpstudio.dev.security.bean.PermissionBean;
import net.ymate.platform.core.IApplication;
import net.ymate.platform.core.beans.annotation.Ignored;
import net.ymate.platform.core.support.IDestroyable;
import net.ymate.platform.core.support.IInitialization;

import java.util.List;

/**
 * ISecurity generated By ModuleMojo on 2020/06/30 17:49
 *
 * @author YMP (https://www.ymate.net/)
 */
@Ignored
public interface ISecurity extends IInitialization<IApplication>, IDestroyable {

    /**
     * 权限key
     */
    enum CacheKey {

        /**
         * 权限组key
         */
        GROUP_CACHE_KEY,
        /**
         * 权限key
         */
        PERMISSIONS_CACHE_KEY
    }

    String MODULE_NAME = "dpstudio.security";

    /**
     * 获取所属应用容器
     *
     * @return 返回所属应用容器实例
     */
    IApplication getOwner();

    /**
     * 获取配置
     *
     * @return 返回配置对象
     */
    ISecurityConfig getConfig();


    /**
     * 获取组列表
     *
     * @return 获取组列表
     */
    List<GroupBean> groupList();

    /**
     * 获取组列表
     *
     * @return
     */
    List<GroupBean> groupList(String level);

    /**
     * 获取权限列表
     * @param groupId
     * @return 获取权限列表
     */
    List<PermissionBean> permissionList(String groupId);

    /**
     * 获取权限列表
     *
     * @return
     */
    List<PermissionBean> permissionList();

    /**
     * 根据code获取
     * @param permissionBeans
     * @param code
     * @return
     */
    PermissionBean findByCode(List<PermissionBean> permissionBeans,String code);

    /**
     * 菜单列表
     *
     * @return 菜单列表
     */
    List<MenuBean> menuList();

    /**
     * 带权限的菜单
     *
     * @return 带权限的菜单
     */
    List<MenuBean> permissionMenu();

    /**
     * 带权限的菜单
     * @param isFounder 是否总管理
     * @param userPermissions 拥有的权限
     * @return 带权限的菜单
     */
    List<MenuBean> permissionMenu(boolean isFounder,List<String> userPermissions);
}
