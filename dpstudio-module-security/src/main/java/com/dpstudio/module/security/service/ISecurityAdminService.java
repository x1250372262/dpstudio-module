package com.dpstudio.module.security.service;

import com.dpstudio.dev.core.R;
import com.dpstudio.dev.dto.PageDTO;
import com.dpstudio.module.security.vo.detail.SecurityAdminDetailVO;
import com.dpstudio.module.security.vo.list.SecurityAdminListVO;
import com.dpstudio.module.security.vo.op.SecurityAdminVO;
import net.ymate.platform.core.persistence.IResultSet;

public interface ISecurityAdminService {

    /**
     * 管理员登陆
     *
     * @param userName
     * @param password
     * @return
     * @throws Exception
     */
    R login(String userName, String password) throws Exception;

    /**
     * 修改密码
     *
     * @param pwd
     * @param newPwd
     * @param reNewPwd
     * @return
     */
    R updatePassword(String pwd, String newPwd, String reNewPwd) throws Exception;

    /**
     * 根据id查询
     *
     * @param id
     * @return
     * @throws Exception
     */
    SecurityAdminDetailVO detail(String id) throws Exception;

    /**
     * 更新用户
     *
     * @param securityAdminVO
     * @return
     * @throws Exception
     */
    R updateInfo(SecurityAdminVO securityAdminVO) throws Exception;

    /**
     * 管理员列表
     *
     * @param userName
     * @param pageDTO
     * @return
     * @throws Exception
     */
    IResultSet<SecurityAdminListVO> list(String userName, String realName, Integer disableStatus, PageDTO pageDTO) throws Exception;

    /**
     * 添加管理员
     *
     * @param securityAdminVO
     * @return
     * @throws Exception
     */
    R create(SecurityAdminVO securityAdminVO, String password) throws Exception;

    /**
     * 删除/禁用管理员
     *
     * @param ids
     * @param disableStatus
     * @return
     * @throws Exception
     */
    R disabled(String[] ids, Integer disableStatus) throws Exception;

    /**
     * 重置密码
     *
     * @param id
     * @return
     * @throws Exception
     */
    R resetPassword(String id) throws Exception;

    /**
     * 解除冻结
     *
     * @param id
     * @return
     * @throws Exception
     */
    R unlock(String id) throws Exception;

    /**
     * 删除
     *
     * @param ids
     * @return
     * @throws Exception
     */
    R delete(String[] ids) throws Exception;

}