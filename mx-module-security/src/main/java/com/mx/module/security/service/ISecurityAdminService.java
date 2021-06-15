package com.mx.module.security.service;

import com.mx.dev.bean.PageBean;
import com.mx.dev.core.R;
import com.mx.module.security.dto.SecurityAdminDTO;
import com.mx.module.security.vo.detail.SecurityAdminDetailVO;
import com.mx.module.security.vo.list.SecurityAdminListVO;
import net.ymate.platform.core.persistence.IResultSet;

public interface ISecurityAdminService {

    /**
     * 模块启动初始化表
     *
     * @param clientName
     * @return
     * @throws Exception
     */
    R init(String clientName) throws Exception;

    /**
     * 管理员登陆
     *
     * @param userName
     * @param password
     * @param clientName
     * @return
     * @throws Exception
     */
    R login(String userName, String password, String clientName) throws Exception;

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
     * @param securityAdminDTO
     * @return
     * @throws Exception
     */
    R updateInfo(SecurityAdminDTO securityAdminDTO) throws Exception;

    /**
     * 管理员列表
     *
     * @param userName
     * @param pageBean
     * @return
     * @throws Exception
     */
    IResultSet<SecurityAdminListVO> list(String userName, String realName, Integer disableStatus, PageBean pageBean) throws Exception;

    /**
     * 添加管理员
     *
     * @param securityAdminDTO
     * @return
     * @throws Exception
     */
    R create(SecurityAdminDTO securityAdminDTO, String password) throws Exception;

    /**
     * 添加管理员
     *
     * @param userName
     * @param realName
     * @param mobile
     * @param password
     * @param clientName
     * @return
     * @throws Exception
     */
    R create(String userName, String realName, String mobile, String password, String clientName) throws Exception;

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