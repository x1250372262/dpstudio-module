package com.dpstudio.dev.security.proxy;

import com.dpstudio.dev.security.IAuthenticator;
import com.dpstudio.dev.security.annotation.Permission;
import com.dpstudio.dev.security.annotation.Security;
import com.dpstudio.dev.security.exception.PermissionException;
import net.ymate.platform.core.beans.annotation.Order;
import net.ymate.platform.core.beans.annotation.Proxy;
import net.ymate.platform.core.beans.proxy.IProxy;
import net.ymate.platform.core.beans.proxy.IProxyChain;
import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * @Author: 徐建鹏.
 * @Date: 2019-01-17.
 * @Time: 15:38.
 * @Description:
 */
@Proxy(annotation = Security.class, order = @Order(2000))
public class SecurityProxy implements IProxy {

    private static final Log _LOG = LogFactory.getLog(SecurityProxy.class);

    private Boolean checkPermission(String code) throws Throwable {

        IAuthenticator iAuthenticator = com.dpstudio.dev.security.Security.get().getModuleCfg().getAuthenticator();

        if (iAuthenticator == null) {
            throw new NullArgumentException("authenticator_class");
        }
        //是总管理
        if (iAuthenticator.isFounder()) {
            return true;
        }
        List<String> userPermissionCodes = iAuthenticator.userPermission();
        if (userPermissionCodes == null) {
            return false;
        }
        if (!userPermissionCodes.contains(code)) {
            return false;
        }
        return true;
    }

    @Override
    public Object doProxy(IProxyChain proxyChain) throws Throwable {
        if (!com.dpstudio.dev.security.Security.get().getModuleCfg().enabled()) {
            return proxyChain.doProxyChain();
        }
        if (proxyChain.getTargetClass().isAnnotationPresent(Security.class)) {
            Permission[] permissions = proxyChain.getTargetMethod().getAnnotationsByType(Permission.class);
            if (permissions != null && permissions.length > 0) {
                for (Permission permission : permissions) {
                    if (StringUtils.isNotBlank(permission.code())) {
                        if (!checkPermission(permission.code())) {
                            String errMsg = "您还没有此操作的权限";
                            if (proxyChain.getProxyFactory().getOwner().getConfig().isDevelopMode() && _LOG.isDebugEnabled()) {
                                _LOG.debug(errMsg);
                            }
                            throw new PermissionException();
                        }
                    }
                }
                return proxyChain.doProxyChain();
            }
        }

        return proxyChain.doProxyChain();
    }
}
