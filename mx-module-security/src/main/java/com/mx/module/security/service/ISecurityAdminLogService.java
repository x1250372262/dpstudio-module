package com.mx.module.security.service;

import com.mx.dev.bean.PageBean;
import com.mx.dev.core.R;
import com.mx.module.security.vo.list.SecurityAdminLogListVO;
import net.ymate.platform.core.persistence.IResultSet;

public interface ISecurityAdminLogService {

    /**
     * 日志列表
     *
     * @param adminId
     * @param content
     * @param startTime
     * @param endTime
     * @param pageBean
     * @return
     * @throws Exception
     */
    IResultSet<SecurityAdminLogListVO> list(String adminId, String content, Long startTime, Long endTime, PageBean pageBean) throws Exception;

    /**
     * 删除日志
     *
     * @param ids
     * @return
     * @throws Exception
     */
    R delete(String[] ids) throws Exception;

    /**
     * 添加登录日志
     *
     * @param adminId
     * @param clientName
     * @param userName
     * @return
     * @throws Exception
     */
    R create(String adminId, String clientName, String userName) throws Exception;

}
