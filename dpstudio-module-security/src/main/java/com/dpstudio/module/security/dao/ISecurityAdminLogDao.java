package com.dpstudio.module.security.dao;

import com.dpstudio.dev.dto.PageDTO;
import com.dpstudio.module.security.model.SecurityAdminLog;
import net.ymate.platform.core.persistence.IResultSet;

public interface ISecurityAdminLogDao {


    /**
     * 日志列表
     *
     * @param adminId
     * @param clientName
     * @param content
     * @param startTime
     * @param endTime
     * @param pageDTO
     * @return
     * @throws Exception
     */
    IResultSet<SecurityAdminLog> findAll(String adminId,String clientName, String content, Long startTime, Long endTime, PageDTO pageDTO) throws Exception;

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
     * @param ids
     * @throws Exception
     */
    int[] delete(String[] ids) throws Exception;

}
