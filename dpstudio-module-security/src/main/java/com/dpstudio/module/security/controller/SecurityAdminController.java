package com.dpstudio.module.security.controller;

import com.dpstudio.dev.core.L;
import com.dpstudio.dev.core.R;
import com.dpstudio.dev.core.V;
import com.dpstudio.dev.dto.PageDTO;
import com.dpstudio.dev.security.annotation.Group;
import com.dpstudio.dev.security.annotation.Permission;
import com.dpstudio.dev.security.annotation.Security;
import com.dpstudio.module.security.SecurityCache;
import com.dpstudio.module.security.core.SecurityConstants;
import com.dpstudio.module.security.core.SecurityPermission;
import com.dpstudio.module.security.dto.SecurityAdminDTO;
import com.dpstudio.module.security.interCeptor.JwtCheckInterceptor;
import com.dpstudio.module.security.interCeptor.JwtOutInterceptor;
import com.dpstudio.module.security.model.SecurityAdmin;
import com.dpstudio.module.security.service.ISecurityAdminService;
import com.dpstudio.module.security.vo.detail.SecurityAdminDetailVO;
import com.dpstudio.module.security.vo.list.SecurityAdminListVO;
import net.ymate.platform.core.beans.annotation.Before;
import net.ymate.platform.core.beans.annotation.Clean;
import net.ymate.platform.core.beans.annotation.Inject;
import net.ymate.platform.core.persistence.IResultSet;
import net.ymate.platform.validation.annotation.VModel;
import net.ymate.platform.validation.validate.VRequired;
import net.ymate.platform.webmvc.annotation.Controller;
import net.ymate.platform.webmvc.annotation.ModelBind;
import net.ymate.platform.webmvc.annotation.RequestMapping;
import net.ymate.platform.webmvc.annotation.RequestParam;
import net.ymate.platform.webmvc.base.Type;
import net.ymate.platform.webmvc.context.WebContext;
import net.ymate.platform.webmvc.util.WebResult;
import net.ymate.platform.webmvc.view.IView;

import java.util.Map;


/**
 * @author mengxiang
 * @Date 2020/10/15.
 * @Time: 15:00.
 * @Description: 管理员控制器
 */
@Controller
@RequestMapping("/admin")
@Before(JwtCheckInterceptor.class)
@Security
public class SecurityAdminController {

    @Inject
    private ISecurityAdminService iSecurityAdminService;

    /**
     * 管理员登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = Type.HttpMethod.POST)
    @Clean
    public IView login(
            @VRequired(msg = "用户名不能为空")
            @RequestParam(value = SecurityAdmin.FIELDS.USER_NAME) String userName,
            @VRequired(msg = "密码名不能为空")
            @RequestParam String password,
            @RequestParam(value = "client_name") String clientName) throws Exception {
        R result = iSecurityAdminService.login(userName, password, clientName);
        return V.view(result);
    }

    /**
     * 用户退出
     *
     * @param
     * @return 登录页面
     * @throws Exception
     */
    @Clean(JwtCheckInterceptor.class)
    @Before(JwtOutInterceptor.class)
    @RequestMapping(value = "/logout", method = Type.HttpMethod.POST)
    public IView logout() {
        return V.ok();
    }

    /**
     * 修改密码
     *
     * @param oldpwd
     * @param newpwd
     * @param confirmpwd
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/pwd/update", method = Type.HttpMethod.POST)
    public IView updatePassword(@VRequired(msg = "原密码不能为空")
                                @RequestParam(value = "old_pwd") String oldpwd,
                                @VRequired(msg = "新密码不能为空")
                                @RequestParam(value = "new_pwd") String newpwd,
                                @VRequired(msg = "确认密码不能为空")
                                @RequestParam(value = "confirm_pwd") String confirmpwd) throws Exception {
        R result = iSecurityAdminService.updatePassword(oldpwd, newpwd, confirmpwd);
        return V.view(result);
    }


    /**
     * 获取用户信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/info", method = Type.HttpMethod.GET)
    public IView get() throws Exception {
        SecurityAdminDetailVO securityAdminDetailVO = iSecurityAdminService.detail(SecurityCache.userId());
        return WebResult.succeed().data(securityAdminDetailVO).keepNullValue().toJsonView();
    }

    /**
     * 修改用户信息
     *
     * @param securityAdminDTO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update/info", method = Type.HttpMethod.POST)
    public IView updateUser(@VModel
                            @ModelBind SecurityAdminDTO securityAdminDTO) throws Exception {
        R result = iSecurityAdminService.updateInfo(securityAdminDTO);
        return V.view(result);
    }

    /**
     * 管理员列表
     *
     * @param userName
     * @param pageDTO
     * @return
     * @throws Exception
     */
    @Group(clientName = SecurityConstants.PERMISSION_CLIENT_NAME, permissions = {@Permission(groupId = SecurityPermission.GROUP_ID_ADMIN,
            groupName = SecurityPermission.GROUP_NAME_ADMIN,
            name = SecurityPermission.PERMISSION_NAME_ADMIN_LIST,
            code = SecurityPermission.PERMISSION_CODE_ADMIN_LIST)})
    @RequestMapping(value = "/list", method = Type.HttpMethod.GET)
    public IView list(@RequestParam(value = SecurityAdmin.FIELDS.USER_NAME) String userName,
                      @RequestParam(value = SecurityAdmin.FIELDS.REAL_NAME) String realName,
                      @RequestParam(value = SecurityAdmin.FIELDS.DISABLE_STATUS) Integer disableStatus,
                      @ModelBind PageDTO pageDTO) throws Exception {
        IResultSet<SecurityAdminListVO> securityAdminListResultSet = iSecurityAdminService.list(userName, realName, disableStatus, pageDTO);
        return new L<SecurityAdminListVO>().listView(securityAdminListResultSet, pageDTO.getPage());
    }

    /**
     * 添加管理员
     *
     * @param securityAdminDTO
     * @return
     * @throws Exception
     */
    @Group(clientName = SecurityConstants.PERMISSION_CLIENT_NAME, permissions = {@Permission(groupId = SecurityPermission.GROUP_ID_ADMIN,
            groupName = SecurityPermission.GROUP_NAME_ADMIN,
            name = SecurityPermission.PERMISSION_NAME_ADMIN_CREATE,
            code = SecurityPermission.PERMISSION_CODE_ADMIN_CREATE)})
    @RequestMapping(value = "/create", method = Type.HttpMethod.POST)
    public IView create(@VModel
                        @ModelBind SecurityAdminDTO securityAdminDTO,
                        @VRequired(msg = "密码不能为空")
                        @RequestParam String password) throws Exception {
        R result = iSecurityAdminService.create(securityAdminDTO, password);
        return V.view(result);
    }

    /**
     * 禁用/启用管理员
     *
     * @param ids
     * @param status
     * @return
     * @throws Exception
     */
    @Group(clientName = SecurityConstants.PERMISSION_CLIENT_NAME, permissions = {@Permission(groupId = SecurityPermission.GROUP_ID_ADMIN,
            groupName = SecurityPermission.GROUP_NAME_ADMIN,
            name = SecurityPermission.PERMISSION_NAME_ADMIN_DISABLED,
            code = SecurityPermission.PERMISSION_CODE_ADMIN_DISABLED)})
    @RequestMapping(value = "/disabled", method = Type.HttpMethod.POST)
    public IView disabled(@VRequired(msg = "id不能为空")
                          @RequestParam(value = "ids[]") String[] ids,
                          @VRequired(msg = "状态不能为空")
                          @RequestParam Integer status) throws Exception {
        R result = iSecurityAdminService.disabled(ids, status);
        return V.view(result);
    }

    /**
     * 重置密码
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Group(clientName = SecurityConstants.PERMISSION_CLIENT_NAME, permissions = {@Permission(groupId = SecurityPermission.GROUP_ID_ADMIN,
            groupName = SecurityPermission.GROUP_NAME_ADMIN,
            name = SecurityPermission.PERMISSION_NAME_ADMIN_PASSWORD,
            code = SecurityPermission.PERMISSION_CODE_ADMIN_PASSWORD)})
    @RequestMapping(value = "/resetPassword", method = Type.HttpMethod.POST)
    public IView resetPassword(@VRequired(msg = "id不能为空")
                               @RequestParam String id) throws Exception {
        R result = iSecurityAdminService.resetPassword(id);
        return V.view(result);
    }

    /**
     * 解除冻结
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Group(clientName = SecurityConstants.PERMISSION_CLIENT_NAME, permissions = {@Permission(groupId = SecurityPermission.GROUP_ID_ADMIN,
            groupName = SecurityPermission.GROUP_NAME_ADMIN,
            name = SecurityPermission.PERMISSION_NAME_ADMIN_UNLOCK,
            code = SecurityPermission.PERMISSION_CODE_ADMIN_UNLOCK)})
    @RequestMapping(value = "/unlock", method = Type.HttpMethod.POST)
    public IView unlock(@VRequired(msg = "id不能为空")
                        @RequestParam String id) throws Exception {
        R result = iSecurityAdminService.unlock(id);
        return V.view(result);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @Group(clientName = SecurityConstants.PERMISSION_CLIENT_NAME, permissions = {@Permission(groupId = SecurityPermission.GROUP_ID_ADMIN,
            groupName = SecurityPermission.GROUP_NAME_ADMIN,
            name = SecurityPermission.PERMISSION_NAME_ADMIN_DELETE,
            code = SecurityPermission.PERMISSION_CODE_ADMIN_DELETE)})
    @RequestMapping(value = "/delete", method = Type.HttpMethod.POST)
    public IView delete(
            @VRequired(msg = "id不能为空")
            @RequestParam(value = "ids[]") String[] ids) throws Exception {
        R r = iSecurityAdminService.delete(ids);
        return V.view(r);
    }

}


