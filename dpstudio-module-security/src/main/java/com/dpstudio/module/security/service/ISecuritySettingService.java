package com.dpstudio.module.security.service;

import com.dpstudio.dev.core.R;
import com.dpstudio.module.security.vo.SecuritySettingDetailVO;
import com.dpstudio.module.security.vo.SecuritySettingOPVO;

/**
 * @Author: 刘玉奇.
 * @Date: 2020/10/17.
 * @Time: 9:38.
 * @Description:
 */
public interface ISecuritySettingService {

    /**
     * 修改安全设置
     *
     * @param id
     * @param lastModifyTime
     * @param securitySettingOPVO
     * @return
     * @throws Exception
     */
    R update(String id,  Long lastModifyTime,SecuritySettingOPVO securitySettingOPVO) throws Exception;

    /**
     * 设置详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    SecuritySettingDetailVO detail(String id) throws Exception;

}
