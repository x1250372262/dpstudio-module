package com.dpstudio.module.security.dao;


import com.dpstudio.module.security.model.SecurityAdmin;
import com.dpstudio.module.security.vo.SecurityAdminListVO;
import net.ymate.platform.persistence.IResultSet;

public interface ISecurityAdminDao {

    /**
     * 根据id查询
     *
     * @param id     数据id
     * @param fields 要查询哪些字段
     * @return 查询的数据
     * @throws Exception 查询异常
     */
    SecurityAdmin findById(String id, String... fields) throws Exception;

    /**
     * 根据用户名查询
     *
     * @param userName 用户名
     * @param fields   要查询哪些字段
     * @return 查询的数据
     * @throws Exception 查询异常
     */
    SecurityAdmin findByUserName(String userName, String... fields) throws Exception;

    /**
     * 修改
     *
     * @param securityAdmin
     * @param fields
     * @return
     * @throws Exception
     */
    SecurityAdmin update(SecurityAdmin securityAdmin, String... fields) throws Exception;

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
     * @param securityAdmin
     * @return
     * @throws Exception
     */
    SecurityAdmin create(SecurityAdmin securityAdmin) throws Exception;

}
