package com.dpstudio.module.security.service;

import com.dpstudio.dev.core.R;
import com.dpstudio.module.security.vo.list.SecurityAdminRoleListVO;
import com.dpstudio.module.security.vo.op.SecurityAdminRoleVO;
import net.ymate.platform.core.persistence.IResultSet;

public interface ISecurityAdminRoleService {

    /**
     * 角色列表
     *
     * @param adminId
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    IResultSet<SecurityAdminRoleListVO> list(String adminId, Integer page, Integer pageSize) throws Exception;

    /**
     * 添加角色
     *
     * @param adminRoleVO
     * @return
     * @throws Exception
     */
    R create(SecurityAdminRoleVO adminRoleVO) throws Exception;

    /**
     * 删除角色
     *
     * @param ids
     * @return
     * @throws Exception
     */
    R delete(String[] ids) throws Exception;

}
