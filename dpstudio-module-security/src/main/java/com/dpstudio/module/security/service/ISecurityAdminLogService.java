package com.dpstudio.module.security.service;

import com.dpstudio.dev.core.R;
import com.dpstudio.module.security.vo.SecurityAdminLogListVO;
import net.ymate.platform.persistence.IResultSet;

/**
 * @Author: 刘玉奇.
 * @Date: 2020/10/16.
 * @Time: 22:02.
 * @Description:
 */
public interface ISecurityAdminLogService {

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
    IResultSet<SecurityAdminLogListVO> findAll(String adminId, String content, Long startTime, Long endTime, int page, int pageSize) throws Exception;

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
     * @param userName
     * @return
     * @throws Exception
     */
    R create(String adminId, String userName) throws Exception;

}
