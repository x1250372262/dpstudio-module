package com.dpstudio.module.security.dao;

import com.dpstudio.module.security.model.SecurityRole;
import net.ymate.platform.core.persistence.IResultSet;

import java.util.List;

public interface ISecurityRoleDao {

    /**
     * 根据id查询
     *
     * @param id
     * @param fields
     * @return
     * @throws Exception
     */
    SecurityRole findById(String id, String... fields) throws Exception;

    /**
     * 根据角色名称查询
     *
     * @param name
     * @param fields
     * @return
     * @throws Exception
     */
    SecurityRole findByName(String name, String... fields) throws Exception;

    /**
     * 查询修改时名称是否重复
     *
     * @param name
     * @param id
     * @param fields
     * @return
     * @throws Exception
     */
    SecurityRole findByNameNotId(String name, String id, String... fields) throws Exception;

    /**
     * 添加角色
     *
     * @param securityRole
     * @return
     * @throws Exception
     */
    SecurityRole create(SecurityRole securityRole) throws Exception;

    /**
     * 修改角色
     *
     * @param securityRole
     * @param fields
     * @return
     * @throws Exception
     */
    SecurityRole update(SecurityRole securityRole, String... fields) throws Exception;

    /**
     * 删除角色
     *
     * @param list
     * @throws Exception
     */
    void delete(List<SecurityRole> list) throws Exception;

    /**
     * 角色列表
     *
     * @param name
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    IResultSet<SecurityRole> findAll(String name, Integer page, Integer pageSize) throws Exception;

}
