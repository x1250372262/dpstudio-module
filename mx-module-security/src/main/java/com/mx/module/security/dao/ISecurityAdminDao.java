package com.mx.module.security.dao;


import com.mx.dev.bean.PageBean;
import com.mx.module.security.model.SecurityAdmin;
import com.mx.module.security.vo.list.SecurityAdminListVO;
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
     * 根据用户名和客户端名称查询
     *
     * @param userName 用户名
     * @param fields   要查询哪些字段
     * @return 查询的数据
     * @throws Exception 查询异常
     */
    SecurityAdmin findByUserNameAndClientName(String userName, String clientName,String... fields) throws Exception;

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
     * 添加
     * @param securityAdminList
     * @return
     * @throws Exception
     */
    List<SecurityAdmin> createAll(List<SecurityAdmin> securityAdminList) throws Exception;

    /**
     * 根据客户端名称和是否总管理查询
     * @param clientName
     * @param founder
     * @return
     * @throws Exception
     */
    SecurityAdmin findByClientNameAndFounder(String clientName,Integer founder) throws Exception;

    /**
     * 管理员列表
     *
     * @param userName
     * @param pageBean
     * @return
     * @throws Exception
     */
    IResultSet<SecurityAdminListVO> list(String clientName,String userName, String realName, Integer disableStatus, PageBean pageBean) throws Exception;

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
