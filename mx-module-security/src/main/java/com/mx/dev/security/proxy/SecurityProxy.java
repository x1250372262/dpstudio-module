package com.mx.dev.security.proxy;

import com.mx.dev.security.IAuthenticator;
import com.mx.dev.security.annotation.Group;
import com.mx.dev.security.annotation.Permission;
import com.mx.dev.security.annotation.Security;
import com.mx.dev.security.exception.PermissionException;
import net.ymate.platform.core.beans.annotation.Proxy;
import net.ymate.platform.core.beans.proxy.IProxy;
import net.ymate.platform.core.beans.proxy.IProxyChain;
import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * @Author: mengxiang.
 * @Date: 2019-01-17.
 * @Time: 15:38.
 * @Description:
 */
@Proxy(annotation = Security.class)
public class SecurityProxy implements IProxy {

    private static final Log LOG = LogFactory.getLog(SecurityProxy.class);

    private Boolean checkPermission(String code) throws Throwable {

        IAuthenticator iAuthenticator = com.mx.dev.security.Security.get().getConfig().authenticatorClass();

        if (iAuthenticator == null) {
            throw new NullArgumentException("authenticator_class");
        }
        //是总管理
        if (iAuthenticator.isFounder(null)) {
            return true;
        }
        List<String> userPermissionCodes = iAuthenticator.userPermissions(null);
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
        if (!com.mx.dev.security.Security.get().getConfig().isEnabled()) {
            return proxyChain.doProxyChain();
        }
        if (proxyChain.getTargetClass().isAnnotationPresent(Security.class)) {
            Group group = proxyChain.getTargetMethod().getAnnotation(Group.class);
            if(group == null){
                return proxyChain.doProxyChain();
            }
            Permission[] permissions = group.permissions();
            if (permissions.length>0) {
                for(Permission permission : permissions){
                    if (StringUtils.isNotBlank(permission.code())) {
                        if (!checkPermission(permission.code())) {
                            String errMsg = "您还没有此操作的权限";
                            if (proxyChain.getProxyFactory().getOwner().isDevEnv() && LOG.isDebugEnabled()) {
                                LOG.debug(errMsg);
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
