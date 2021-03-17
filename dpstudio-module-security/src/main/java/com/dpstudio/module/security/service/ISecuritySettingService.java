package com.dpstudio.module.security.service;

import com.dpstudio.dev.core.R;
import com.dpstudio.module.security.dto.SecuritySettingDTO;
import com.dpstudio.module.security.vo.detail.SecuritySettingDetailVO;

public interface ISecuritySettingService {

    /**
     * 修改安全设置
     *
     * @param lastModifyTime
     * @param securitySettingDTO
     * @return
     * @throws Exception
     */
    R update(Long lastModifyTime, SecuritySettingDTO securitySettingDTO) throws Exception;

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
     *
     * @param clientName
     * @return
     * @throws Exception
     */
    R init(String clientName) throws Exception;

}
