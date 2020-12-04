package com.dpstudio.module.security.controller;

import com.dpstudio.dev.core.L;
import com.dpstudio.dev.core.R;
import com.dpstudio.dev.core.V;
import com.dpstudio.module.security.interCeptor.JwtCheckInterceptor;
import com.dpstudio.module.security.model.SecurityAdminRole;
import com.dpstudio.module.security.service.ISecurityAdminRoleService;
import com.dpstudio.module.security.vo.list.SecurityAdminRoleListVO;
import com.dpstudio.module.security.vo.op.SecurityAdminRoleVO;
import net.ymate.platform.core.beans.annotation.Before;
import net.ymate.platform.core.beans.annotation.Inject;
import net.ymate.platform.core.persistence.IResultSet;
import net.ymate.platform.validation.annotation.VModel;
import net.ymate.platform.validation.validate.VRequired;
import net.ymate.platform.webmvc.annotation.Controller;
import net.ymate.platform.webmvc.annotation.ModelBind;
import net.ymate.platform.webmvc.annotation.RequestMapping;
import net.ymate.platform.webmvc.annotation.RequestParam;
import net.ymate.platform.webmvc.base.Type;
import net.ymate.platform.webmvc.view.IView;

@Controller
@RequestMapping("/admin/role")
@Before(JwtCheckInterceptor.class)
public class SecurityAdminRoleController {

    @Inject
    private ISecurityAdminRoleService iSecurityAdminRoleService;

    /**
     * 管理员角色集合
     *
     * @param adminId
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = Type.HttpMethod.GET)
    public IView list(@VRequired(msg = "adminId不能为空")
                      @RequestParam(value = SecurityAdminRole.FIELDS.ADMIN_ID) String adminId,
                      @RequestParam(defaultValue = "1") Integer page,
                      @RequestParam(defaultValue = "10") Integer pageSize) throws Exception {
        IResultSet<SecurityAdminRoleListVO> securityAdminRoleListResultSet = iSecurityAdminRoleService.list(adminId, page, pageSize);
        return new L<SecurityAdminRoleListVO>().listView(securityAdminRoleListResultSet, page);
    }

    /**
     * 添加角色
     *
     * @param securityAdminRoleVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/create", method = Type.HttpMethod.POST)
    public IView create(@VModel
                        @ModelBind SecurityAdminRoleVO securityAdminRoleVO) throws Exception {
        R r = iSecurityAdminRoleService.create(securityAdminRoleVO);
        return V.view(r);
    }

    /**
     * 删除角色
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete", method = Type.HttpMethod.POST)
    public IView delete(
            @VRequired(msg = "id不能为空")
            @RequestParam(value = "ids[]") String[] ids) throws Exception {
        R r = iSecurityAdminRoleService.delete(ids);
        return V.view(r);
    }

}
