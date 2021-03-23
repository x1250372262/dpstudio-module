package com.dpstudio.module.security.service;

import com.dpstudio.module.security.vo.PermissionVO;

import java.util.List;

/**
 * @Author: mengxiang.
 * @create: 2021-03-15 14:09
 * @Description:
 */
public interface IPermissionService {

    /**
     * 权限下拉选
     * @return
     * @throws Exception
     */
     List<PermissionVO> select() throws Exception;
}
