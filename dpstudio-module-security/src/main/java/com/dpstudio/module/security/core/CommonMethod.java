package com.dpstudio.module.security.core;

import com.dpstudio.module.security.service.ISecuritySettingService;
import com.dpstudio.module.security.vo.SecuritySettingDetailVO;
import net.ymate.framework.webmvc.support.UserSessionBean;
import net.ymate.platform.core.YMP;

import java.util.Optional;

/**
 * @Author: 徐建鹏.
 * @Date: 2020/10/19.
 * @Time: 4:43 下午.
 * @Description:
 */
public class CommonMethod {

    /**
     * 获取登陆用户id
     *
     * @return
     */
    public static String userId() {
        return Optional.ofNullable(UserSessionBean.current())
                .map(UserSessionBean::getUid).orElse("1");
    }

    public static SecuritySettingDetailVO settingDetail() {
        try {
            return YMP.get().getBean(ISecuritySettingService.class).detail(SecurityConstants.SET_ID);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("安全配置不存在");
        }
    }
}
