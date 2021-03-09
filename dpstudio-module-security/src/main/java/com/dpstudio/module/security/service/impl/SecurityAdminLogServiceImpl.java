package com.dpstudio.module.security.service.impl;

import com.dpstudio.dev.core.R;
import com.dpstudio.dev.utils.BeanUtils;
import com.dpstudio.dev.utils.ResultSetUtils;
import com.dpstudio.module.security.SecurityCache;
import com.dpstudio.module.security.core.SecurityConstants;
import com.dpstudio.module.security.dao.ISecurityAdminLogDao;
import com.dpstudio.module.security.model.SecurityAdminLog;
import com.dpstudio.module.security.service.ISecurityAdminLogService;
import com.dpstudio.module.security.vo.list.SecurityAdminLogListVO;
import net.ymate.platform.commons.util.DateTimeUtils;
import net.ymate.platform.commons.util.UUIDUtils;
import net.ymate.platform.core.beans.annotation.Bean;
import net.ymate.platform.core.beans.annotation.Inject;
import net.ymate.platform.core.persistence.IResultSet;
import net.ymate.platform.webmvc.context.WebContext;
import net.ymate.platform.webmvc.util.WebUtils;

import java.util.ArrayList;
import java.util.List;

@Bean
public class SecurityAdminLogServiceImpl implements ISecurityAdminLogService {

    @Inject
    private ISecurityAdminLogDao iSecurityAdminLogDao;

    @Override
    public IResultSet<SecurityAdminLogListVO> findAll(String adminId, String content, Long startTime, Long endTime, Integer page, Integer pageSize) throws Exception {
        IResultSet<SecurityAdminLog> adminLogResultSet = iSecurityAdminLogDao.findAll(adminId, content, startTime, endTime, page, pageSize);
        return ResultSetUtils.copy(adminLogResultSet, SecurityAdminLogListVO::new);
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
                .lastModifyUser(SecurityCache.userId())
                .createUser(SecurityCache.userId())
                .content(userName + "在" + DateTimeUtils.formatTime(DateTimeUtils.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "登录了系统，IP是" + WebUtils.getRemoteAddress(WebContext.getRequest()))
                .build();
        securityAdminLog = iSecurityAdminLogDao.create(securityAdminLog);
        return R.result(securityAdminLog);
    }
}
