package com.dpstudio.module.security.service.impl;

import com.dpstudio.dev.core.R;
import com.dpstudio.dev.core.code.C;
import com.dpstudio.dev.security.ISecurityConfig;
import com.dpstudio.dev.security.Security;
import com.dpstudio.dev.security.bean.MenuBean;
import com.dpstudio.dev.security.jwt.JWT;
import com.dpstudio.dev.support.jwt.JwtBean;
import com.dpstudio.dev.support.jwt.JwtConfig;
import com.dpstudio.dev.support.jwt.JwtHelper;
import com.dpstudio.dev.utils.BeanUtils;
import com.dpstudio.module.security.SecurityCache;
import com.dpstudio.module.security.core.Code;
import com.dpstudio.module.security.core.SecurityConstants;
import com.dpstudio.module.security.dao.ISecurityAdminDao;
import com.dpstudio.module.security.dao.ISecurityAdminRoleDao;
import com.dpstudio.module.security.model.SecurityAdmin;
import com.dpstudio.module.security.model.SecurityRole;
import com.dpstudio.module.security.service.ISecurityAdminLogService;
import com.dpstudio.module.security.service.ISecurityAdminService;
import com.dpstudio.module.security.service.ISecuritySettingService;
import com.dpstudio.module.security.vo.detail.SecurityAdminDetailVO;
import com.dpstudio.module.security.vo.detail.SecuritySettingDetailVO;
import com.dpstudio.module.security.vo.list.SecurityAdminListVO;
import com.dpstudio.module.security.vo.list.SecurityAdminRoleListVO;
import com.dpstudio.module.security.vo.op.SecurityAdminVO;
import net.ymate.platform.commons.lang.BlurObject;
import net.ymate.platform.commons.util.DateTimeUtils;
import net.ymate.platform.commons.util.UUIDUtils;
import net.ymate.platform.core.YMP;
import net.ymate.platform.core.beans.annotation.Bean;
import net.ymate.platform.core.beans.annotation.Inject;
import net.ymate.platform.core.persistence.IResultSet;
import net.ymate.platform.core.persistence.Params;
import net.ymate.platform.core.persistence.annotation.Transaction;
import net.ymate.platform.persistence.jdbc.IDBLocker;
import net.ymate.platform.webmvc.context.WebContext;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Bean
@Transaction
public class SecurityAdminServiceImpl implements ISecurityAdminService {

    @Inject
    private ISecurityAdminDao iSecurityAdminDao;
    @Inject
    private ISecurityAdminRoleDao iSecurityAdminRoleDao;
    @Inject
    private ISecurityAdminLogService iSecurityAdminLogService;
    @Inject
    private ISecuritySettingService iSecuritySettingService;

    @Override
    @Transaction
    public R login(String userName, String password) throws Exception {
        SecurityAdmin securityAdmin = iSecurityAdminDao.findByUserName(userName, SecurityAdmin.FIELDS.ID,
                SecurityAdmin.FIELDS.GENDER, SecurityAdmin.FIELDS.PHOTO_URI, SecurityAdmin.FIELDS.PASSWORD,
                SecurityAdmin.FIELDS.USER_NAME, SecurityAdmin.FIELDS.SALT,
                SecurityAdmin.FIELDS.REAL_NAME, SecurityAdmin.FIELDS.LOGIN_ERROR_COUNT,
                SecurityAdmin.FIELDS.FOUNDER, SecurityAdmin.FIELDS.DISABLE_STATUS,
                SecurityAdmin.FIELDS.LOGIN_LOCK_END_TIME, SecurityAdmin.FIELDS.SESSION_TOKEN);
        if (securityAdmin == null) {
            return R.create(Code.SECURITY_ADMIN_USERNAME_NOT_EXIST.getCode()).msg(Code.SECURITY_ADMIN_USERNAME_NOT_EXIST.getMsg());
        }
        SecuritySettingDetailVO securitySettingDetailVO = iSecuritySettingService.detail(SecurityConstants.SET_ID);
        //开启错误计数 并且 错误次数大于等于设置的
        boolean loginErrorBool = Objects.equals(securitySettingDetailVO.getLoginErrorStatus(), SecurityConstants.BOOL_TRUE)
                && securityAdmin.getLoginErrorCount() >= securitySettingDetailVO.getLoginErrorCount()
                && securityAdmin.getLoginLockEndTime() >= DateTimeUtils.currentTimeMillis();
        if (loginErrorBool) {
            return R.create(Code.SECURITY_ADMIN_IS_LOCKED.getCode()).msg(Code.SECURITY_ADMIN_IS_LOCKED.getMsg() + "，请于" + DateTimeUtils.formatTime(securityAdmin.getLoginLockEndTime(), "yyyy-MM-dd HH:mm:ss") + "之后再试");
        }
        if (securityAdmin.getLoginLockEndTime() > 0) {
            //清除冻结状态
            securityAdmin.setLoginErrorCount(0);
            securityAdmin.setLoginLockStatus(SecurityConstants.BOOL_FALSE);
            securityAdmin.setLoginLockStartTime(0L);
            securityAdmin.setLoginLockEndTime(0L);
            iSecurityAdminDao.update(securityAdmin, SecurityAdmin.FIELDS.LOGIN_ERROR_COUNT, SecurityAdmin.FIELDS.LOGIN_LOCK_STATUS,
                    SecurityAdmin.FIELDS.LOGIN_LOCK_START_TIME, SecurityAdmin.FIELDS.LOGIN_LOCK_END_TIME);
        }

        String oldPassword = securityAdmin.getPassword();
        password = DigestUtils.md5Hex(Base64.encodeBase64((password + securityAdmin.getSalt()).getBytes(SecurityConstants.CHARTSET)));
        //两次密码不一致
        if (!oldPassword.equals(password)) {
            securityAdmin.setLoginErrorCount(securityAdmin.getLoginErrorCount() + 1);
            //错误次数到了 直接锁定
            if (securityAdmin.getLoginErrorCount() >= securitySettingDetailVO.getLoginErrorCount()) {
                securityAdmin.setLoginLockStatus(SecurityConstants.BOOL_TRUE);
                securityAdmin.setLoginLockStartTime(DateTimeUtils.currentTimeMillis());
                //默认锁定24小时
                long lockTime = DateTimeUtils.MINUTE * 24;
                if (securitySettingDetailVO.getLoginErrorTime() > 0) {
                    lockTime = DateTimeUtils.MINUTE * securitySettingDetailVO.getLoginErrorTime();
                }
                securityAdmin.setLoginLockEndTime(DateTimeUtils.currentTimeMillis() + lockTime);
            }
            iSecurityAdminDao.update(securityAdmin, SecurityAdmin.FIELDS.LOGIN_ERROR_COUNT, SecurityAdmin.FIELDS.LOGIN_LOCK_STATUS,
                    SecurityAdmin.FIELDS.LOGIN_LOCK_START_TIME, SecurityAdmin.FIELDS.LOGIN_LOCK_END_TIME);
            return R.create(Code.SECURITY_ADMIN_USERNAME_OR_PASSWORD_ERROR.getCode()).msg(Code.SECURITY_ADMIN_USERNAME_OR_PASSWORD_ERROR.getMsg());
        }
        if (Objects.equals(SecurityConstants.BOOL_TRUE, securityAdmin.getDisableStatus())) {
            return R.create(Code.SECURITY_ADMIN_DISABLED.getCode()).msg(Code.SECURITY_ADMIN_DISABLED.getMsg());
        }
        securityAdmin.setLoginErrorCount(0);
        securityAdmin.setLoginLockStatus(SecurityConstants.BOOL_FALSE);
        securityAdmin.setLoginLockStartTime(0L);
        securityAdmin.setLoginLockEndTime(0L);

        ISecurityConfig iSecurityConfig = Security.get().getConfig();
        JwtConfig jwtConfig = JwtConfig.builder()
                .secret(iSecurityConfig.secret())
                .paramName(iSecurityConfig.paramName())
                .headerName(iSecurityConfig.headerName())
                .autoResponse(iSecurityConfig.autoResponse())
                .verifyTime(DateTimeUtils.MINUTE * iSecurityConfig.verifyTime());
        R jwtResult = JWT.attr("uid", securityAdmin.getId())
                .create(jwtConfig);
        if (!Objects.equals(jwtResult.code(), C.SUCCESS.getCode())) {
            return jwtResult;
        }
        //放到缓存
        SecurityCache.JwtCache.setPara(jwtResult.attr("jwtToken"));
        SecurityCache.JwtCache.setParaByAdminId(securityAdmin.getId(), jwtResult.attr("jwtToken"));
        iSecurityAdminDao.update(securityAdmin, SecurityAdmin.FIELDS.LOGIN_ERROR_COUNT, SecurityAdmin.FIELDS.LOGIN_LOCK_STATUS,
                SecurityAdmin.FIELDS.LOGIN_LOCK_START_TIME, SecurityAdmin.FIELDS.LOGIN_LOCK_END_TIME, SecurityAdmin.FIELDS.SESSION_TOKEN);
        //开启日志记录
        if (Objects.equals(securitySettingDetailVO.getLoginLogStatus(), SecurityConstants.BOOL_TRUE)) {
            iSecurityAdminLogService.create(securityAdmin.getId(), StringUtils.defaultIfBlank(securityAdmin.getRealName(), securityAdmin.getUserName()));
        }
        List<MenuBean> menuBeanList = Security.get().permissionMenu();
        return R.ok().attr("menu_list", menuBeanList);
    }

    @Override
    public R updatePassword(String pwd, String newPwd, String reNewPwd) throws Exception {
        if (!newPwd.equals(reNewPwd)) {
            return R.create(Code.SECURITY_ADMIN_PASSWORD_NO_SAME.getCode()).msg(Code.SECURITY_ADMIN_PASSWORD_NO_SAME.getMsg());
        }
        SecurityAdmin securityAdmin = iSecurityAdminDao.findById(BlurObject.bind(JWT.Store.getPara("uid")).toStringValue(), IDBLocker.DEFAULT, SecurityAdmin.FIELDS.USER_NAME, SecurityAdmin.FIELDS.PASSWORD, SecurityAdmin.FIELDS.ID, SecurityAdmin.FIELDS.SALT);
        if (securityAdmin == null) {
            return R.create(Code.SECURITY_ADMIN_NOT_EXIST.getCode()).msg(Code.SECURITY_ADMIN_NOT_EXIST.getMsg());
        }
        String salt = securityAdmin.getSalt();
        String oldpwd = DigestUtils.md5Hex(Base64.encodeBase64((pwd + salt).getBytes(SecurityConstants.CHARTSET)));
        if (!Objects.equals(oldpwd, securityAdmin.getPassword())) {
            return R.create(Code.SECURITY_ADMIN_PASSWORD_OLD_ERROR.getCode()).msg(Code.SECURITY_ADMIN_PASSWORD_OLD_ERROR.getMsg());
        }
        pwd = DigestUtils.md5Hex(Base64.encodeBase64((newPwd + salt).getBytes(SecurityConstants.CHARTSET)));
        securityAdmin.setPassword(pwd);
        securityAdmin.setLastModifyTime(DateTimeUtils.currentTimeMillis());
        securityAdmin.setLastModifyUser(SecurityCache.userId());
        securityAdmin = iSecurityAdminDao.update(securityAdmin, SecurityAdmin.FIELDS.PASSWORD, SecurityAdmin.FIELDS.LAST_MODIFY_TIME, SecurityAdmin.FIELDS.LAST_MODIFY_USER);
        if (securityAdmin != null) {
            clearLoginStatus();
        }
        return R.result(securityAdmin);
    }

    private void clearLoginStatus() {
        HttpServletRequest request = WebContext.getRequest();
        String token = request.getHeader(JWT.JWT_CONFIG.getHeaderName());

        if (StringUtils.isBlank(token) && StringUtils.isBlank(JWT.JWT_CONFIG.getParamName())) {
            token = request.getParameter(JWT.JWT_CONFIG.getParamName());
        }
        if (StringUtils.isBlank(token)) {
            return;
        }
        SecurityCache.JwtCache.removePara(token);
        R r = JwtHelper.parse(token);
        if (!Objects.equals(r.code(), C.SUCCESS.getCode())) {
            return;
        }
        String uid = r.attr("uid");
        if (StringUtils.isBlank(uid)) {
            return;
        }
        SecurityCache.JwtCache.removeParaByAdminId(uid);
        try {
            SecurityAdmin securityAdmin = YMP.get().getBeanFactory().getBean(ISecurityAdminDao.class).findById(uid, null);
            if (securityAdmin == null) {
                return;
            }
            SecurityCache.AdminCache.removePara(securityAdmin.getId());
        } catch (Exception ignored) {
        }
    }

    @Override
    public SecurityAdminDetailVO detail(String id) throws Exception {
        SecurityAdmin securityAdmin = iSecurityAdminDao.findById(id, null);
        return BeanUtils.copy(securityAdmin, SecurityAdminDetailVO::new, (s, securityAdminDetailVO) -> {
            securityAdminDetailVO.setThumb(s.getPhotoUri());
        });
    }

    @Override
    public R updateInfo(SecurityAdminVO securityAdminVO) throws Exception {
        SecurityAdmin securityAdmin = iSecurityAdminDao.findById(BlurObject.bind(JWT.Store.getPara("uid")).toStringValue(), IDBLocker.DEFAULT);
        if (securityAdmin == null) {
            return R.create(Code.SECURITY_ADMIN_NOT_EXIST.getCode())
                    .msg(Code.SECURITY_ADMIN_NOT_EXIST.getMsg());
        }
        securityAdmin.setRealName(StringUtils.defaultIfBlank(securityAdminVO.getRealName(), ""));
        securityAdmin.setPhotoUri(StringUtils.defaultIfBlank(securityAdminVO.getThumb(), ""));
        securityAdmin.setMobile(StringUtils.defaultIfBlank(securityAdminVO.getMobile(), ""));
        securityAdmin.setGender(securityAdminVO.getGender());
        securityAdmin = iSecurityAdminDao.update(securityAdmin, SecurityAdmin.FIELDS.REAL_NAME, SecurityAdmin.FIELDS.PHOTO_URI, SecurityAdmin.FIELDS.MOBILE, SecurityAdmin.FIELDS.GENDER);
        SecurityCache.AdminCache.setPara(securityAdmin);
        return R.result(securityAdmin);
    }

    @Override
    public IResultSet<SecurityAdminListVO> list(String userName, String realName, Integer disableStatus, Integer page, Integer pageSize) throws Exception {
        IResultSet<SecurityAdminListVO> list = iSecurityAdminDao.list(userName, realName, disableStatus, page, pageSize);
        Params adminIds = Params.create();
        for (SecurityAdminListVO securityAdminListVO : list.getResultData()) {
            adminIds.add(securityAdminListVO.getId());
        }
        IResultSet<SecurityAdminRoleListVO> iResultSet = iSecurityAdminRoleDao.findByAdminIds(adminIds, page, pageSize);
        for (SecurityAdminListVO securityAdminListVO : list.getResultData()) {
            String roleName = "";
            for (SecurityAdminRoleListVO securityAdminRoleListVO : iResultSet.getResultData()) {
                if (securityAdminListVO.getId().equals(securityAdminRoleListVO.getAdminId())) {
                    if (StringUtils.isNotBlank(roleName)) {
                        roleName = roleName + "," + securityAdminRoleListVO.getRoleName();
                    } else {
                        roleName = securityAdminRoleListVO.getRoleName();
                    }
                }
            }
            securityAdminListVO.setRoleName(roleName);
        }
        return list;
    }

    @Override
    public R create(SecurityAdminVO securityAdminVO, String password) throws Exception {
        SecurityAdmin securityAdmin = iSecurityAdminDao.findByUserName(securityAdminVO.getUserName());
        if (securityAdmin != null) {
            return R.create(Code.SECURITY_ADMIN_EXIST.getCode()).msg(Code.SECURITY_ADMIN_EXIST.getMsg());
        }
        String salt = UUIDUtils.randomStr(6, false);
        password = DigestUtils.md5Hex(Base64.encodeBase64((password + salt).getBytes(SecurityConstants.CHARTSET)));
        String finalPassword = password;
        securityAdmin = BeanUtils.copy(securityAdminVO, SecurityAdmin::new, (s, t) -> {
            t.setId(UUIDUtils.UUID());
            t.setSalt(salt);
            t.setPassword(finalPassword);
            t.setCreateTime(DateTimeUtils.currentTimeMillis());
            t.setCreateUser(SecurityCache.userId());
            t.setLastModifyTime(DateTimeUtils.currentTimeMillis());
            t.setLastModifyUser(SecurityCache.userId());
        });
        securityAdmin = iSecurityAdminDao.create(securityAdmin);
        return R.result(securityAdmin);
    }

    @Override
    public R disabled(String[] ids, Integer disableStatus) throws Exception {
        List<String> params = Arrays.asList(ids);
        IResultSet<SecurityAdmin> securityAdminResultSet = iSecurityAdminDao.findAllByIds(params, IDBLocker.DEFAULT);
        if (securityAdminResultSet.isResultsAvailable()) {
            List<SecurityAdmin> securityAdminList = BeanUtils.copyList(securityAdminResultSet.getResultData(), SecurityAdmin::new, (s, t) -> {
                t.setDisableStatus(disableStatus);
            });
            if (!securityAdminList.isEmpty()) {
                iSecurityAdminDao.updateAll(securityAdminList, SecurityAdmin.FIELDS.DISABLE_STATUS);
            }
        }
        return R.ok();
    }

    @Override
    public R resetPassword(String id) throws Exception {
        SecurityAdmin securityAdmin = iSecurityAdminDao.findById(id, IDBLocker.DEFAULT);
        if (securityAdmin == null) {
            return R.create(Code.SECURITY_ADMIN_NOT_EXIST.getCode()).msg(Code.SECURITY_ADMIN_NOT_EXIST.getMsg());
        }
        String userName = securityAdmin.getUserName();
        userName = DigestUtils.md5Hex(userName);
        String password = DigestUtils.md5Hex(Base64.encodeBase64((userName + securityAdmin.getSalt()).getBytes(SecurityConstants.CHARTSET)));
        securityAdmin.setPassword(password);
        securityAdmin = iSecurityAdminDao.update(securityAdmin, SecurityAdmin.FIELDS.PASSWORD);
        return R.result(securityAdmin);
    }

    @Override
    public R unlock(String id) throws Exception {
        SecurityAdmin securityAdmin = iSecurityAdminDao.findById(id, IDBLocker.DEFAULT);
        if (securityAdmin == null) {
            return R.create(Code.SECURITY_ADMIN_NOT_EXIST.getCode()).msg(Code.SECURITY_ADMIN_NOT_EXIST.getMsg());
        }
        securityAdmin.setLoginErrorCount(0);
        securityAdmin.setLoginLockStatus(SecurityConstants.BOOL_FALSE);
        securityAdmin.setLoginLockStartTime(0L);
        securityAdmin.setLoginLockEndTime(0L);
        securityAdmin = iSecurityAdminDao.update(securityAdmin, SecurityAdmin.FIELDS.LOGIN_ERROR_COUNT, SecurityAdmin.FIELDS.LOGIN_LOCK_STATUS,
                SecurityAdmin.FIELDS.LOGIN_LOCK_START_TIME, SecurityAdmin.FIELDS.LOGIN_LOCK_END_TIME);
        return R.result(securityAdmin);
    }

    @Override
    public R delete(String[] ids) throws Exception {
        List<SecurityAdmin> list = new ArrayList<>();
        for (String id : ids) {
            list.add(SecurityAdmin.builder().id(id).build());
        }
        iSecurityAdminDao.delete(list);
        return R.ok();
    }


}
