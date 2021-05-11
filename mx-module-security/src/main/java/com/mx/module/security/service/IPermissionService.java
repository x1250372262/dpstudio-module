package com.mx.module.security.service;

import com.mx.module.security.vo.PermissionVO;

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
