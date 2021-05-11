package com.mx.module.security.interCeptor;

import com.mx.dev.core.R;
import com.mx.dev.core.V;
import com.mx.dev.code.C;
import com.mx.dev.security.Security;
import com.mx.dev.security.jwt.JWT;
import com.mx.dev.support.jwt.JwtHelper;
import com.mx.module.security.SecurityCache;
import net.ymate.platform.core.beans.intercept.IInterceptor;
import net.ymate.platform.core.beans.intercept.InterceptContext;
import net.ymate.platform.webmvc.context.WebContext;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author mengxiang
 * @Date 2020.06.13
 * @Time: 20:40
 * @Description: jwt退出拦截器
 */
public class JwtOutInterceptor implements IInterceptor {

    @Override
    public Object intercept(InterceptContext context) {
        // 判断当前拦截器执行方向
        if (Direction.BEFORE.equals(context.getDirection())) {
            HttpServletRequest request = WebContext.getRequest();
            String token = request.getHeader(JWT.JWT_CONFIG.getHeaderName());

            if (StringUtils.isBlank(token) && StringUtils.isBlank(JWT.JWT_CONFIG.getParamName())) {
                token = request.getParameter(JWT.JWT_CONFIG.getParamName());
            }

            String clientName = request.getHeader(Security.get().getConfig().headerClientName());

            if (StringUtils.isBlank(clientName) && StringUtils.isBlank(Security.get().getConfig().paramClientName())) {
                clientName = request.getParameter(Security.get().getConfig().paramClientName());
            }

            if (StringUtils.isBlank(token)) {
                return null;
            }
            SecurityCache.JwtCache.removePara(token,clientName);
            R r = JwtHelper.parse(token);
            if (!Objects.equals(r.code(), C.SUCCESS.getCode())) {
                return V.view(r);
            }
            String uid = r.attr("uid");
            if (StringUtils.isBlank(uid)) {
                return null;
            }
            SecurityCache.JwtCache.removeParaByAdminId(uid,clientName);
            try {
                SecurityCache.AdminCache.removePara(uid);
            } catch (Exception e) {
                return null;
            }
            return null;
        }
        return null;
    }
}
