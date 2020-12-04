package com.dpstudio.dev.security.jwt;

import com.dpstudio.dev.core.R;
import com.dpstudio.dev.core.code.C;
import com.dpstudio.dev.support.jwt.JwtBean;
import com.dpstudio.dev.support.jwt.JwtConfig;
import com.dpstudio.dev.support.jwt.JwtHelper;
import net.ymate.platform.commons.json.JsonWrapper;
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

    public static class Store {
        private static final ThreadLocal<Map<String, Object>> jwtThreadLocal = new ThreadLocal<>();


        public static void setPara(Map<String, Object> map) {
            jwtThreadLocal.set(map);
        }

        public static void removePara() {
            jwtThreadLocal.remove();
        }

        public static Object getPara(String key) {
            Map<String, Object> map = jwtThreadLocal.get();
            return map == null ? null : map.get(key);
        }

        public static Map<String, Object> getParas() {
            return jwtThreadLocal.get();
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

    public R create(JwtConfig jwtConfig) {
        return build(jwtConfig);
    }

    public R build(JwtConfig jwtConfig) {
        JwtBean jwtBean = JwtHelper.get(jwtConfig)
                .attrs(attrMap)
                .create();
        if (StringUtils.isBlank(jwtBean.getToken())) {
            return R.create(C.JWT_CREATE_ERROR.getCode()).msg(C.JWT_CREATE_ERROR.getMsg());
        }
        if (jwtConfig.autoResponse()) {
            WebContext.getResponse().setHeader(jwtConfig.getHeaderName(), JsonWrapper.toJsonString(jwtBean,false,true));
        }
        return R.ok().attr("jwtToken", jwtBean);
    }

    public R create() {
        return build(JWT_CONFIG);
    }

}
