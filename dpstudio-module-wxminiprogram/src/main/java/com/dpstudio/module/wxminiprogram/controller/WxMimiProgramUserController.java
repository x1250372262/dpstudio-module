package com.dpstudio.module.wxminiprogram.controller;

import com.dpstudio.dev.core.R;
import com.dpstudio.dev.core.V;
import com.dpstudio.module.wxminiprogram.dto.MobileDTO;
import com.dpstudio.module.wxminiprogram.dto.UserDTO;
import com.dpstudio.module.wxminiprogram.dto.UserInfoDTO;
import com.dpstudio.module.wxminiprogram.service.IWxMimiProgramUserService;
import net.ymate.platform.core.beans.annotation.Inject;
import net.ymate.platform.validation.annotation.VModel;
import net.ymate.platform.validation.validate.VRequired;
import net.ymate.platform.webmvc.annotation.Controller;
import net.ymate.platform.webmvc.annotation.ModelBind;
import net.ymate.platform.webmvc.annotation.RequestMapping;
import net.ymate.platform.webmvc.annotation.RequestParam;
import net.ymate.platform.webmvc.base.Type;
import net.ymate.platform.webmvc.view.IView;

/**
 * 小程序用户接口
 *
 * @Author: mengxiang.
 * @Date 2021.03.31.
 * @Time: 09:30.
 * @Description: 小程序用户接口
 */
@Controller
@RequestMapping("/dpstudio/wx/mimiprogram/user")
public class WxMimiProgramUserController {

    @Inject
    private IWxMimiProgramUserService iWxMimiProgramUserService;


    /**
     * 获取session
     *
     * @param code code|String|Y|xxxxxx
     * @return 获取session
     * @throws Exception
     * @resp ret 错误码|int|Y|0
     * @resp msg 错误描述|String|N|
     * @resp data session信息|String|Y|xxxxxx
     * @respbody 返回示例数据 例子:{"ret":0,"msg":"","data":"xxxxxxx"}
     */
    @RequestMapping(value = "getSession", method = Type.HttpMethod.POST)
    public IView getSession(@VRequired(msg = "code不能为空")
                            @RequestParam String code) throws Exception {
        R result = iWxMimiProgramUserService.getSession(code);
        return V.view(result);

    }

    /**
     * 用户信息
     *
     * @return 用户信息
     * @throws Exception
     * @paramObj UserDTO
     * @resp ret 错误码|int|Y|0
     * @resp msg 错误描述|String|N|
     * @resp token 用户小程序id|String|Y|xxxxxx
     * @respbody 返回示例数据 例子:{"ret":0,"msg":"","token":"xxxxx"}
     */
    @RequestMapping(value = "userInfo", method = Type.HttpMethod.POST)
    public IView userInfo(@VModel
                          @ModelBind UserDTO userDTO) throws Exception {
        R result = iWxMimiProgramUserService.userInfo(userDTO);
        return V.view(result);
    }

    /**
     * 修改
     *
     * @return 用户信息
     * @throws Exception
     * @paramObj UserDTO
     * @resp ret 错误码|int|Y|0
     * @resp msg 错误描述|String|N|
     * @resp token 用户小程序id|String|Y|xxxxxx
     * @respbody 返回示例数据 例子:{"ret":0,"msg":"","token":"xxxxx"}
     */
    @RequestMapping(value = "update", method = Type.HttpMethod.POST)
    public IView update(@VModel
                          @ModelBind UserInfoDTO userInfoDTO) throws Exception {
        R result = iWxMimiProgramUserService.update(userInfoDTO);
        return V.view(result);
    }

    /**
     * 获取手机号
     *
     * @return 获取手机号
     * @throws Exception
     * @paramObj MobileDTO
     * @resp ret 错误码|int|Y|0
     * @resp msg 错误描述|String|N|
     * @respbody 返回示例数据 例子:{"ret":0,"msg":""}
     */
    @RequestMapping(value = "mobileInfo", method = Type.HttpMethod.POST)
    public IView mobileInfo(@VModel
                            @ModelBind MobileDTO mobileDTO) throws Exception {
        R result = iWxMimiProgramUserService.mobileInfo(mobileDTO);
        return V.view(result);

    }
}
