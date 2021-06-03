package com.mx.module.security.interCeptor;

import com.mx.dev.code.C;
import com.mx.dev.core.R;
import com.mx.dev.core.V;
import com.mx.dev.security.Security;
import com.mx.dev.security.jwt.JWT;
import com.mx.dev.support.jwt.JwtBean;
import com.mx.dev.support.jwt.JwtHelper;
import com.mx.module.security.SecurityCache;
import com.mx.module.security.core.Code;
import com.mx.module.security.dao.ISecurityAdminDao;
import com.mx.module.security.model.SecurityAdmin;
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
import java.util.concurrent.locks.ReentrantLock;

import static com.mx.dev.security.jwt.JWT.JWT_CONFIG;

/**
 * @author mengxiang
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
            try {
                HttpServletRequest request = WebContext.getRequest();
                String token = request.getHeader(JWT_CONFIG.getHeaderName());

                if (StringUtils.isBlank(token) && StringUtils.isBlank(JWT_CONFIG.getParamName())) {
                    token = request.getParameter(JWT_CONFIG.getParamName());
                }

                String clientName = request.getHeader(Security.get().getConfig().headerClientName());
                String client = request.getHeader("client");

                if (StringUtils.isNotBlank(client) && StringUtils.isNotBlank(client) && !client.equals(clientName)) {
                    return timeOut();
                }

                if (StringUtils.isBlank(clientName) && StringUtils.isBlank(Security.get().getConfig().paramClientName())) {
                    clientName = request.getParameter(Security.get().getConfig().paramClientName());
                }

                if (StringUtils.isBlank(token)) {
                    return timeOut();
                }
                //先看看缓存有没有
                JwtBean jwtBean = SecurityCache.JwtCache.getPara(token, clientName);
                if (jwtBean == null || StringUtils.isBlank(jwtBean.getToken()) || (jwtBean.getVerifyTime() > 0 && jwtBean.getVerifyTime() < DateTimeUtils.currentTimeMillis())) {
                    return timeOut();
                }
                if (!jwtBean.getToken().equals(token)) {
                    return timeOut();
                }
                R r = JwtHelper.parse(token);
                if (r == null || !Objects.equals(r.code(), C.SUCCESS.getCode())) {
                    return timeOut();
                }
                String uid = r.attr("uid");
                if (StringUtils.isBlank(uid)) {
                    return timeOut();
                }

                SecurityAdmin securityAdmin = YMP.get().getBeanFactory().getBean(ISecurityAdminDao.class).findById(uid, null);
                if (securityAdmin == null) {
                    return timeOut();
                }
                SecurityAdmin loginAdmin = SecurityCache.AdminCache.getPara(SecurityCache.userId());
                if (loginAdmin == null) {
                    SecurityCache.AdminCache.setPara(securityAdmin);
                }
                JWT.Store.setPara(token, r.attrs());
                //重新刷新时间
                jwtBean.setVerifyTime(DateTimeUtils.currentTimeMillis() + JWT_CONFIG.verifyTime());
                //放到缓存
//                SecurityCache.JwtCache.removePara(token, clientName);
//                SecurityCache.JwtCache.removeParaByAdminId(uid, clientName);
                SecurityCache.JwtCache.setPara(jwtBean, clientName);
                SecurityCache.JwtCache.setParaByAdminId(uid, jwtBean, clientName);
                if (JWT_CONFIG.autoResponse()) {
                    WebContext.getResponse().setHeader(JWT_CONFIG.getHeaderName(), JsonWrapper.toJsonString(jwtBean, false, true));
                }
            } catch (Exception e) {
                e.printStackTrace();
                return timeOut();
            }

        }
        return null;
    }
}
