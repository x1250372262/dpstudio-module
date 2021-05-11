package com.mx.dev.security.bean;

/**
 * @Author: mengxiang.
 * @Date: 2019-01-16.
 * @Time: 11:48.
 * @Description:
 */
public class PermissionBean {

    private String name;

    private String code;

    private String groupId;

    private String groupName;

    private String clientName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public PermissionBean(String name, String code, String groupId, String groupName, String clientName) {
        this.name = name;
        this.code = code;
        this.groupId = groupId;
        this.groupName = groupName;
        this.clientName = clientName;
    }

}
