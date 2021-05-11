package com.mx.module.wxminiprogram.impl;

import com.alibaba.fastjson.JSONObject;
import com.mx.dev.core.R;
import com.mx.module.wxminiprogram.ErrorCode;
import com.mx.module.wxminiprogram.IWxMimiProgramHandler;
import com.mx.module.wxminiprogram.WxMiniProgram;
import com.mx.module.wxminiprogram.bean.WxPhoneInfo;
import com.mx.module.wxminiprogram.bean.WxUserInfo;
import com.mx.module.wxminiprogram.dto.UserInfoDTO;
import com.mx.module.wxminiprogram.model.MimiprogramUser;
import net.ymate.platform.commons.util.DateTimeUtils;
import net.ymate.platform.commons.util.UUIDUtils;
import net.ymate.platform.core.persistence.Fields;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: mengxiang.
 * @Date: 2020/4/2.
 * @Time: 5:41 下午.
 * @Description: 默认数据处理实现类
 */
public class DefaultWxMimiProgramHandler implements IWxMimiProgramHandler {
    @Override
    public R handlerUserData(WxUserInfo wxUserInfo, String attach) throws Exception {
        boolean defaultHandlerByDatabase = WxMiniProgram.get().getConfig().dataHandlerDefaultByDatabases();
        if (defaultHandlerByDatabase) {
            MimiprogramUser mimiprogramUser = MimiprogramUser.builder().openId(wxUserInfo.getOpenId()).build().findFirst();
            if (mimiprogramUser == null) {
                //保存微信用户信息
                MimiprogramUser.builder().id(UUIDUtils.UUID())
                        .lastModifyTime(DateTimeUtils.currentTimeMillis())
                        .createTime(DateTimeUtils.currentTimeMillis())
                        .openId(wxUserInfo.getOpenId())
                        .build().save();
            }
        } else {
            System.out.println("默认数据处理实现输出微信用户信息:" + JSONObject.toJSONString(wxUserInfo));
        }

        return R.ok()
                .attr("token", wxUserInfo.getOpenId());
    }

    @Override
    public R handlerMobileData(String attach, String token, WxPhoneInfo wxPhoneInfo) throws Exception {
        boolean defaultHandlerByDatabase = WxMiniProgram.get().getConfig().dataHandlerDefaultByDatabases();
        if (defaultHandlerByDatabase) {
            if (StringUtils.isNotBlank(token)) {
                MimiprogramUser mimiprogramUser = MimiprogramUser.builder().openId(token).build().findFirst();
                if (mimiprogramUser != null) {
                    mimiprogramUser.setMobile(wxPhoneInfo.getPhoneNumber());
                    mimiprogramUser.update(Fields.create(MimiprogramUser.FIELDS.MOBILE));
                } else {
                    return R.create(ErrorCode.USER_INFO_NOT_EXISTS.getCode())
                            .msg(ErrorCode.USER_INFO_NOT_EXISTS.getMsg());
                }
            }
        } else {
            System.out.println("默认数据处理实现输出微信手机号信息:" + JSONObject.toJSONString(wxPhoneInfo));
        }

        return R.ok();
    }

    @Override
    public R updateUserData(UserInfoDTO userInfoDTO) throws Exception {
        boolean defaultHandlerByDatabase = WxMiniProgram.get().getConfig().dataHandlerDefaultByDatabases();
        if (defaultHandlerByDatabase) {
            MimiprogramUser mimiprogramUser = MimiprogramUser.builder().openId(userInfoDTO.getOpenId()).build().findFirst();
            if (mimiprogramUser != null) {
                mimiprogramUser.setAvatarUrl(userInfoDTO.getAvatarUrl());
                mimiprogramUser.setCity(userInfoDTO.getCity());
                mimiprogramUser.setCountry(userInfoDTO.getCountry());
                mimiprogramUser.setLastModifyTime(DateTimeUtils.currentTimeMillis());
                mimiprogramUser.setGender(userInfoDTO.getGender());
                mimiprogramUser.setNickName(userInfoDTO.getNickName());
                mimiprogramUser.setProvince(userInfoDTO.getProvince());
                mimiprogramUser.update(Fields.create(MimiprogramUser.FIELDS.AVATAR_URL, MimiprogramUser.FIELDS.CITY, MimiprogramUser.FIELDS.COUNTRY,
                        MimiprogramUser.FIELDS.LAST_MODIFY_TIME, MimiprogramUser.FIELDS.GENDER, MimiprogramUser.FIELDS.NICK_NAME, MimiprogramUser.FIELDS.PROVINCE));
                return R.ok()
                        .attr("token", mimiprogramUser.getOpenId());
            }

        } else {
            System.out.println("默认数据处理实现输出微信用户信息:" + JSONObject.toJSONString(userInfoDTO));
        }

        return R.ok();
    }
}