package com.dpstudio.module.security.dao;

import com.dpstudio.module.security.model.SecuritySetting;

import java.util.List;

public interface ISecuritySettingDao {

    /**
     * 根据clientName查询设置信息
     *
     * @param clientName
     * @param fields
     * @return
     * @throws Exception
     */
    SecuritySetting findByClientName(String clientName, String... fields) throws Exception;

    /**
     * 修改设置信息
     *
     * @param securitySetting
     * @param fields
     * @return
     * @throws Exception
     */
    SecuritySetting update(SecuritySetting securitySetting, String... fields) throws Exception;

    /**
     * 批量添加
     * @param securitySettingList
     * @return
     * @throws Exception
     */
    List<SecuritySetting> createAll(List<SecuritySetting> securitySettingList) throws Exception;

    /**
     * 添加
     * @param securitySetting
     * @return
     * @throws Exception
     */
    SecuritySetting create(SecuritySetting securitySetting) throws Exception;

    /**
     * 根据客户端名称查询
     * @param clientName
     * @return
     * @throws Exception
     */
    SecuritySetting findByClientName(String clientName) throws Exception;

}
