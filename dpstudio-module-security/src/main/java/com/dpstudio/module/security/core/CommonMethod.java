package com.dpstudio.module.security.core;

import com.dpstudio.module.security.service.ISecuritySettingService;
import com.dpstudio.module.security.vo.detail.SecuritySettingDetailVO;
import net.ymate.platform.core.YMP;

/**
 * @Author: mengxiang.
 * @Date: 2020/10/19.
 * @Time: 4:43 下午.
 * @Description:
 */
public class CommonMethod {


    public static SecuritySettingDetailVO settingDetail() {
        try {
            return YMP.get().getBeanFactory().getBean(ISecuritySettingService.class).detail(SecurityConstants.SET_ID);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("安全配置不存在");
        }
    }
}
