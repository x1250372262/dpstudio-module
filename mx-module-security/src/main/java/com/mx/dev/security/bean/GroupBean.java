package com.mx.dev.security.bean;

/**
 * @Author: mengxiang.
 * @Date: 2019-01-16.
 * @Time: 11:48.
 * @Description:
 */
public class GroupBean {

    private String name;

    private String id;

    private String clientName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GroupBean(String name, String id, String clientName) {
        this.name = name;
        this.id = id;
        this.clientName = clientName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
