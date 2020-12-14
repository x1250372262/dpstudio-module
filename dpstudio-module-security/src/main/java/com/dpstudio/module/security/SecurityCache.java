package com.dpstudio.module.security;

import com.dpstudio.dev.security.Security;
import com.dpstudio.dev.security.jwt.JWT;
import com.dpstudio.dev.support.jwt.JwtBean;
import com.dpstudio.module.security.model.SecurityAdmin;
import net.ymate.platform.cache.Caches;
import net.ymate.platform.cache.ICaches;
import net.ymate.platform.commons.lang.BlurObject;
import net.ymate.platform.webmvc.context.WebContext;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @Author: xujianpeng.
 * @Date: 2020/6/13.
 * @Time: 8:18 下午.
 * @Description:
 */
public class SecurityCache {

    private static final int TIME_OUT = Security.get().getConfig().verifyTime() * 60;

    public static String userId() {
        return (String) Optional.ofNullable(JWT.Store.getPara(token(), "uid"))
                .orElse("1");
    }

    public static String token() {
        HttpServletRequest request = WebContext.getRequest();
        String token = request.getHeader(JWT.JWT_CONFIG.getHeaderName());

        if (StringUtils.isBlank(token) && StringUtils.isBlank(JWT.JWT_CONFIG.getParamName())) {
            token = request.getParameter(JWT.JWT_CONFIG.getParamName());
        }
        return token;
    }

    private static final ICaches CACHES = Caches.get();

    public static class AdminCache {

        public static void setPara(SecurityAdmin securityAdmin) {

            SecurityCache.setPara(AdminCache.class.getName(), securityAdmin.getId(), securityAdmin, TIME_OUT);
        }

        public static void removePara(String adminId) {
            SecurityCache.removePara(SecurityAdmin.class.getName(), adminId);
        }

        public static SecurityAdmin getPara(String adminId) {
            Object object = SecurityCache.getPara(SecurityAdmin.class.getName(), adminId);
            return object == null ? null : (SecurityAdmin) object;
        }
    }

    public static class JwtCache {

        public static void setParaByAdminId(String adminId, JwtBean jwtBean) {
            SecurityCache.setPara("jwtCacheByAdminId", adminId, jwtBean, TIME_OUT);
        }

        public static void setPara(JwtBean jwtBean) {
            SecurityCache.setPara("jwtCache", jwtBean.getToken(), jwtBean, TIME_OUT);
        }

        public static void removeParaByAdminId(String adminId) {
            SecurityCache.removePara("jwtCacheByAdminId", adminId);
        }

        public static void removePara(String token) {
            SecurityCache.removePara("jwtCache", token);
        }

        public static JwtBean getParaByAdminId(String adminId) {
            Object object = SecurityCache.getPara("jwtCacheByAdminId", adminId);
            return object == null ? null : (JwtBean) object;
        }

        public static JwtBean getPara(String token) {
            Object object = SecurityCache.getPara("jwtCache", token);
            return object == null ? null : (JwtBean) object;
        }


    }

    public static void setPara(String cacheName, String cacheKey, Object cacheValue, long timeout) {
        CACHES.put(cacheName, cacheKey, cacheValue, BlurObject.bind(timeout).toIntValue());
    }

    public static void removePara(String cacheName, String cacheKey) {
        CACHES.remove(cacheName, cacheKey);
    }

    public static Object getPara(String cacheName, String cacheKey) {
        return CACHES.get(cacheName, cacheKey);
    }

}
