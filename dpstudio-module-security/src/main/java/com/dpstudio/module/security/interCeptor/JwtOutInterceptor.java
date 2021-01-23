package com.dpstudio.module.security.interCeptor;

import com.dpstudio.dev.core.R;
import com.dpstudio.dev.core.V;
import com.dpstudio.dev.code.C;
import com.dpstudio.dev.security.jwt.JWT;
import com.dpstudio.dev.support.jwt.JwtHelper;
import com.dpstudio.module.security.SecurityCache;
import com.dpstudio.module.security.dao.ISecurityAdminDao;
import com.dpstudio.module.security.model.SecurityAdmin;
import net.ymate.platform.commons.json.IJsonNodeWrapper;
import net.ymate.platform.commons.json.IJsonObjectWrapper;
import net.ymate.platform.commons.json.JsonWrapper;
import net.ymate.platform.core.YMP;
import net.ymate.platform.core.beans.intercept.IInterceptor;
import net.ymate.platform.core.beans.intercept.InterceptContext;
import net.ymate.platform.webmvc.context.WebContext;
import net.ymate.platform.webmvc.view.impl.JsonView;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author xujianpeng
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
            if (StringUtils.isBlank(token)) {
                return null;
            }
            SecurityCache.JwtCache.removePara(token);
            R r = JwtHelper.parse(token);
            if (!Objects.equals(r.code(), C.SUCCESS.getCode())) {
                return V.view(r);
            }
            String uid = r.attr("uid");
            if (StringUtils.isBlank(uid)) {
                return null;
            }
            SecurityCache.JwtCache.removeParaByAdminId(uid);
            try {
                SecurityAdmin securityAdmin = YMP.get().getBeanFactory().getBean(ISecurityAdminDao.class).findById(uid,null);
                if (securityAdmin == null) {
                    return null;
                }
                SecurityCache.AdminCache.removePara(securityAdmin.getId());
            } catch (Exception e) {
                return null;
            }
            return null;
        }
        return null;
    }
}
