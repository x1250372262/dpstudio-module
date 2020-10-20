package com.dpstudio.module.security.service.impl;

import com.dpstudio.dev.core.R;
import com.dpstudio.dev.core.code.C;
import com.dpstudio.dev.utils.BeanUtils;
import com.dpstudio.module.security.core.CommonMethod;
import com.dpstudio.module.security.model.SecuritySetting;
import com.dpstudio.module.security.dao.ISecuritySettingDao;
import com.dpstudio.module.security.service.ISecuritySettingService;
import com.dpstudio.module.security.vo.SecuritySettingDetailVO;
import com.dpstudio.module.security.vo.SecuritySettingOPVO;
import net.ymate.platform.core.beans.annotation.Bean;
import net.ymate.platform.core.beans.annotation.Inject;
import net.ymate.platform.core.util.DateTimeUtils;

/**
 * @Author: 刘玉奇.
 * @Date: 2020/10/17.
 * @Time: 9:39.
 * @Description:
 */
@Bean
public class SecuritySettingServiceImpl implements ISecuritySettingService {

    @Inject
    private ISecuritySettingDao iSecuritySettingDao;

    @Override
    public R update(String id, Long lastModifyTime, SecuritySettingOPVO securitySettingOPVO) throws Exception {
        SecuritySetting securitySetting = iSecuritySettingDao.findById(id);
        if (securitySetting == null) {
            return R.create(C.NO_DATA.getCode()).msg(C.NO_DATA.getMsg());
        }
        if (!R.checkVersion(lastModifyTime, securitySetting.getLastModifyTime())) {
            return R.create(C.VERSION_NOT_SAME.getCode()).msg(C.VERSION_NOT_SAME.getMsg());
        }
        securitySetting = BeanUtils.copy(securitySettingOPVO, SecuritySetting::new, (securitySettingOPVOCopy, securitySettingCopy) -> {
            securitySettingCopy.setId(id);
            securitySettingCopy.setLastModifyTime(DateTimeUtils.currentTimeMillis());
            securitySettingCopy.setLastModifyUser(CommonMethod.userId());
        });
        securitySetting = iSecuritySettingDao.update(securitySetting, SecuritySetting.FIELDS.LOGIN_LOG_STATUS,
                SecuritySetting.FIELDS.LOGIN_ERROR_COUNT, SecuritySetting.FIELDS.LOGIN_ERROR_TIME,
                SecuritySetting.FIELDS.LOGIN_ERROR_STATUS, SecuritySetting.FIELDS.LOGIN_UNLOCK_FOUNDER,
                SecuritySetting.FIELDS.LOGIN_NOT_IP_STATUS, SecuritySetting.FIELDS.LOGIN_NOT_IP_NOTICE,
                SecuritySetting.FIELDS.LOGIN_CLIENT_STATUS, SecuritySetting.FIELDS.LOGIN_CLIENT_TYPE,
                SecuritySetting.FIELDS.LAST_MODIFY_TIME, SecuritySetting.FIELDS.LAST_MODIFY_USER);
        return R.result(securitySetting);
    }

    @Override
    public SecuritySettingDetailVO detail(String id) throws Exception {
        SecuritySetting securitySetting = iSecuritySettingDao.findById(id);
        return BeanUtils.copy(securitySetting, SecuritySettingDetailVO::new);
    }
}
