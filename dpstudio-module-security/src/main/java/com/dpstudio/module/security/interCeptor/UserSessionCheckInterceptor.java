package com.dpstudio.module.security.interCeptor;

import com.dpstudio.module.security.core.Code;
import com.dpstudio.module.security.core.SecurityConstants;
import com.dpstudio.module.security.dao.ISecurityAdminDao;
import com.dpstudio.module.security.model.SecurityAdmin;
import com.dpstudio.module.security.service.ISecuritySettingService;
import com.dpstudio.module.security.vo.SecuritySettingDetailVO;
import net.ymate.framework.core.Optional;
import net.ymate.framework.webmvc.support.UserSessionBean;
import net.ymate.platform.core.YMP;
import net.ymate.platform.core.beans.intercept.IInterceptor;
import net.ymate.platform.core.beans.intercept.InterceptContext;
import net.ymate.platform.core.lang.BlurObject;
import net.ymate.platform.core.util.ExpressionUtils;
import net.ymate.platform.webmvc.base.Type;
import net.ymate.platform.webmvc.context.WebContext;
import net.ymate.platform.webmvc.util.ErrorCode;
import net.ymate.platform.webmvc.util.WebResult;
import net.ymate.platform.webmvc.util.WebUtils;
import net.ymate.platform.webmvc.view.IView;
import net.ymate.platform.webmvc.view.View;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class UserSessionCheckInterceptor implements IInterceptor {

    private final ISecurityAdminDao iSecurityAdminDao = YMP.get().getBean(ISecurityAdminDao.class);
    private final ISecuritySettingService iSecuritySettingService = YMP.get().getBean(ISecuritySettingService.class);

    @Override
    public Object intercept(InterceptContext context) throws Exception {
        // 判断当前拦截器执行方向
        if (Direction.BEFORE.equals(context.getDirection())) {
            UserSessionBean sessionBean = UserSessionBean.current(context);
            if (sessionBean == null) {
                return login(context);
            } else {
                SecuritySettingDetailVO securitySettingDetailVO = iSecuritySettingService.detail(SecurityConstants.SET_ID);
                //禁止多端登录并且类型是踢出系统
                boolean loginClientBool = securitySettingDetailVO != null
                        && Objects.equals(securitySettingDetailVO.getLoginClientStatus(),SecurityConstants.BOOL_TRUE)
                        && Objects.equals(securitySettingDetailVO.getLoginClientType(),SecurityConstants.BOOL_TRUE);
                if(loginClientBool){
                    //验证sessionid是否是之前用户
                    SecurityAdmin securityAdmin = iSecurityAdminDao.findById(sessionBean.getUid(),SecurityAdmin.FIELDS.SESSION_TOKEN);
                    if(securityAdmin == null){
                        return login(context);
                    }
                    if(StringUtils.isNotBlank(securityAdmin.getSessionToken()) && !securityAdmin.getSessionToken().equals(sessionBean.getId())){
                        return out(context);
                    }
                }
                // 更新会话最后活动时间
                sessionBean.touch();
            }
        }
        return null;
    }


    private IView login(InterceptContext context) {
        HttpServletRequest request = WebContext.getRequest();
        //
        String redirectUrl = context.getOwner().getConfig().getParam(Optional.REDIRECT_LOGIN_URL);
        if (StringUtils.isBlank(redirectUrl) || !StringUtils.startsWithIgnoreCase(redirectUrl, "http://") && !StringUtils.startsWithIgnoreCase(redirectUrl, "https://")) {
            redirectUrl = WebUtils.buildRedirectURL(context, request, StringUtils.defaultIfBlank(WebUtils.buildRedirectURL(context, null), "login?redirect_url=${redirect_url}"), true);
        }
        redirectUrl = ExpressionUtils.bind(redirectUrl).set(Type.Const.REDIRECT_URL, WebUtils.appendQueryStr(request, true)).getResult();
        //
        ErrorCode _message = ErrorCode.create(ErrorCode.USER_SESSION_INVALID_OR_TIMEOUT, "用户未授权登录或会话已过期，请重新登录");
        //
        if (WebUtils.isAjax(WebContext.getRequest(), true, true)) {
            return WebResult.formatView(WebResult.create(WebContext.getContext().getOwner(), _message)
                    .attr(Type.Const.REDIRECT_URL, redirectUrl), Type.Const.FORMAT_JSON);
        }
        if (context.getContextParams().containsKey(Optional.OBSERVE_SILENCE)) {
            return View.redirectView(redirectUrl);
        }
        return WebUtils.buildErrorView(WebContext.getContext().getOwner(), _message, redirectUrl, BlurObject.bind(context.getOwner().getConfig().getParam(Optional.REDIRECT_TIME_INTERVAL)).toIntValue());
    }

    private IView out(InterceptContext context) {
        HttpServletRequest request = WebContext.getRequest();
        //
        String redirectUrl = context.getOwner().getConfig().getParam(Optional.REDIRECT_LOGIN_URL);
        if (StringUtils.isBlank(redirectUrl) || !StringUtils.startsWithIgnoreCase(redirectUrl, "http://") && !StringUtils.startsWithIgnoreCase(redirectUrl, "https://")) {
            redirectUrl = WebUtils.buildRedirectURL(context, request, StringUtils.defaultIfBlank(WebUtils.buildRedirectURL(context, null), "login?redirect_url=${redirect_url}"), true);
        }
        redirectUrl = ExpressionUtils.bind(redirectUrl).set(Type.Const.REDIRECT_URL, WebUtils.appendQueryStr(request, true)).getResult();
        //
        ErrorCode _message = ErrorCode.create(Code.SECURITY_ADMIN_OUT.getCode(), Code.SECURITY_ADMIN_OUT.getMsg());
        //
        if (WebUtils.isAjax(WebContext.getRequest(), true, true)) {
            return WebResult.formatView(WebResult.create(WebContext.getContext().getOwner(), _message)
                    .attr(Type.Const.REDIRECT_URL, redirectUrl), Type.Const.FORMAT_JSON);
        }
        if (context.getContextParams().containsKey(Optional.OBSERVE_SILENCE)) {
            return View.redirectView(redirectUrl);
        }
        return WebUtils.buildErrorView(WebContext.getContext().getOwner(), _message, redirectUrl, BlurObject.bind(context.getOwner().getConfig().getParam(Optional.REDIRECT_TIME_INTERVAL)).toIntValue());
    }

}
