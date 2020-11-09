package com.dpstudio.module.security.service;

import com.dpstudio.dev.core.R;
import com.dpstudio.module.security.vo.detail.SecurityRoleDetailVO;
import com.dpstudio.module.security.vo.detail.SecurityRoleListVO;
import com.dpstudio.module.security.vo.op.SecurityRoleVO;
import com.dpstudio.module.security.vo.select.SecurityRoleSelectVO;
import net.ymate.platform.core.persistence.IResultSet;

import java.util.List;

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
    IResultSet<SecurityRoleListVO> list(String name, Integer page, Integer pageSize) throws Exception;

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
     * @param securityRoleVO
     * @return
     * @throws Exception
     */
    R create(SecurityRoleVO securityRoleVO) throws Exception;

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
     * @param securityRoleVO
     * @return
     * @throws Exception
     */
    R update(String id, Long lastModifyTime, SecurityRoleVO securityRoleVO) throws Exception;

    /**
     * 删除角色
     *
     * @param ids
     * @return
     * @throws Exception
     */
    R delete(String[] ids) throws Exception;

}
