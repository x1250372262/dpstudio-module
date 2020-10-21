package com.dpstudio.module.security.interCeptor;

import net.ymate.platform.core.beans.annotation.Before;
import net.ymate.platform.webmvc.IInterceptorRule;
import net.ymate.platform.webmvc.annotation.InterceptorRule;

/**
 * Author: 徐建鹏.
 * Date: 17/5/2.
 * Time: 09:16.
 * 后台拦截session
 */
@InterceptorRule
@Before(UserSessionCheckInterceptor.class)
public class SecurityInterceptor implements IInterceptorRule {
    @InterceptorRule("/dpstudio/security/*")
    public void adminAll() {
    }
}