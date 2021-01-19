package com.dpstudio.module.security.interCeptor;

import com.dpstudio.dev.core.R;
import com.dpstudio.dev.core.V;
import com.dpstudio.dev.core.code.C;
import com.dpstudio.dev.security.ISecurityConfig;
import com.dpstudio.dev.security.Security;
import com.dpstudio.dev.security.jwt.JWT;
import com.dpstudio.dev.support.jwt.JwtBean;
import com.dpstudio.dev.support.jwt.JwtConfig;
import com.dpstudio.dev.support.jwt.JwtHelper;
import com.dpstudio.module.security.SecurityCache;
import com.dpstudio.module.security.core.Code;
import com.dpstudio.module.security.dao.ISecurityAdminDao;
import com.dpstudio.module.security.model.SecurityAdmin;
import net.ymate.platform.commons.json.JsonWrapper;
import net.ymate.platform.commons.util.DateTimeUtils;
import net.ymate.platform.core.YMP;
import net.ymate.platform.core.beans.intercept.IInterceptor;
import net.ymate.platform.core.beans.intercept.InterceptContext;
import net.ymate.platform.webmvc.context.WebContext;
import net.ymate.platform.webmvc.view.IView;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static com.dpstudio.dev.security.jwt.JWT.JWT_CONFIG;

/**
 * @author xujianpeng
 * @Date 2020.06.13
 * @Time: 20:40
 * @Description: jwt拦截器
 */
public class JwtCheckInterceptor implements IInterceptor {

    private IView timeOut() {
        return V.view(R.create(Code.SECURITY_ADMIN_INVALID_OR_TIMEOUT.getCode()).msg(Code.SECURITY_ADMIN_INVALID_OR_TIMEOUT.getMsg()));
    }

    @Override
    public Object intercept(InterceptContext context) {


        // 判断当前拦截器执行方向
        if (Direction.BEFORE.equals(context.getDirection())) {

            HttpServletRequest request = WebContext.getRequest();
            String token = request.getHeader(JWT_CONFIG.getHeaderName());

            if (StringUtils.isBlank(token) && StringUtils.isBlank(JWT_CONFIG.getParamName())) {
                token = request.getParameter(JWT_CONFIG.getParamName());
            }

            if (StringUtils.isBlank(token)) {
                return timeOut();
            }
            //先看看缓存有没有
            JwtBean jwtBean = SecurityCache.JwtCache.getPara(token);
            if (jwtBean == null || StringUtils.isBlank(jwtBean.getToken()) || (jwtBean.getVerifyTime() > 0 && jwtBean.getVerifyTime() < DateTimeUtils.currentTimeMillis())) {
                return timeOut();
            }
            if (!jwtBean.getToken().equals(token)) {
                return timeOut();
            }
            R r = JwtHelper.parse(token);
            if (!Objects.equals(r.code(), C.SUCCESS.getCode())) {
                return timeOut();
            }
            String uid = r.attr("uid");
            if (StringUtils.isBlank(uid)) {
                return timeOut();
            }

            try {
                SecurityAdmin securityAdmin = YMP.get().getBeanFactory().getBean(ISecurityAdminDao.class).findById(uid, null);
                if (securityAdmin == null) {
                    return timeOut();
                }
                JWT.Store.setPara(token, r.attrs());
                SecurityCache.AdminCache.setPara(securityAdmin);
            } catch (Exception e) {
                return timeOut();
            }
            //验证通过之后判断是否需要刷新token
//            if (jwtBean.getVerifyTime() > 0 && jwtBean.getVerifyTime() - DateTimeUtils.currentTimeMillis() <= DateTimeUtils.MINUTE) {
//            R jwtResult = JWT.attr("uid", uid)
//                    .build();
//            if (!Objects.equals(jwtResult.code(), C.SUCCESS.getCode())) {
//                return jwtResult;
//            }
            //重新刷新时间
            jwtBean.setVerifyTime(DateTimeUtils.currentTimeMillis() + JWT_CONFIG.verifyTime());
            //放到缓存
            SecurityCache.JwtCache.removePara(token);
            SecurityCache.JwtCache.removeParaByAdminId(uid);
            SecurityCache.JwtCache.setPara(jwtBean);
            SecurityCache.JwtCache.setParaByAdminId(uid, jwtBean);
            if (JWT_CONFIG.autoResponse()) {
                WebContext.getResponse().setHeader(JWT_CONFIG.getHeaderName(), JsonWrapper.toJsonString(jwtBean, false, true));
            }
//            }
        }
        return null;
    }
}
