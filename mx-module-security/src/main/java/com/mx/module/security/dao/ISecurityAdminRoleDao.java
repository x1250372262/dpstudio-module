package com.mx.module.security.dao;

import com.mx.dev.bean.PageBean;
import com.mx.module.security.model.SecurityAdminRole;
import com.mx.module.security.vo.list.SecurityAdminRoleListVO;
import net.ymate.platform.core.persistence.IResultSet;
import net.ymate.platform.core.persistence.Params;

public interface ISecurityAdminRoleDao {

    /**
     * 角色列表
     *
     * @param adminId
     * @param pageBean
     * @return
     * @throws Exception
     */
    IResultSet<SecurityAdminRoleListVO> findAll(String adminId, PageBean pageBean) throws Exception;

    /**
     * 查询所有
     * @param adminId
     * @param fields
     * @return
     * @throws Exception
     */
    IResultSet<SecurityAdminRole> findAll(String adminId, String...fields) throws Exception;

    /**
     * 管理员角色集合
     *
     * @param adminIds
     * @param pageBean
     * @return
     * @throws Exception
     */
    IResultSet<SecurityAdminRoleListVO> findByAdminIds(Params adminIds, PageBean pageBean) throws Exception;

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
     * @param ids
     * @throws Exception
     */
    int[] delete(String[] ids) throws Exception;

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
