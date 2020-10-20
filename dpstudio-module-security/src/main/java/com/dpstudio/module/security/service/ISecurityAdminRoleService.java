package com.dpstudio.module.security.service;

import com.dpstudio.dev.core.R;
import com.dpstudio.module.security.vo.SecurityAdminRoleListVO;
import com.dpstudio.module.security.vo.SecurityAdminRoleOPVO;
import net.ymate.platform.persistence.IResultSet;

/**
 * @Author: 刘玉奇.
 * @Date: 2020/10/15.
 * @Time: 22:30.
 * @Description:
 */
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
    IResultSet<SecurityAdminRoleListVO> list(String adminId, int page, int pageSize) throws Exception;

    /**
     * 添加角色
     *
     * @param adminRoleOPVO
     * @return
     * @throws Exception
     */
    R create(SecurityAdminRoleOPVO adminRoleOPVO) throws Exception;

    /**
     * 删除角色
     *
     * @param ids
     * @return
     * @throws Exception
     */
    R delete(String[] ids) throws Exception;

}
