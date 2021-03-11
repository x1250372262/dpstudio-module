package com.dpstudio.module.security.service;

import com.dpstudio.dev.core.R;
import com.dpstudio.dev.dto.PageDTO;
import com.dpstudio.module.security.vo.list.SecurityAdminLogListVO;
import net.ymate.platform.core.persistence.IResultSet;

public interface ISecurityAdminLogService {

    /**
     * 日志列表
     *
     * @param adminId
     * @param content
     * @param startTime
     * @param endTime
     * @param pageDTO
     * @return
     * @throws Exception
     */
    IResultSet<SecurityAdminLogListVO> findAll(String adminId, String content, Long startTime, Long endTime, PageDTO pageDTO) throws Exception;

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
