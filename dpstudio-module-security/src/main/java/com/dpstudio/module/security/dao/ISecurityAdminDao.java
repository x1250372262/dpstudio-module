package com.dpstudio.module.security.dao;


import com.dpstudio.dev.dto.PageDTO;
import com.dpstudio.module.security.model.SecurityAdmin;
import com.dpstudio.module.security.vo.list.SecurityAdminListVO;
import net.ymate.platform.core.persistence.IResultSet;
import net.ymate.platform.persistence.jdbc.IDBLocker;

import java.util.List;

public interface ISecurityAdminDao {

    /**
     * 根据id查询
     *
     * @param id     数据id
     * @param fields 要查询哪些字段
     * @return 查询的数据
     * @throws Exception 查询异常
     */
    SecurityAdmin findById(String id, IDBLocker idbLocker, String... fields) throws Exception;

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
     * 修改
     *
     * @param securityAdminList
     * @param fields
     * @return
     * @throws Exception
     */
    List<SecurityAdmin> updateAll(List<SecurityAdmin> securityAdminList, String... fields) throws Exception;

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
     * @param securityAdmin
     * @return
     * @throws Exception
     */
    SecurityAdmin create(SecurityAdmin securityAdmin) throws Exception;

    /**
     * 根据ids查询所有
     *
     * @param params
     * @param idbLocker
     * @return
     * @throws Exception
     */
    IResultSet<SecurityAdmin> findAllByIds(List<String> params, IDBLocker idbLocker) throws Exception;

    /**
     * 删除
     *
     * @param ids
     * @throws Exception
     */
    int[] delete(String[] ids) throws Exception;

}
