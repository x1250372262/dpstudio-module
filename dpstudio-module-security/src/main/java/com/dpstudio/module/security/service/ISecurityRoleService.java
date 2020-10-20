package com.dpstudio.module.security.service;

import com.dpstudio.dev.core.R;
import com.dpstudio.module.security.vo.SecurityRoleDetailVO;
import com.dpstudio.module.security.vo.SecurityRoleListVO;
import com.dpstudio.module.security.vo.SecurityRoleOPVO;
import com.dpstudio.module.security.vo.SecurityRoleSelectVO;
import net.ymate.platform.persistence.IResultSet;

import java.util.List;

/**
 * @Author: 刘玉奇.
 * @Date: 2020/10/15.
 * @Time: 15:43.
 * @Description:角色管理
 */
public interface ISecurityRoleService {

    /**
     * 角色列表
     *
     * @param name
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    IResultSet<SecurityRoleListVO> list(String name, int page, int pageSize) throws Exception;

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
     * @param securityRoleOPVO
     * @return
     * @throws Exception
     */
    R create(SecurityRoleOPVO securityRoleOPVO) throws Exception;

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
     * @param securityRoleOPVO
     * @return
     * @throws Exception
     */
    R update(String id, Long lastModifyTime, SecurityRoleOPVO securityRoleOPVO) throws Exception;

    /**
     * 删除角色
     *
     * @param ids
     * @return
     * @throws Exception
     */
    R delete(String[] ids) throws Exception;

}
