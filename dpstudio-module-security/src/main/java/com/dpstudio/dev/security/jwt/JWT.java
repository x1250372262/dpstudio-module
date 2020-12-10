package com.dpstudio.dev.security.jwt;

import com.dpstudio.dev.core.R;
import com.dpstudio.dev.core.code.C;
import com.dpstudio.dev.security.ISecurityConfig;
import com.dpstudio.dev.security.Security;
import com.dpstudio.dev.support.jwt.JwtBean;
import com.dpstudio.dev.support.jwt.JwtConfig;
import com.dpstudio.dev.support.jwt.JwtHelper;
import net.ymate.platform.commons.json.JsonWrapper;
import net.ymate.platform.commons.util.DateTimeUtils;
import net.ymate.platform.webmvc.context.WebContext;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xujianpeng.
 * @Date: 2020/6/13.
 * @Time: 8:18 下午.
 * @Description:
 */
public class JWT {

    public static final JwtConfig JWT_CONFIG = JwtConfig.builder();

    public static final JWT ME = new JWT();

    private static Map<String, Object> attrMap = new HashMap<>();

    private JWT() {

    }

    static {
        ISecurityConfig iSecurityConfig = Security.get().getConfig();
        JWT_CONFIG.setSecret(iSecurityConfig.secret());
        JWT_CONFIG.setParamName(iSecurityConfig.paramName());
        JWT_CONFIG.setHeaderName(iSecurityConfig.headerName());
        JWT_CONFIG.setAutoResponse(iSecurityConfig.autoResponse());
        JWT_CONFIG.setVerifyTime(DateTimeUtils.MINUTE * iSecurityConfig.verifyTime());
    }

    public static class Store {
        private static final ThreadLocal<Map<String, Object>> JWT_THREAD_LOCAL = new ThreadLocal<>();


        public static void setPara(Map<String, Object> map) {
            JWT_THREAD_LOCAL.set(map);
        }

        public static void removePara() {
            JWT_THREAD_LOCAL.remove();
        }

        public static Object getPara(String key) {
            Map<String, Object> map = JWT_THREAD_LOCAL.get();
            return map == null ? null : map.get(key);
        }

        public static Map<String, Object> getParas() {
            return JWT_THREAD_LOCAL.get();
        }

    }


    public static JWT attrs(Map<String, Object> attrs) {
        attrMap = attrs;
        return ME;
    }

    public static JWT attr(String attrKey, Object attrValue) {
        attrMap.put(attrKey, attrValue);
        return ME;
    }

    public static JWT attr(String attrKey, Object attrValue, Object defaultValue) {
        attrMap.put(attrKey, ObjectUtils.defaultIfNull(attrValue, defaultValue));
        return ME;
    }

    public static R parse(String token) {
        return JwtHelper.parse(token);
    }

    public R build() {
        JwtBean jwtBean = JwtHelper.get(JWT_CONFIG)
                .attrs(attrMap)
                .create();
        if (StringUtils.isBlank(jwtBean.getToken())) {
            return R.create(C.JWT_CREATE_ERROR.getCode()).msg(C.JWT_CREATE_ERROR.getMsg());
        }
        if (JWT_CONFIG.autoResponse()) {
            WebContext.getResponse().setHeader(JWT_CONFIG.getHeaderName(), JsonWrapper.toJsonString(jwtBean, false, true));
        }
        return R.ok().attr("jwtToken", jwtBean);
    }

}
