package com.dpstudio.module.security.controller;

import com.dpstudio.dev.core.R;
import com.dpstudio.dev.core.V;
import com.dpstudio.module.security.core.SecurityConstants;
import com.dpstudio.module.security.model.SecuritySetting;
import com.dpstudio.module.security.service.ISecuritySettingService;
import com.dpstudio.module.security.vo.detail.SecuritySettingDetailVO;
import com.dpstudio.module.security.vo.op.SecuritySettingVO;
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
public class SecuritySettingController {

    @Inject
    private ISecuritySettingService iSecuritySettingService;

    /**
     * 修改安全设置信息
     *
     * @param id
     * @param securitySettingVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update", method = Type.HttpMethod.POST)
    public IView update(@VRequired(msg = "id不能为空")
                        @RequestParam(defaultValue = SecurityConstants.SET_ID) String id,
                        @VRequired(msg = "最后修改时间不能为空")
                        @RequestParam(value = SecuritySetting.FIELDS.LAST_MODIFY_TIME) Long lastModifyTime,
                        @VModel
                        @ModelBind SecuritySettingVO securitySettingVO) throws Exception {
        R r = iSecuritySettingService.update(id, lastModifyTime, securitySettingVO);
        return V.view(r);
    }

    /**
     * 安全设置详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/detail", method = Type.HttpMethod.POST)
    public IView detail(@VRequired(msg = "id不能为空")
                        @RequestParam(defaultValue = SecurityConstants.SET_ID) String id) throws Exception {
        SecuritySettingDetailVO securitySettingDetailVO = iSecuritySettingService.detail(id);
        return WebResult.succeed().data(securitySettingDetailVO).keepNullValue().toJsonView();
    }

}
