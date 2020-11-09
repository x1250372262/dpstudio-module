package com.dpstudio.module.security.dao;

import com.dpstudio.module.security.model.SecurityAdminLog;
import net.ymate.platform.core.persistence.IResultSet;

import java.util.List;

public interface ISecurityAdminLogDao {


    /**
     * 日志列表
     *
     * @param adminId
     * @param content
     * @param startTime
     * @param endTime
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    IResultSet<SecurityAdminLog> findAll(String adminId, String content, Long startTime, Long endTime, Integer page, Integer pageSize) throws Exception;

    /**
     * 添加日志
     *
     * @param securityAdminLog
     * @return
     * @throws Exception
     */
    SecurityAdminLog create(SecurityAdminLog securityAdminLog) throws Exception;

    /**
     * 删除
     *
     * @param list
     * @throws Exception
     */
    void delete(List<SecurityAdminLog> list) throws Exception;

}
