package com.dpstudio.module.security.controller;

import com.dpstudio.dev.core.L;
import com.dpstudio.dev.core.R;
import com.dpstudio.dev.core.V;
import com.dpstudio.dev.security.jwt.JWT;
import com.dpstudio.module.security.interCeptor.JwtCheckInterceptor;
import com.dpstudio.module.security.interCeptor.JwtOutInterceptor;
import com.dpstudio.module.security.model.SecurityAdmin;
import com.dpstudio.module.security.service.ISecurityAdminService;
import com.dpstudio.module.security.vo.detail.SecurityAdminDetailVO;
import com.dpstudio.module.security.vo.list.SecurityAdminListVO;
import com.dpstudio.module.security.vo.op.SecurityAdminVO;
import net.ymate.platform.commons.lang.BlurObject;
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
import net.ymate.platform.webmvc.util.WebResult;
import net.ymate.platform.webmvc.view.IView;


/**
 * @author 徐建鹏
 * @Date 2020/10/15.
 * @Time: 15:00.
 * @Description: 管理员控制器
 */
@Controller
@RequestMapping("/admin")
@Before(JwtCheckInterceptor.class)
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
            @RequestParam String password) throws Exception {

        R result = iSecurityAdminService.login(userName, password);
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
        SecurityAdminDetailVO securityAdminDetailVO = iSecurityAdminService.detail(BlurObject.bind(JWT.Store.getPara("uid")).toStringValue());
        return WebResult.succeed().data(securityAdminDetailVO).keepNullValue().toJsonView();
    }

    /**
     * 修改用户信息
     *
     * @param securityAdminVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update/info", method = Type.HttpMethod.POST)
    public IView updateUser(@VModel
                            @ModelBind SecurityAdminVO securityAdminVO) throws Exception {
        R result = iSecurityAdminService.updateInfo(securityAdminVO);
        return V.view(result);
    }

    /**
     * 管理员列表
     *
     * @param userName
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = Type.HttpMethod.GET)
    public IView list(@RequestParam(value = SecurityAdmin.FIELDS.USER_NAME) String userName,
                      @RequestParam(value = SecurityAdmin.FIELDS.REAL_NAME) String realName,
                      @RequestParam(value = SecurityAdmin.FIELDS.DISABLE_STATUS) Integer disableStatus,
                      @RequestParam(defaultValue = "1") Integer page,
                      @RequestParam(defaultValue = "10") Integer pageSize) throws Exception {
        IResultSet<SecurityAdminListVO> securityAdminListResultSet = iSecurityAdminService.list(userName, realName, disableStatus, page, pageSize);
        return new L<SecurityAdminListVO>().listView(securityAdminListResultSet, page);
    }

    /**
     * 添加管理员
     *
     * @param securityAdminVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/create", method = Type.HttpMethod.POST)
    public IView create(@VModel
                        @ModelBind SecurityAdminVO securityAdminVO,
                        @VRequired(msg = "密码不能为空")
                        @RequestParam String password) throws Exception {
        R result = iSecurityAdminService.create(securityAdminVO, password);
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
    @RequestMapping(value = "/delete", method = Type.HttpMethod.POST)
    public IView delete(
            @VRequired(msg = "id不能为空")
            @RequestParam(value = "ids[]") String[] ids) throws Exception {
        R r = iSecurityAdminService.delete(ids);
        return V.view(r);
    }

}


