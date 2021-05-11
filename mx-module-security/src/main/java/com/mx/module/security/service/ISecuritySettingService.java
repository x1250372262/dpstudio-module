package com.mx.module.security.service;

import com.mx.dev.core.R;
import com.mx.module.security.dto.SecuritySettingDTO;
import com.mx.module.security.vo.detail.SecuritySettingDetailVO;

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
