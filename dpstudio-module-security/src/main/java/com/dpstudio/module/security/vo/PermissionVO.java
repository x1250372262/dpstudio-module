package com.dpstudio.module.security.vo;

import com.dpstudio.dev.security.bean.PermissionBean;

import java.util.List;

/**
 * @Author: mengxiang.
 * @create: 2021-03-15 14:11
 * @Description:
 */
public class PermissionVO {

    private String groupId;

    private String groupName;


    private List<PermissionBean> permissionBeans;

    public PermissionVO() {
    }

    public PermissionVO(String groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<PermissionBean> getPermissionBeans() {
        return permissionBeans;
    }

    public void setPermissionBeans(List<PermissionBean> permissionBeans) {
        this.permissionBeans = permissionBeans;
    }
}
