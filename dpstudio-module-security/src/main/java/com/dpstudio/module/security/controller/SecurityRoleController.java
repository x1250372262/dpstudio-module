package com.dpstudio.module.security.controller;

import com.dpstudio.dev.core.L;
import com.dpstudio.dev.core.R;
import com.dpstudio.dev.core.V;
import com.dpstudio.module.security.model.SecurityRole;
import com.dpstudio.module.security.service.ISecurityRoleService;
import com.dpstudio.module.security.vo.detail.SecurityRoleDetailVO;
import com.dpstudio.module.security.vo.detail.SecurityRoleListVO;
import com.dpstudio.module.security.vo.op.SecurityRoleVO;
import com.dpstudio.module.security.vo.select.SecurityRoleSelectVO;
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

import java.util.List;

@Controller
@RequestMapping("/role")
public class SecurityRoleController {

    @Inject
    private ISecurityRoleService iSecurityRoleService;

    /**
     * 角色列表
     *
     * @param name
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = Type.HttpMethod.GET)
    public IView list(@RequestParam String name,
                      @RequestParam(defaultValue = "1") Integer page,
                      @RequestParam(defaultValue = "10") Integer pageSize) throws Exception {
        IResultSet<SecurityRoleListVO> securityRoleListResultSet = iSecurityRoleService.list(name, page, pageSize);
        return new L<SecurityRoleListVO>().listView(securityRoleListResultSet, page);
    }

    /**
     * 角色下拉选
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/select", method = Type.HttpMethod.GET)
    public IView select() throws Exception {
        List<SecurityRoleSelectVO> securityRoleSelectVOList = iSecurityRoleService.select();
        return WebResult.succeed().data(securityRoleSelectVOList).keepNullValue().toJsonView();
    }

    /**
     * 添加角色
     *
     * @param securityRoleVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/create", method = Type.HttpMethod.POST)
    public IView create(@VModel
                        @ModelBind SecurityRoleVO securityRoleVO) throws Exception {
        R r = iSecurityRoleService.create(securityRoleVO);
        return V.view(r);
    }

    /**
     * 角色详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/detail", method = Type.HttpMethod.POST)
    public IView detail(@VRequired(msg = "id不能为空")
                        @RequestParam String id) throws Exception {
        SecurityRoleDetailVO securityRoleDetailVO = iSecurityRoleService.detail(id);
        return WebResult.succeed().data(securityRoleDetailVO).keepNullValue().toJsonView();
    }

    /**
     * 修改角色
     *
     * @param id
     * @param securityRoleVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update", method = Type.HttpMethod.POST)
    public IView update(@VRequired(msg = "id不能为空")
                        @RequestParam String id,
                        @VRequired(msg = "最后修改时间不能为空")
                        @RequestParam(value = SecurityRole.FIELDS.LAST_MODIFY_TIME) Long lastModifyTime,
                        @VModel
                        @ModelBind SecurityRoleVO securityRoleVO) throws Exception {
        R r = iSecurityRoleService.update(id, lastModifyTime, securityRoleVO);
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
        R r = iSecurityRoleService.delete(ids);
        return V.view(r);
    }

}
