//package com.dpstudio.dev.security.taglib;
//
//import cn.hutool.core.collection.CollectionUtil;
//import com.dpstudio.dev.security.IAuthenticator;
//import com.dpstudio.dev.security.Security;
//import org.apache.commons.lang.NullArgumentException;
//import org.apache.commons.lang3.StringUtils;
//
//import javax.servlet.jsp.tagext.BodyTagSupport;
//import java.util.List;
//
///**
// * @author mengxiang
// * @Date 2020.01.02.
// * @Time: 14:30.
// * @Description: 权限jsp标签
// */
//public class PermissionTag extends BodyTagSupport {
//
//    private String permission;
//
//    private Boolean checkPermission(String permission) throws Exception {
//
//        //如果权限是空的 证明不需要
//        if (StringUtils.isBlank(permission)) {
//            return true;
//        }
//        IAuthenticator iAuthenticator = Security.get().getConfig().authenticatorClass();
//
//        if (iAuthenticator == null) {
//            throw new NullArgumentException("authenticator_class");
//        }
//
//        //是总管理
//        if (iAuthenticator.isFounder()) {
//            return true;
//        }
//
//        List<String> userPermissionCodes = iAuthenticator.userPermissions();
//
//        //这个人没有权限不行
//        if (CollectionUtil.isEmpty(userPermissionCodes)) {
//            return false;
//        }
//        //判断是否包含权限
//        return userPermissionCodes.contains(permission);
//    }
//
//
//    @Override
//    public int doStartTag() {
//        // 如果为true则显示文本中的内容，否则不显示
//        try {
//            if (checkPermission(permission)) {
//                return EVAL_BODY_INCLUDE;
//            } else {
//                return SKIP_BODY;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return SKIP_BODY;
//        }
//    }
//
//    @Override
//    public int doEndTag() {
//        return EVAL_PAGE;
//    }
//
//    public String getPermission() {
//        return permission;
//    }
//
//    public void setPermission(String permission) {
//        this.permission = permission;
//    }
//}
