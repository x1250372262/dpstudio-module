package com.dpstudio.module.security.dao.impl;

import com.dpstudio.module.security.model.SecuritySetting;
import com.dpstudio.module.security.dao.ISecuritySettingDao;
import net.ymate.platform.core.beans.annotation.Bean;
import net.ymate.platform.persistence.Fields;

/**
 * @Author: 刘玉奇.
 * @Date: 2020/10/17.
 * @Time: 9:38.
 * @Description:
 */
@Bean
public class SecuritySettingDaoImpl implements ISecuritySettingDao {

    @Override
    public SecuritySetting findById(String id, String... fields) throws Exception {
        return SecuritySetting.builder().id(id).build().load(Fields.create(fields));
    }

    @Override
    public SecuritySetting update(SecuritySetting securitySetting, String... fields) throws Exception {
        return securitySetting.update(Fields.create(fields));
    }
}
