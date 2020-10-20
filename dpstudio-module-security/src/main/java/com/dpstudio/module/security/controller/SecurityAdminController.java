package com.dpstudio.module.security.controller;

import com.dpstudio.dev.core.R;
import com.dpstudio.module.security.model.SecurityAdmin;
import com.dpstudio.module.security.service.ISecurityAdminService;
import com.dpstudio.module.security.vo.SecurityAdminDetailVO;
import com.dpstudio.module.security.vo.SecurityAdminListVO;
import com.dpstudio.module.security.vo.SecurityAdminOPVO;
import net.ymate.framework.webmvc.intercept.UserSessionCheckInterceptor;
import net.ymate.framework.webmvc.support.UserSessionBean;
import net.ymate.platform.core.beans.annotation.Before;
import net.ymate.platform.core.beans.annotation.Clean;
import net.ymate.platform.core.beans.annotation.Inject;
import net.ymate.platform.persistence.IResultSet;
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
import net.ymate.platform.webmvc.view.View;


/**
 * @author 徐建鹏
 * @Date 2020/10/15.
 * @Time: 15:00.
 * @Description: 管理员控制器
 */
@Controller
@RequestMapping("/admin")
@Before(UserSessionCheckInterceptor.class)
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
        return result.json();
    }

    /**
     * 用户退出
     *
     * @param
     * @return 登录页面
     * @throws Exception
     */
    @Clean
    @RequestMapping(value = "/logout")
    public IView logout() throws Exception {
        WebContext.getRequest().getSession().invalidate();
        return View.jspView("admin/login_view");
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
        return result.json();
    }


    /**
     * 获取用户信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/info", method = Type.HttpMethod.GET)
    public IView get() throws Exception {
        SecurityAdminDetailVO securityAdminDetailVO = iSecurityAdminService.detail(UserSessionBean.current().getUid());
        return WebResult.succeed().data(securityAdminDetailVO).keepNullValue().toJSON();
    }

    /**
     * 修改用户信息
     *
     * @param securityAdminOPVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update/info", method = Type.HttpMethod.POST)
    public IView updateUser(@VModel
                            @ModelBind SecurityAdminOPVO securityAdminOPVO) throws Exception {
        R result = iSecurityAdminService.updateInfo(securityAdminOPVO);
        return result.json();
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
                      @RequestParam(defaultValue = "1") int page,
                      @RequestParam(defaultValue = "10") int pageSize) throws Exception {
        IResultSet<SecurityAdminListVO> securityAdminListVOIResultSet = iSecurityAdminService.list(userName, realName, disableStatus, page, pageSize);
        return R.listView(securityAdminListVOIResultSet, page);
    }

    /**
     * 添加管理员
     *
     * @param securityAdminOPVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/create", method = Type.HttpMethod.POST)
    public IView create(@VModel
                        @ModelBind SecurityAdminOPVO securityAdminOPVO,
                        @VRequired(msg = "密码不能为空")
                        @RequestParam String password) throws Exception {
        R r = iSecurityAdminService.create(securityAdminOPVO,password);
        return r.json();
    }

    /**
     * 禁用/启用管理员
     *
     * @param id
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/disabled", method = Type.HttpMethod.POST)
    public IView disabled(@VRequired(msg = "id不能为空")
                          @RequestParam String id,
                          @VRequired(msg = "状态不能为空")
                          @RequestParam Integer status) throws Exception {
        R r = iSecurityAdminService.disabled(id, status);
        return r.json();
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
        R r = iSecurityAdminService.resetPassword(id);
        return r.json();
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
        R r = iSecurityAdminService.unlock(id);
        return r.json();
    }

}


