package com.mx.module.security.service;

import com.mx.dev.bean.PageBean;
import com.mx.dev.core.R;
import com.mx.module.security.dto.SecurityRoleDTO;
import com.mx.module.security.vo.detail.SecurityRoleDetailVO;
import com.mx.module.security.vo.list.SecurityRoleListVO;
import com.mx.module.security.vo.select.SecurityRoleSelectVO;
import net.ymate.platform.core.persistence.IResultSet;

import java.util.List;

public interface ISecurityRoleService {

    /**
     * 角色列表
     *
     * @param name
     * @param pageBean
     * @return
     * @throws Exception
     */
    IResultSet<SecurityRoleListVO> list(String name, PageBean pageBean) throws Exception;

    /**
     * 角色下拉选
     *
     * @return
     * @throws Exception
     */
    List<SecurityRoleSelectVO> select() throws Exception;

    /**
     * 添加角色
     *
     * @param securityRoleDTO
     * @return
     * @throws Exception
     */
    R create(SecurityRoleDTO securityRoleDTO) throws Exception;

    /**
     * 角色详情
     *
     * @param id
     * @param fields
     * @return
     * @throws Exception
     */
    SecurityRoleDetailVO detail(String id, String... fields) throws Exception;

    /**
     * 修改角色
     *
     * @param id
     * @param securityRoleDTO
     * @return
     * @throws Exception
     */
    R update(String id, Long lastModifyTime, SecurityRoleDTO securityRoleDTO) throws Exception;

    /**
     * 删除角色
     *
     * @param ids
     * @return
     * @throws Exception
     */
    R delete(String[] ids) throws Exception;

    /**
     * 权限详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    R permissionDetail(String id) throws Exception;

    /**
     * 设置权限
     *
     * @param id
     * @param permissions
     * @return
     * @throws Exception
     */
    R permissionSet(String id, String[] permissions) throws Exception;

}
