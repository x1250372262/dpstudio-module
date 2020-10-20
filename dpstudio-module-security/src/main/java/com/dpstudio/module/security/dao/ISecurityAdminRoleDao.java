package com.dpstudio.module.security.dao;

import com.dpstudio.module.security.model.SecurityAdminRole;
import com.dpstudio.module.security.vo.SecurityAdminRoleListVO;
import net.ymate.platform.persistence.IResultSet;
import net.ymate.platform.persistence.Params;

import java.util.List;

/**
 * @Author: 刘玉奇.
 * @Date: 2020/10/15.
 * @Time: 22:29.
 * @Description:
 */
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
    IResultSet<SecurityAdminRoleListVO> findAll(String adminId, int page, int pageSize) throws Exception;

    /**
     * 管理员角色集合
     *
     * @param adminIds
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    IResultSet<SecurityAdminRoleListVO> findByAdminIds(Params adminIds, int page, int pageSize) throws Exception;

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
