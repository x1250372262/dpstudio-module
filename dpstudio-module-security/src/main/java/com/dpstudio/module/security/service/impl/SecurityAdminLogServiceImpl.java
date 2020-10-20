package com.dpstudio.module.security.service.impl;

import com.dpstudio.dev.core.R;
import com.dpstudio.module.security.core.CommonMethod;
import com.dpstudio.module.security.core.ResultSetUtils;
import com.dpstudio.module.security.model.SecurityAdminLog;
import com.dpstudio.module.security.core.SecurityConstants;
import com.dpstudio.module.security.dao.ISecurityAdminLogDao;
import com.dpstudio.module.security.service.ISecurityAdminLogService;
import com.dpstudio.module.security.vo.SecurityAdminLogListVO;
import net.ymate.platform.core.beans.annotation.Bean;
import net.ymate.platform.core.beans.annotation.Inject;
import net.ymate.platform.core.util.DateTimeUtils;
import net.ymate.platform.core.util.UUIDUtils;
import net.ymate.platform.persistence.IResultSet;
import net.ymate.platform.webmvc.context.WebContext;
import net.ymate.platform.webmvc.util.WebUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 刘玉奇.
 * @Date: 2020/10/16.
 * @Time: 22:02.
 * @Description:
 */
@Bean
public class SecurityAdminLogServiceImpl implements ISecurityAdminLogService {

    @Inject
    private ISecurityAdminLogDao iSecurityAdminLogDao;

    @Override
    public IResultSet<SecurityAdminLogListVO> findAll(String adminId, String content, Long startTime, Long endTime, int page, int pageSize) throws Exception {
        IResultSet<SecurityAdminLog> adminLogIResultSet = iSecurityAdminLogDao.findAll(adminId, content, startTime, endTime, page, pageSize);
        return ResultSetUtils.copy(adminLogIResultSet, SecurityAdminLogListVO::new);
    }

    @Override
    public R delete(String[] ids) throws Exception {
        List<SecurityAdminLog> list = new ArrayList<>();
        for (String id : ids) {
            list.add(SecurityAdminLog.builder().id(id).build());
        }
        iSecurityAdminLogDao.delete(list);
        return R.ok();
    }

    @Override
    public R create(String adminId, String userName) throws Exception {
        SecurityAdminLog securityAdminLog = SecurityAdminLog.builder()
                .id(UUIDUtils.UUID())
                .type(SecurityConstants.ADMIN_LOG_TYPE)
                .action(SecurityConstants.ADMIN_LOG_ACTION)
                .adminId(adminId)
                .createTime(DateTimeUtils.currentTimeMillis())
                .lastModifyTime(DateTimeUtils.currentTimeMillis())
                .lastModifyUser(CommonMethod.userId())
                .createUser(CommonMethod.userId())
                .content(userName + "在" + DateTimeUtils.formatTime(DateTimeUtils.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "登录了系统，IP是" + WebUtils.getRemoteAddr(WebContext.getRequest()))
                .build();
        securityAdminLog = iSecurityAdminLogDao.create(securityAdminLog);
        return R.result(securityAdminLog);
    }
}
