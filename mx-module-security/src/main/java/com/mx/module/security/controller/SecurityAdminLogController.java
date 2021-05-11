package com.mx.module.security.controller;

import com.mx.dev.core.L;
import com.mx.dev.core.R;
import com.mx.dev.core.V;
import com.mx.dev.dto.PageDTO;
import com.mx.dev.security.annotation.Group;
import com.mx.dev.security.annotation.Permission;
import com.mx.dev.security.annotation.Security;
import com.mx.module.security.core.SecurityConstants;
import com.mx.module.security.core.SecurityPermission;
import com.mx.module.security.interCeptor.JwtCheckInterceptor;
import com.mx.module.security.model.SecurityAdminLog;
import com.mx.module.security.service.ISecurityAdminLogService;
import com.mx.module.security.vo.list.SecurityAdminLogListVO;
import net.ymate.platform.core.beans.annotation.Before;
import net.ymate.platform.core.beans.annotation.Inject;
import net.ymate.platform.core.persistence.IResultSet;
import net.ymate.platform.validation.validate.VRequired;
import net.ymate.platform.webmvc.annotation.Controller;
import net.ymate.platform.webmvc.annotation.ModelBind;
import net.ymate.platform.webmvc.annotation.RequestMapping;
import net.ymate.platform.webmvc.annotation.RequestParam;
import net.ymate.platform.webmvc.base.Type;
import net.ymate.platform.webmvc.view.IView;

@Controller
@RequestMapping("/admin/log")
@Before(JwtCheckInterceptor.class)
@Security
public class SecurityAdminLogController {

    @Inject
    private ISecurityAdminLogService iAdminLogService;

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
    @Group(clientName = SecurityConstants.PERMISSION_CLIENT_NAME,permissions = {@Permission(groupId = SecurityPermission.GROUP_ID_ADMIN,
            groupName = SecurityPermission.GROUP_NAME_ADMIN,
            name = SecurityPermission.PERMISSION_NAME_ADMIN_LOG_LIST,
            code = SecurityPermission.PERMISSION_CODE_ADMIN_LOG_LIST)})
    @RequestMapping(value = "/list", method = Type.HttpMethod.GET)
    public IView list(@RequestParam(value = SecurityAdminLog.FIELDS.ADMIN_ID) String adminId,
                      @RequestParam String content,
                      @RequestParam(value = "start_time") Long startTime,
                      @RequestParam(value = "end_time") Long endTime,
                      @ModelBind PageDTO pageDTO) throws Exception {
        IResultSet<SecurityAdminLogListVO> adminLogListResultSet = iAdminLogService.list(adminId, content, startTime, endTime, pageDTO);
        return new L<SecurityAdminLogListVO>().listView(adminLogListResultSet, pageDTO.getPage());
    }

    /**
     * 删除日志
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @Group(clientName = SecurityConstants.PERMISSION_CLIENT_NAME,permissions = {@Permission(groupId = SecurityPermission.GROUP_ID_ADMIN,
            groupName = SecurityPermission.GROUP_NAME_ADMIN,
            name = SecurityPermission.PERMISSION_NAME_ADMIN_LOG_DELETE,
            code = SecurityPermission.PERMISSION_CODE_ADMIN_LOG_DELETE)})
    @RequestMapping(value = "/delete", method = Type.HttpMethod.POST)
    public IView delete(
            @VRequired(msg = "id不能为空")
            @RequestParam(value = "ids[]") String[] ids) throws Exception {
        R result = iAdminLogService.delete(ids);
        return V.view(result);
    }

}
