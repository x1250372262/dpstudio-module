package com.dpstudio.module.security.interCeptor;

import com.dpstudio.dev.core.UserSession;
import com.dpstudio.module.security.core.Code;
import com.dpstudio.module.security.core.SecurityConstants;
import com.dpstudio.module.security.dao.ISecurityAdminDao;
import com.dpstudio.module.security.model.SecurityAdmin;
import com.dpstudio.module.security.service.ISecuritySettingService;
import com.dpstudio.module.security.vo.detail.SecuritySettingDetailVO;
import net.ymate.platform.core.YMP;
import net.ymate.platform.core.beans.intercept.IInterceptor;
import net.ymate.platform.core.beans.intercept.InterceptContext;
import net.ymate.platform.core.beans.intercept.InterceptException;
import net.ymate.platform.core.support.ErrorCode;
import net.ymate.platform.webmvc.base.Type;
import net.ymate.platform.webmvc.context.WebContext;
import net.ymate.platform.webmvc.util.WebResult;
import net.ymate.platform.webmvc.util.WebUtils;
import net.ymate.platform.webmvc.view.IView;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class UserSessionCheckInterceptor implements IInterceptor {

    private final ISecurityAdminDao iSecurityAdminDao = YMP.get().getBeanFactory().getBean(ISecurityAdminDao.class);
    private final ISecuritySettingService iSecuritySettingService = YMP.get().getBeanFactory().getBean(ISecuritySettingService.class);

    @Override
    public Object intercept(InterceptContext context) throws InterceptException {
        // 判断当前拦截器执行方向
        if (Direction.BEFORE.equals(context.getDirection())) {
            UserSession sessionBean = UserSession.current();
            if (sessionBean == null) {
                return login();
            } else {
                SecuritySettingDetailVO securitySettingDetailVO = null;
                try {
                    securitySettingDetailVO = iSecuritySettingService.detail(SecurityConstants.SET_ID);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //禁止多端登录并且类型是踢出系统
                boolean loginClientBool = securitySettingDetailVO != null
                        && Objects.equals(securitySettingDetailVO.getLoginClientStatus(), SecurityConstants.BOOL_TRUE)
                        && Objects.equals(securitySettingDetailVO.getLoginClientType(), SecurityConstants.BOOL_TRUE);
                if (loginClientBool) {
                    //验证sessionid是否是之前用户
                    SecurityAdmin securityAdmin = null;
                    try {
                        securityAdmin = iSecurityAdminDao.findById(sessionBean.getUid(), SecurityAdmin.FIELDS.SESSION_TOKEN);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (securityAdmin == null) {
                        return login();
                    }
                    if (StringUtils.isNotBlank(securityAdmin.getSessionToken()) && !securityAdmin.getSessionToken().equals(sessionBean.getId())) {
                        return out();
                    }
                }
                // 更新会话最后活动时间
                sessionBean.touch();
            }
        }
        return null;
    }


    private IView login() {
        ErrorCode message = ErrorCode.create(Code.SECURITY_ADMIN_INVALID_OR_TIMEOUT.getCode(), Code.SECURITY_ADMIN_INVALID_OR_TIMEOUT.getMsg());
        if (WebUtils.isAjax(WebContext.getRequest())) {
            return WebResult.formatView(WebResult.create(WebContext.getContext().getOwner(), message), Type.Const.FORMAT_JSON);
        }
        return WebUtils.buildErrorView(WebContext.getContext().getOwner(), message, null, 3);
    }

    private IView out() {
        ErrorCode message = ErrorCode.create(Code.SECURITY_ADMIN_OUT.getCode(), Code.SECURITY_ADMIN_OUT.getMsg());
        //
        if (WebUtils.isAjax(WebContext.getRequest())) {
            return WebResult.formatView(WebResult.create(WebContext.getContext().getOwner(), message), Type.Const.FORMAT_JSON);
        }
        return WebUtils.buildErrorView(WebContext.getContext().getOwner(), message, null, 3);
    }

}
