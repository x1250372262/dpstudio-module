package com.dpstudio.module.security.service.impl;

import com.dpstudio.dev.core.R;
import com.dpstudio.dev.dto.PageDTO;
import com.dpstudio.dev.utils.ResultSetUtils;
import com.dpstudio.module.security.SecurityCache;
import com.dpstudio.module.security.core.SecurityConstants;
import com.dpstudio.module.security.dao.ISecurityAdminLogDao;
import com.dpstudio.module.security.model.SecurityAdmin;
import com.dpstudio.module.security.model.SecurityAdminLog;
import com.dpstudio.module.security.service.ISecurityAdminLogService;
import com.dpstudio.module.security.vo.detail.SecuritySettingDetailVO;
import com.dpstudio.module.security.vo.list.SecurityAdminLogListVO;
import net.ymate.platform.commons.util.DateTimeUtils;
import net.ymate.platform.commons.util.UUIDUtils;
import net.ymate.platform.core.beans.annotation.Bean;
import net.ymate.platform.core.beans.annotation.Inject;
import net.ymate.platform.core.persistence.IResultSet;
import net.ymate.platform.core.persistence.impl.DefaultResultSet;
import net.ymate.platform.persistence.jdbc.base.impl.BeanResultSetHandler;
import net.ymate.platform.webmvc.context.WebContext;
import net.ymate.platform.webmvc.util.WebUtils;

import java.util.ArrayList;

@Bean
public class SecurityAdminLogServiceImpl implements ISecurityAdminLogService {

    @Inject
    private ISecurityAdminLogDao iSecurityAdminLogDao;

    @Override
    public IResultSet<SecurityAdminLogListVO> list(String adminId, String content, Long startTime, Long endTime, PageDTO pageDTO) throws Exception {
        SecurityAdmin loginAdmin = SecurityCache.AdminCache.getPara(SecurityCache.userId());
        if (loginAdmin == null) {
           return new DefaultResultSet<>(new ArrayList<>());
        }
        IResultSet<SecurityAdminLog> adminLogResultSet = iSecurityAdminLogDao.findAll(adminId,loginAdmin.getClientName(), content, startTime, endTime, pageDTO);
        return ResultSetUtils.copy(adminLogResultSet, SecurityAdminLogListVO::new);
    }

    @Override
    public R delete(String[] ids) throws Exception {
        int[] result = iSecurityAdminLogDao.delete(ids);
        return R.result(result);
    }

    @Override
    public R create(String adminId,String clientName, String userName) throws Exception {
        SecurityAdminLog securityAdminLog = SecurityAdminLog.builder()
                .id(UUIDUtils.UUID())
                .clientName(clientName)
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
