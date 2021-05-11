package com.mx.module.security.service.impl;

import com.mx.dev.code.C;
import com.mx.dev.core.R;
import com.mx.dev.utils.BeanUtils;
import com.mx.module.security.SecurityCache;
import com.mx.module.security.core.Code;
import com.mx.module.security.dao.ISecuritySettingDao;
import com.mx.module.security.dto.SecuritySettingDTO;
import com.mx.module.security.model.SecurityAdmin;
import com.mx.module.security.model.SecuritySetting;
import com.mx.module.security.service.ISecuritySettingService;
import com.mx.module.security.vo.detail.SecuritySettingDetailVO;
import net.ymate.platform.commons.util.DateTimeUtils;
import net.ymate.platform.commons.util.UUIDUtils;
import net.ymate.platform.core.beans.annotation.Bean;
import net.ymate.platform.core.beans.annotation.Inject;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Bean
public class SecuritySettingServiceImpl implements ISecuritySettingService {

    @Inject
    private ISecuritySettingDao iSecuritySettingDao;

    @Override
    public R update(Long lastModifyTime, SecuritySettingDTO securitySettingDTO) throws Exception {
        SecurityAdmin securityAdmin = SecurityCache.AdminCache.getPara(SecurityCache.userId());
        if (securityAdmin == null) {
            return R.create(Code.SECURITY_ADMIN_NOT_EXIST.getCode())
                    .msg(Code.SECURITY_ADMIN_NOT_EXIST.getMsg());
        }
        SecuritySetting securitySetting = iSecuritySettingDao.findByClientName(securityAdmin.getClientName());
        if (securitySetting == null) {
            return R.create(C.NO_DATA.getCode()).msg(C.NO_DATA.getMsg());
        }
        if (!R.checkVersion(lastModifyTime, securitySetting.getLastModifyTime())) {
            return R.create(C.VERSION_NOT_SAME.getCode()).msg(C.VERSION_NOT_SAME.getMsg());
        }
        securitySetting = BeanUtils.duplicate(securitySettingDTO, securitySetting, (s, t) -> {
            t.setLastModifyTime(DateTimeUtils.currentTimeMillis());
            t.setLastModifyUser(SecurityCache.userId());
        });
        securitySetting = iSecuritySettingDao.update(securitySetting, SecuritySetting.FIELDS.LOGIN_LOG_STATUS,
                SecuritySetting.FIELDS.LOGIN_ERROR_COUNT, SecuritySetting.FIELDS.LOGIN_ERROR_TIME,
                SecuritySetting.FIELDS.LOGIN_ERROR_STATUS, SecuritySetting.FIELDS.LOGIN_NOT_IP_STATUS,
                SecuritySetting.FIELDS.LOGIN_NOT_IP_NOTICE, SecuritySetting.FIELDS.LAST_MODIFY_TIME, SecuritySetting.FIELDS.LAST_MODIFY_USER);
        return R.result(securitySetting);
    }

    @Override
    public SecuritySettingDetailVO detail(String clientName) throws Exception {
        if (StringUtils.isBlank(clientName)) {
            SecurityAdmin loginAdmin = SecurityCache.AdminCache.getPara(SecurityCache.userId());
            if (loginAdmin == null) {
                return new SecuritySettingDetailVO();
            }
            clientName = loginAdmin.getClientName();
        }
        SecuritySetting securitySetting = iSecuritySettingDao.findByClientName(clientName);
        return BeanUtils.copy(securitySetting, SecuritySettingDetailVO::new);
    }


    private SecuritySetting initSecuritySetting(String clientName) throws Exception {
        String id = UUIDUtils.UUID();
        return SecuritySetting.builder()
                .id(id)
                .clientName(clientName)
                .lastModifyTime(DateTimeUtils.currentTimeMillis())
                .lastModifyUser(id)
                .build();
    }


    @Override
    public R init(String clientName) throws Exception {
        if (StringUtils.isBlank(clientName)) {
            SecuritySetting securitySetting = iSecuritySettingDao.findByClientName(clientName);
            if (securitySetting == null) {
                securitySetting = initSecuritySetting("");
                securitySetting = iSecuritySettingDao.create(securitySetting);
            }
            return R.result(securitySetting);
        } else {
            if (!clientName.contains("|")) {
                SecuritySetting securitySetting = iSecuritySettingDao.findByClientName(clientName);
                if (securitySetting == null) {
                    securitySetting = initSecuritySetting(clientName);
                    securitySetting = iSecuritySettingDao.create(securitySetting);
                }
                return R.result(securitySetting);
            }
            String[] clientNameArray = clientName.split("\\|");
            List<SecuritySetting> securitySettingList = new ArrayList<>();
            for (String clientNameStr : clientNameArray) {
                SecuritySetting securitySetting = iSecuritySettingDao.findByClientName(clientNameStr);
                if (securitySetting == null) {
                    securitySettingList.add(initSecuritySetting(clientNameStr));
                }
            }
            if (securitySettingList.isEmpty()) {
                return R.ok();
            }
            securitySettingList = iSecuritySettingDao.createAll(securitySettingList);
            return R.result(securitySettingList);
        }

    }
}
