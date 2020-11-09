package com.dpstudio.module.security.service;

import com.dpstudio.dev.core.R;
import com.dpstudio.module.security.vo.detail.SecuritySettingDetailVO;
import com.dpstudio.module.security.vo.op.SecuritySettingVO;

public interface ISecuritySettingService {

    /**
     * 修改安全设置
     *
     * @param id
     * @param lastModifyTime
     * @param securitySettingVO
     * @return
     * @throws Exception
     */
    R update(String id, Long lastModifyTime, SecuritySettingVO securitySettingVO) throws Exception;

    /**
     * 设置详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    SecuritySettingDetailVO detail(String id) throws Exception;

}
