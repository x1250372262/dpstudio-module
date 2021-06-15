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
import com.mx.module.security.dto.SecurityRoleDTO;
import com.mx.module.security.interCeptor.JwtCheckInterceptor;
import com.mx.module.security.model.SecurityRole;
import com.mx.module.security.service.ISecurityRoleService;
import com.mx.module.security.vo.detail.SecurityRoleDetailVO;
import com.mx.module.security.vo.list.SecurityRoleListVO;
import com.mx.module.security.vo.select.SecurityRoleSelectVO;
import net.ymate.platform.core.beans.annotation.Before;
import net.ymate.platform.core.beans.annotation.Inject;
import net.ymate.platform.core.persistence.IResultSet;
import net.ymate.platform.validation.annotation.VModel;
import net.ymate.platform.validation.validate.VRequired;
import net.ymate.platform.webmvc.annotation.*;
import net.ymate.platform.webmvc.base.Type;
import net.ymate.platform.webmvc.util.WebResult;
import net.ymate.platform.webmvc.view.IView;

import java.util.List;

@Controller
@RequestMapping("/role")
@Before(JwtCheckInterceptor.class)
@Security
public class SecurityRoleController {

    @Inject
    private ISecurityRoleService iSecurityRoleService;

    /**
     * 角色列表
     *
     * @param name
     * @param pageDTO
     * @return
     * @throws Exception
     */
    @Group(clientName = SecurityConstants.PERMISSION_CLIENT_NAME, permissions = {@Permission(groupId = SecurityPermission.GROUP_ID_ROLE,
            groupName = SecurityPermission.GROUP_NAME_ROLE,
            name = SecurityPermission.PERMISSION_NAME_ROLE_LIST,
            code = SecurityPermission.PERMISSION_CODE_ROLE_LIST)})
    @RequestMapping(value = "/list", method = Type.HttpMethod.GET)
    public IView list(@RequestParam String name,
                      @ModelBind PageDTO pageDTO) throws Exception {
        IResultSet<SecurityRoleListVO> securityRoleListResultSet = iSecurityRoleService.list(name, pageDTO.toBean());
        return new L<SecurityRoleListVO>().listView(securityRoleListResultSet, pageDTO.getPage());
    }

    /**
     * 角色下拉选
     *
     * @return
     * @throws Exception
     */
    @Group(clientName = SecurityConstants.PERMISSION_CLIENT_NAME, permissions = {@Permission(groupId = SecurityPermission.GROUP_ID_ROLE,
            groupName = SecurityPermission.GROUP_NAME_ROLE,
            name = SecurityPermission.PERMISSION_NAME_ROLE_SELECT,
            code = SecurityPermission.PERMISSION_CODE_ROLE_SELECT)})
    @RequestMapping(value = "/select", method = Type.HttpMethod.GET)
    public IView select() throws Exception {
        List<SecurityRoleSelectVO> securityRoleSelectVOList = iSecurityRoleService.select();
        return WebResult.succeed().data(securityRoleSelectVOList).keepNullValue().toJsonView();
    }

    /**
     * 添加角色
     *
     * @param securityRoleDTO
     * @return
     * @throws Exception
     */
    @Group(clientName = SecurityConstants.PERMISSION_CLIENT_NAME, permissions = {@Permission(groupId = SecurityPermission.GROUP_ID_ROLE,
            groupName = SecurityPermission.GROUP_NAME_ROLE,
            name = SecurityPermission.PERMISSION_NAME_ROLE_CREATE,
            code = SecurityPermission.PERMISSION_CODE_ROLE_CREATE)})
    @RequestMapping(value = "/create", method = Type.HttpMethod.POST)
    public IView create(@VModel
                        @ModelBind SecurityRoleDTO securityRoleDTO) throws Exception {
        R r = iSecurityRoleService.create(securityRoleDTO);
        return V.view(r);
    }

    /**
     * 角色详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Group(clientName = SecurityConstants.PERMISSION_CLIENT_NAME, permissions = {@Permission(groupId = SecurityPermission.GROUP_ID_ROLE,
            groupName = SecurityPermission.GROUP_NAME_ROLE,
            name = SecurityPermission.PERMISSION_NAME_ROLE_DETAIL,
            code = SecurityPermission.PERMISSION_CODE_ROLE_DETAIL)})
    @RequestMapping(value = "/detail/{id}", method = Type.HttpMethod.GET)
    public IView detail(@VRequired(msg = "id不能为空")
                        @PathVariable String id) throws Exception {
        SecurityRoleDetailVO securityRoleDetailVO = iSecurityRoleService.detail(id);
        return new L<SecurityRoleDetailVO>().detailView(securityRoleDetailVO);
    }

    /**
     * 修改角色
     *
     * @param id
     * @param securityRoleDTO
     * @return
     * @throws Exception
     */
    @Group(clientName = SecurityConstants.PERMISSION_CLIENT_NAME, permissions = {@Permission(groupId = SecurityPermission.GROUP_ID_ROLE,
            groupName = SecurityPermission.GROUP_NAME_ROLE,
            name = SecurityPermission.PERMISSION_NAME_ROLE_UPDATE,
            code = SecurityPermission.PERMISSION_CODE_ROLE_UPDATE)})
    @RequestMapping(value = "/update/{id}", method = Type.HttpMethod.POST)
    public IView update(@VRequired(msg = "id不能为空")
                        @PathVariable String id,
                        @VRequired(msg = "最后修改时间不能为空")
                        @RequestParam(value = SecurityRole.FIELDS.LAST_MODIFY_TIME) Long lastModifyTime,
                        @VModel
                        @ModelBind SecurityRoleDTO securityRoleDTO) throws Exception {
        R r = iSecurityRoleService.update(id, lastModifyTime, securityRoleDTO);
        return V.view(r);
    }

    /**
     * 删除角色
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @Group(clientName = SecurityConstants.PERMISSION_CLIENT_NAME, permissions = {@Permission(groupId = SecurityPermission.GROUP_ID_ROLE,
            groupName = SecurityPermission.GROUP_NAME_ROLE,
            name = SecurityPermission.PERMISSION_NAME_ROLE_DELETE,
            code = SecurityPermission.PERMISSION_CODE_ROLE_DELETE)})
    @RequestMapping(value = "/delete", method = Type.HttpMethod.POST)
    public IView delete(
            @VRequired(msg = "id不能为空")
            @RequestParam(value = "ids[]") String[] ids) throws Exception {
        R r = iSecurityRoleService.delete(ids);
        return V.view(r);
    }

    /**
     * 权限详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/permission/detail", method = Type.HttpMethod.POST)
    public IView permissionDetail(@VRequired(msg = "id不能为空")
                                  @RequestParam String id) throws Exception {
        R result = iSecurityRoleService.permissionDetail(id);
        return V.view(result);
    }


    /**
     * 设置权限
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/permission/set", method = Type.HttpMethod.POST)
    public IView permissionSet(@VRequired(msg = "id不能为空")
                               @RequestParam String id,
                               @RequestParam(value = "permissions[]") String[] permissions) throws Exception {
        R result = iSecurityRoleService.permissionSet(id, permissions);
        return V.view(result);
    }

}
