package com.mx.module.security.controller;

import com.mx.dev.core.L;
import com.mx.dev.security.annotation.Security;
import com.mx.module.security.interCeptor.JwtCheckInterceptor;
import com.mx.module.security.service.IPermissionService;
import com.mx.module.security.vo.PermissionVO;
import net.ymate.platform.core.beans.annotation.Before;
import net.ymate.platform.core.beans.annotation.Inject;
import net.ymate.platform.webmvc.annotation.Controller;
import net.ymate.platform.webmvc.annotation.RequestMapping;
import net.ymate.platform.webmvc.view.IView;

import java.util.List;

/**
 * @Author: mengxiang.
 * @create: 2021-03-15 14:07
 * @Description:
 */
@Controller
@RequestMapping("/permission")
@Before(JwtCheckInterceptor.class)
@Security
public class PermissionController {

    @Inject
    private IPermissionService iPermissionService;

    /**
     * 权限下拉选
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/select")
    public IView select() throws Exception {
        List<PermissionVO> permissionList = iPermissionService.select();
        return new L<PermissionVO>().listView(permissionList);
    }
}
