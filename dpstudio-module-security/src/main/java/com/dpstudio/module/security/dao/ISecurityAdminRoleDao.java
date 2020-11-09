package com.dpstudio.module.security.dao;

import com.dpstudio.module.security.model.SecurityAdminRole;
import com.dpstudio.module.security.vo.list.SecurityAdminRoleListVO;
import net.ymate.platform.core.persistence.IResultSet;
import net.ymate.platform.core.persistence.Params;

import java.util.List;

public interface ISecurityAdminRoleDao {

    /**
     * 角色列表
     *
     * @param adminId
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    IResultSet<SecurityAdminRoleListVO> findAll(String adminId, Integer page, Integer pageSize) throws Exception;

    /**
     * 管理员角色集合
     *
     * @param adminIds
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    IResultSet<SecurityAdminRoleListVO> findByAdminIds(Params adminIds, Integer page, Integer pageSize) throws Exception;

    /**
     * 添加角色
     *
     * @param securityAdminRole
     * @return
     * @throws Exception
     */
    SecurityAdminRole create(SecurityAdminRole securityAdminRole) throws Exception;

    /**
     * 删除角色
     *
     * @param list
     * @throws Exception
     */
    void delete(List<SecurityAdminRole> list) throws Exception;

    /**
     * 根据adminId和roleId查询
     *
     * @param roleId
     * @param adminId
     * @param fields
     * @return
     * @throws Exception
     */
    SecurityAdminRole findByRoleIdAndAdminId(String roleId, String adminId, String... fields) throws Exception;

}
