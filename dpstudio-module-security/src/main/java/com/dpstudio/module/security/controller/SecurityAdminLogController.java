package com.dpstudio.module.security.controller;

import com.dpstudio.dev.core.R;
import com.dpstudio.module.security.model.SecurityAdminLog;
import com.dpstudio.module.security.service.ISecurityAdminLogService;
import com.dpstudio.module.security.vo.SecurityAdminLogListVO;
import net.ymate.platform.core.beans.annotation.Inject;
import net.ymate.platform.persistence.IResultSet;
import net.ymate.platform.validation.validate.VRequired;
import net.ymate.platform.webmvc.annotation.Controller;
import net.ymate.platform.webmvc.annotation.RequestMapping;
import net.ymate.platform.webmvc.annotation.RequestParam;
import net.ymate.platform.webmvc.base.Type;
import net.ymate.platform.webmvc.view.IView;

/**
 * @Author: 刘玉奇.
 * @Date: 2020/10/16.
 * @Time: 22:01.
 * @Description:
 */
@Controller
@RequestMapping("/admin/log")
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
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = Type.HttpMethod.GET)
    public IView list(@RequestParam(value = SecurityAdminLog.FIELDS.ADMIN_ID) String adminId,
                      @RequestParam String content,
                      @RequestParam(value = "start_time") Long startTime,
                      @RequestParam(value = "end_time") Long endTime,
                      @RequestParam(defaultValue = "1") int page,
                      @RequestParam(defaultValue = "10") int pageSize) throws Exception {
        IResultSet<SecurityAdminLogListVO> adminLogListVOIResultSet = iAdminLogService.findAll(adminId, content, startTime, endTime, page, pageSize);
        return R.listView(adminLogListVOIResultSet, page);
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
        R r = iAdminLogService.delete(ids);
        return r.json();
    }

}
