package com.mx.module.security.service;

import com.mx.dev.core.R;
import com.mx.dev.dto.PageDTO;
import com.mx.module.security.dto.SecurityAdminRoleDTO;
import com.mx.module.security.vo.list.SecurityAdminRoleListVO;
import net.ymate.platform.core.persistence.IResultSet;

public interface ISecurityAdminRoleService {

    /**
     * 角色列表
     *
     * @param adminId
     * @param pageDTO
     * @return
     * @throws Exception
     */
    IResultSet<SecurityAdminRoleListVO> list(String adminId, PageDTO pageDTO) throws Exception;

    /**
     * 添加角色
     *
     * @param securityAdminRoleDTO
     * @return
     * @throws Exception
     */
    R create(SecurityAdminRoleDTO securityAdminRoleDTO) throws Exception;

    /**
     * 删除角色
     *
     * @param ids
     * @return
     * @throws Exception
     */
    R delete(String[] ids) throws Exception;

}
