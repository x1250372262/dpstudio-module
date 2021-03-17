package com.dpstudio.module.security.controller;

import com.dpstudio.dev.core.L;
import com.dpstudio.dev.core.R;
import com.dpstudio.dev.core.V;
import com.dpstudio.dev.dto.PageDTO;
import com.dpstudio.dev.security.annotation.Group;
import com.dpstudio.dev.security.annotation.Permission;
import com.dpstudio.dev.security.annotation.Security;
import com.dpstudio.module.security.core.SecurityConstants;
import com.dpstudio.module.security.core.SecurityPermission;
import com.dpstudio.module.security.dto.SecurityAdminRoleDTO;
import com.dpstudio.module.security.interCeptor.JwtCheckInterceptor;
import com.dpstudio.module.security.model.SecurityAdminRole;
import com.dpstudio.module.security.service.ISecurityAdminRoleService;
import com.dpstudio.module.security.vo.list.SecurityAdminRoleListVO;
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
@Security
public class SecurityAdminRoleController {

    @Inject
    private ISecurityAdminRoleService iSecurityAdminRoleService;

    /**
     * 管理员角色集合
     *
     * @param adminId
     * @param pageDTO
     * @return
     * @throws Exception
     */
    @Group(clientName = SecurityConstants.PERMISSION_CLIENT_NAME,permissions = {@Permission(groupId = SecurityPermission.GROUP_ID_ADMIN,
            groupName = SecurityPermission.GROUP_NAME_ADMIN,
            name = SecurityPermission.PERMISSION_NAME_ADMIN_ROLE_LIST,
            code = SecurityPermission.PERMISSION_CODE_ADMIN_ROLE_LIST)})
    @RequestMapping(value = "/list", method = Type.HttpMethod.GET)
    public IView list(@VRequired(msg = "adminId不能为空")
                      @RequestParam(value = SecurityAdminRole.FIELDS.ADMIN_ID) String adminId,
                      @ModelBind PageDTO pageDTO) throws Exception {
        IResultSet<SecurityAdminRoleListVO> securityAdminRoleListResultSet = iSecurityAdminRoleService.list(adminId, pageDTO);
        return new L<SecurityAdminRoleListVO>().listView(securityAdminRoleListResultSet, pageDTO.getPage());
    }

    /**
     * 添加角色
     *
     * @param securityAdminRoleDTO
     * @return
     * @throws Exception
     */
    @Group(clientName = SecurityConstants.PERMISSION_CLIENT_NAME,permissions = {@Permission(groupId = SecurityPermission.GROUP_ID_ADMIN,
            groupName = SecurityPermission.GROUP_NAME_ADMIN,
            name = SecurityPermission.PERMISSION_NAME_ADMIN_ROLE_CREATE,
            code = SecurityPermission.PERMISSION_CODE_ADMIN_ROLE_CREATE)})
    @RequestMapping(value = "/create", method = Type.HttpMethod.POST)
    public IView create(@VModel
                        @ModelBind SecurityAdminRoleDTO securityAdminRoleDTO) throws Exception {
        R r = iSecurityAdminRoleService.create(securityAdminRoleDTO);
        return V.view(r);
    }

    /**
     * 删除角色
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @Group(clientName = SecurityConstants.PERMISSION_CLIENT_NAME,permissions = {@Permission(groupId = SecurityPermission.GROUP_ID_ADMIN,
            groupName = SecurityPermission.GROUP_NAME_ADMIN,
            name = SecurityPermission.PERMISSION_NAME_ADMIN_ROLE_DELETE,
            code = SecurityPermission.PERMISSION_CODE_ADMIN_ROLE_DELETE)})
    @RequestMapping(value = "/delete", method = Type.HttpMethod.POST)
    public IView delete(
            @VRequired(msg = "id不能为空")
            @RequestParam(value = "ids[]") String[] ids) throws Exception {
        R r = iSecurityAdminRoleService.delete(ids);
        return V.view(r);
    }

}
