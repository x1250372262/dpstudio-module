package com.dpstudio.module.security;

import com.dpstudio.dev.security.Security;
import com.dpstudio.dev.security.jwt.JWT;
import com.dpstudio.dev.support.jwt.JwtBean;
import com.dpstudio.module.security.core.SecurityConstants;
import com.dpstudio.module.security.model.SecurityAdmin;
import net.ymate.platform.cache.Caches;
import net.ymate.platform.cache.ICaches;
import net.ymate.platform.commons.lang.BlurObject;
import net.ymate.platform.webmvc.context.WebContext;
import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @Author: mengxiang.
 * @Date: 2020/6/13.
 * @Time: 8:18 下午.
 * @Description:
 */
public class SecurityCache {

    private static final int TIME_OUT = Security.get().getConfig().verifyTime() * 60;

    public static String userId() {
        return (String) Optional.ofNullable(JWT.Store.getPara(token(), SecurityConstants.JWT_ADMIN_ID_KEY))
                .orElse("1");
    }
    public static String userId(String token) {
        return (String) Optional.ofNullable(JWT.Store.getPara(token, SecurityConstants.JWT_ADMIN_ID_KEY))
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
            SecurityCache.setPara(SecurityAdmin.class.getName(), securityAdmin.getId(), securityAdmin, TIME_OUT);
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

        private static String fixKey(String key, String clientName) {
            if(StringUtils.isBlank(clientName)){
                throw new NullArgumentException("clientName");
            }
            return key.concat("_").concat(clientName);
        }

        public static void setParaByAdminId(String adminId, JwtBean jwtBean, String clientName) {
            SecurityCache.setPara(SecurityConstants.JWT_CACHE_ADIN_NAME, fixKey(adminId, clientName), jwtBean, TIME_OUT);
        }

        public static void setPara(JwtBean jwtBean, String clientName) {
            SecurityCache.setPara(SecurityConstants.JWT_CACHE_NAME, fixKey(jwtBean.getToken(), clientName), jwtBean, TIME_OUT);
        }

        public static void removeParaByAdminId(String adminId, String clientName) {
            SecurityCache.removePara(SecurityConstants.JWT_CACHE_ADIN_NAME, fixKey(adminId, clientName));
        }

        public static void removePara(String token, String clientName) {
            SecurityCache.removePara(SecurityConstants.JWT_CACHE_NAME, fixKey(token, clientName));
        }

        public static JwtBean getParaByAdminId(String adminId, String clientName) {
            Object object = SecurityCache.getPara(SecurityConstants.JWT_CACHE_ADIN_NAME, fixKey(adminId, clientName));
            return object == null ? null : (JwtBean) object;
        }

        public static JwtBean getPara(String token, String clientName) {
            Object object = SecurityCache.getPara(SecurityConstants.JWT_CACHE_NAME, fixKey(token, clientName));
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
