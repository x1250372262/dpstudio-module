package com.dpstudio.module.security.dao;

import com.dpstudio.module.security.model.SecuritySetting;

/**
 * @Author: 刘玉奇.
 * @Date: 2020/10/17.
 * @Time: 9:37.
 * @Description:
 */
public interface ISecuritySettingDao {

    /**
     * 根据id查询设置信息
     *
     * @param id
     * @param fields
     * @return
     * @throws Exception
     */
    SecuritySetting findById(String id, String... fields) throws Exception;

    /**
     * 修改设置信息
     *
     * @param securitySetting
     * @param fields
     * @return
     * @throws Exception
     */
    SecuritySetting update(SecuritySetting securitySetting, String... fields) throws Exception;

}
