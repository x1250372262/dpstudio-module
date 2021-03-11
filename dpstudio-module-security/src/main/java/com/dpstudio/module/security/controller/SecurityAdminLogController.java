package com.dpstudio.module.security.controller;

import com.dpstudio.dev.core.L;
import com.dpstudio.dev.core.R;
import com.dpstudio.dev.core.V;
import com.dpstudio.dev.dto.PageDTO;
import com.dpstudio.module.security.interCeptor.JwtCheckInterceptor;
import com.dpstudio.module.security.model.SecurityAdminLog;
import com.dpstudio.module.security.service.ISecurityAdminLogService;
import com.dpstudio.module.security.vo.list.SecurityAdminLogListVO;
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
    @RequestMapping(value = "/list", method = Type.HttpMethod.GET)
    public IView list(@RequestParam(value = SecurityAdminLog.FIELDS.ADMIN_ID) String adminId,
                      @RequestParam String content,
                      @RequestParam(value = "start_time") Long startTime,
                      @RequestParam(value = "end_time") Long endTime,
                      @ModelBind PageDTO pageDTO) throws Exception {
        IResultSet<SecurityAdminLogListVO> adminLogListResultSet = iAdminLogService.findAll(adminId, content, startTime, endTime, pageDTO);
        return new L<SecurityAdminLogListVO>().listView(adminLogListResultSet, pageDTO.getPage());
    }

    /**
     * 删除日志
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete", method = Type.HttpMethod.POST)
    public IView delete(
            @VRequired(msg = "id不能为空")
            @RequestParam(value = "ids[]") String[] ids) throws Exception {
        R result = iAdminLogService.delete(ids);
        return V.view(result);
    }

}
