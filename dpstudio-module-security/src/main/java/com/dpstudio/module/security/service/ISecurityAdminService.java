package com.dpstudio.module.security.service;

import com.dpstudio.dev.core.R;
import com.dpstudio.module.security.vo.SecurityAdminDetailVO;
import com.dpstudio.module.security.vo.SecurityAdminListVO;
import com.dpstudio.module.security.vo.SecurityAdminOPVO;
import net.ymate.platform.persistence.IResultSet;

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
     * @param securityAdminOPVO
     * @return
     * @throws Exception
     */
    R updateInfo(SecurityAdminOPVO securityAdminOPVO) throws Exception;

    /**
     * 管理员列表
     *
     * @param userName
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    IResultSet<SecurityAdminListVO> list(String userName, String realName, Integer disableStatus, int page, int pageSize) throws Exception;

    /**
     * 添加管理员
     *
     * @param securityAdminOPVO
     * @return
     * @throws Exception
     */
    R create(SecurityAdminOPVO securityAdminOPVO,String password) throws Exception;

    /**
     * 删除/禁用管理员
     *
     * @param id
     * @param disableStatus
     * @return
     * @throws Exception
     */
    R disabled(String id, Integer disableStatus) throws Exception;

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

}