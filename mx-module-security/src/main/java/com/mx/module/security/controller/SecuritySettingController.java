package com.mx.module.security.controller;

import com.mx.dev.core.R;
import com.mx.dev.core.V;
import com.mx.dev.security.annotation.Group;
import com.mx.dev.security.annotation.Permission;
import com.mx.dev.security.annotation.Security;
import com.mx.module.security.core.SecurityConstants;
import com.mx.module.security.core.SecurityPermission;
import com.mx.module.security.dto.SecuritySettingDTO;
import com.mx.module.security.interCeptor.JwtCheckInterceptor;
import com.mx.module.security.model.SecuritySetting;
import com.mx.module.security.service.ISecuritySettingService;
import com.mx.module.security.vo.detail.SecuritySettingDetailVO;
import net.ymate.platform.core.beans.annotation.Before;
import net.ymate.platform.core.beans.annotation.Inject;
import net.ymate.platform.validation.annotation.VModel;
import net.ymate.platform.validation.validate.VRequired;
import net.ymate.platform.webmvc.annotation.Controller;
import net.ymate.platform.webmvc.annotation.ModelBind;
import net.ymate.platform.webmvc.annotation.RequestMapping;
import net.ymate.platform.webmvc.annotation.RequestParam;
import net.ymate.platform.webmvc.base.Type;
import net.ymate.platform.webmvc.util.WebResult;
import net.ymate.platform.webmvc.view.IView;

@Controller
@RequestMapping("/setting")
@Before(JwtCheckInterceptor.class)
@Security
public class SecuritySettingController {

    @Inject
    private ISecuritySettingService iSecuritySettingService;

    /**
     * 修改安全设置信息
     *
     * @param securitySettingDTO
     * @return
     * @throws Exception
     */
    @Group(clientName = SecurityConstants.PERMISSION_CLIENT_NAME, permissions = {@Permission(groupId = SecurityPermission.GROUP_ID_SETTING,
            groupName = SecurityPermission.GROUP_NAME_SETTING,
            name = SecurityPermission.PERMISSION_NAME_SETTING_UPDATE,
            code = SecurityPermission.PERMISSION_CODE_SETTING_UPDATE)})
    @RequestMapping(value = "/update", method = Type.HttpMethod.POST)
    public IView update(@VRequired(msg = "最后修改时间不能为空")
                        @RequestParam(value = SecuritySetting.FIELDS.LAST_MODIFY_TIME) Long lastModifyTime,
                        @VModel
                        @ModelBind SecuritySettingDTO securitySettingDTO) throws Exception {
        R r = iSecuritySettingService.update(lastModifyTime, securitySettingDTO);
        return V.view(r);
    }

    /**
     * 安全设置详情
     *
     * @param
     * @return
     * @throws Exception
     */
    @Group(clientName = SecurityConstants.PERMISSION_CLIENT_NAME, permissions = {@Permission(groupId = SecurityPermission.GROUP_ID_SETTING,
            groupName = SecurityPermission.GROUP_NAME_SETTING,
            name = SecurityPermission.PERMISSION_NAME_SETTING_DETAIL,
            code = SecurityPermission.PERMISSION_CODE_SETTING_DETAIL)})
    @RequestMapping(value = "/detail", method = Type.HttpMethod.POST)
    public IView detail() throws Exception {
        SecuritySettingDetailVO securitySettingDetailVO = iSecuritySettingService.detail(null);
        return WebResult.succeed().data(securitySettingDetailVO).keepNullValue().toJsonView();
    }

}