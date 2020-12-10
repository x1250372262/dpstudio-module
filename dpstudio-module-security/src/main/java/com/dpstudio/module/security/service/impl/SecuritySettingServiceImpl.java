package com.dpstudio.module.security.service.impl;

import com.dpstudio.dev.core.R;
import com.dpstudio.dev.core.code.C;
import com.dpstudio.dev.utils.BeanUtils;
import com.dpstudio.module.security.SecurityCache;
import com.dpstudio.module.security.dao.ISecuritySettingDao;
import com.dpstudio.module.security.model.SecuritySetting;
import com.dpstudio.module.security.service.ISecuritySettingService;
import com.dpstudio.module.security.vo.detail.SecuritySettingDetailVO;
import com.dpstudio.module.security.vo.op.SecuritySettingVO;
import net.ymate.platform.commons.util.DateTimeUtils;
import net.ymate.platform.core.beans.annotation.Bean;
import net.ymate.platform.core.beans.annotation.Inject;

@Bean
public class SecuritySettingServiceImpl implements ISecuritySettingService {

    @Inject
    private ISecuritySettingDao iSecuritySettingDao;

    @Override
    public R update(String id, Long lastModifyTime, SecuritySettingVO securitySettingVO) throws Exception {
        SecuritySetting securitySetting = iSecuritySettingDao.findById(id);
        if (securitySetting == null) {
            return R.create(C.NO_DATA.getCode()).msg(C.NO_DATA.getMsg());
        }
        if (!R.checkVersion(lastModifyTime, securitySetting.getLastModifyTime())) {
            return R.create(C.VERSION_NOT_SAME.getCode()).msg(C.VERSION_NOT_SAME.getMsg());
        }
        securitySetting = BeanUtils.duplicate(securitySettingVO, securitySetting, (s, t) -> {
            t.setLastModifyTime(DateTimeUtils.currentTimeMillis());
            t.setLastModifyUser(SecurityCache.userId());
        });
        securitySetting = iSecuritySettingDao.update(securitySetting, SecuritySetting.FIELDS.LOGIN_LOG_STATUS,
                SecuritySetting.FIELDS.LOGIN_ERROR_COUNT, SecuritySetting.FIELDS.LOGIN_ERROR_TIME,
                SecuritySetting.FIELDS.LOGIN_ERROR_STATUS, SecuritySetting.FIELDS.LOGIN_NOT_IP_STATUS,
                SecuritySetting.FIELDS.LOGIN_NOT_IP_NOTICE,SecuritySetting.FIELDS.LAST_MODIFY_TIME, SecuritySetting.FIELDS.LAST_MODIFY_USER);
        return R.result(securitySetting);
    }

    @Override
    public SecuritySettingDetailVO detail(String id) throws Exception {
        SecuritySetting securitySetting = iSecuritySettingDao.findById(id);
        return BeanUtils.copy(securitySetting, SecuritySettingDetailVO::new);
    }
}
