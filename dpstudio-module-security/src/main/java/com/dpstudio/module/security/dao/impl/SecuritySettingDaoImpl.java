package com.dpstudio.module.security.dao.impl;

import com.dpstudio.module.security.dao.ISecuritySettingDao;
import com.dpstudio.module.security.model.SecuritySetting;
import net.ymate.platform.core.beans.annotation.Bean;
import net.ymate.platform.core.persistence.Fields;
import net.ymate.platform.persistence.jdbc.JDBC;

import java.util.List;

@Bean
public class SecuritySettingDaoImpl implements ISecuritySettingDao {

    @Override
    public SecuritySetting findByClientName(String clientName, String... fields) throws Exception{
        return SecuritySetting.builder().clientName(clientName).build().findFirst(Fields.create(fields));
    }

    @Override
    public SecuritySetting update(SecuritySetting securitySetting, String... fields) throws Exception {
        return securitySetting.update(Fields.create(fields));
    }

    @Override
    public List<SecuritySetting> createAll(List<SecuritySetting> securitySettingList) throws Exception {
        return JDBC.get().openSession(session -> session.insert(securitySettingList));
    }

    @Override
    public SecuritySetting create(SecuritySetting securitySetting) throws Exception {
        return securitySetting.save();
    }

    @Override
    public SecuritySetting findByClientName(String clientName) throws Exception {
        return SecuritySetting.builder().clientName(clientName).build().findFirst();
    }
}
