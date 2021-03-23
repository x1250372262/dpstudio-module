package com.dpstudio.module.security.service.impl;

import com.dpstudio.dev.security.Security;
import com.dpstudio.dev.security.bean.GroupBean;
import com.dpstudio.dev.security.bean.PermissionBean;
import com.dpstudio.dev.utils.ListUtils;
import com.dpstudio.module.security.SecurityCache;
import com.dpstudio.module.security.core.SecurityConstants;
import com.dpstudio.module.security.model.SecurityAdmin;
import com.dpstudio.module.security.service.IPermissionService;
import com.dpstudio.module.security.vo.PermissionVO;
import net.ymate.platform.core.beans.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: mengxiang.
 * @create: 2021-03-15 14:11
 * @Description:
 */
@Bean
public class PermissionServiceImpl implements IPermissionService {

    private void fillData(List<GroupBean> groupBeans, List<PermissionVO> permissionVOS, String clientName) {
        for(GroupBean groupBean : groupBeans){
            PermissionVO permissionVO = new PermissionVO(groupBean.getId(), groupBean.getName());
            List<PermissionBean> permissionBeans = Security.get().permissionList(groupBean.getId(), clientName);
            permissionVO.setPermissionBeans(permissionBeans);
            permissionVOS.add(permissionVO);
        }
//        groupBeans.forEach(groupBean -> {
//            PermissionVO permissionVO = new PermissionVO(groupBean.getId(), groupBean.getName());
//            List<PermissionBean> permissionBeans = Security.get().permissionList(groupBean.getId(), clientName);
//            permissionVO.setPermissionBeans(permissionBeans);
//            permissionVOS.add(permissionVO);
//        });
    }

    @Override
    public List<PermissionVO> select() throws Exception {
        List<PermissionVO> permissionVOS = new ArrayList<>();
        SecurityAdmin loginAdmin = SecurityCache.AdminCache.getPara(SecurityCache.userId());
        if (loginAdmin == null) {
            return permissionVOS;
        }
        //先获取客户端的权限
        List<GroupBean> groupBeans = Security.get().groupList(loginAdmin.getClientName());
        if (ListUtils.isNotEmpty(groupBeans)) {
            fillData(groupBeans, permissionVOS, loginAdmin.getClientName());
        }
        //在获取安全模块的权限
        List<GroupBean> groupSecurityBeans = Security.get().groupList(SecurityConstants.PERMISSION_CLIENT_NAME);
        if (ListUtils.isNotEmpty(groupSecurityBeans)) {
            fillData(groupSecurityBeans, permissionVOS, SecurityConstants.PERMISSION_CLIENT_NAME);
        }
        return permissionVOS;
    }
}
