package com.dpstudio.module.security.service;

import com.dpstudio.dev.core.R;
import com.dpstudio.module.security.vo.detail.SecuritySettingDetailVO;
import com.dpstudio.module.security.vo.op.SecuritySettingVO;

public interface ISecuritySettingService {

    /**
     * 修改安全设置
     *
     * @param lastModifyTime
     * @param securitySettingVO
     * @return
     * @throws Exception
     */
    R update(Long lastModifyTime, SecuritySettingVO securitySettingVO) throws Exception;

    /**
     * 设置详情
     *
     * @param clientName
     * @return
     * @throws Exception
     */
    SecuritySettingDetailVO detail(String clientName) throws Exception;


    /**
     * 模块启动初始化表
     * @param clientName
     * @return
     * @throws Exception
     */
    R init(String clientName) throws Exception;

}
