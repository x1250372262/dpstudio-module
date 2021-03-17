package com.dpstudio.module.security.service.impl;

import com.dpstudio.dev.code.C;
import com.dpstudio.dev.core.Constants;
import com.dpstudio.dev.core.R;
import com.dpstudio.dev.dto.PageDTO;
import com.dpstudio.dev.security.Security;
import com.dpstudio.dev.security.bean.MenuBean;
import com.dpstudio.dev.security.jwt.JWT;
import com.dpstudio.dev.support.jwt.JwtBean;
import com.dpstudio.dev.support.jwt.JwtHelper;
import com.dpstudio.dev.support.spi.SpiLoader;
import com.dpstudio.dev.utils.BeanUtils;
import com.dpstudio.module.security.SecurityCache;
import com.dpstudio.module.security.core.Code;
import com.dpstudio.module.security.core.SecurityConstants;
import com.dpstudio.module.security.dao.ISecurityAdminDao;
import com.dpstudio.module.security.dao.ISecurityAdminRoleDao;
import com.dpstudio.module.security.model.SecurityAdmin;
import com.dpstudio.module.security.service.ISecurityAdminLogService;
import com.dpstudio.module.security.service.ISecurityAdminService;
import com.dpstudio.module.security.service.ISecuritySettingService;
import com.dpstudio.module.security.service.handler.ISecurityAdminHandler;
import com.dpstudio.module.security.vo.detail.SecurityAdminDetailVO;
import com.dpstudio.module.security.vo.detail.SecuritySettingDetailVO;
import com.dpstudio.module.security.vo.list.SecurityAdminListVO;
import com.dpstudio.module.security.vo.list.SecurityAdminRoleListVO;
import com.dpstudio.module.security.vo.op.SecurityAdminVO;
import net.ymate.platform.commons.util.DateTimeUtils;
import net.ymate.platform.commons.util.UUIDUtils;
import net.ymate.platform.core.YMP;
import net.ymate.platform.core.beans.annotation.Bean;
import net.ymate.platform.core.beans.annotation.Inject;
import net.ymate.platform.core.persistence.IResultSet;
import net.ymate.platform.core.persistence.Params;
import net.ymate.platform.core.persistence.annotation.Transaction;
import net.ymate.platform.core.persistence.impl.DefaultResultSet;
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

    private SecurityAdmin initSecurityAdmin(String clientName) throws Exception {
        String salt = UUIDUtils.randomStr(6, false);
        String password = DigestUtils.md5Hex(SecurityConstants.DEFAULT_ADMIN_PASSWORD);
        password = DigestUtils.md5Hex(Base64.encodeBase64((password + salt).getBytes(Constants.DEFAULT_CHARTSET)));
        String id = UUIDUtils.UUID();
        return SecurityAdmin.builder()
                .id(id)
                .userName(SecurityConstants.DEFAULT_ADMIN_USER_NAME)
                .realName(SecurityConstants.DEFAULT_ADMIN_REAL_NAME)
                .password(password)
                .founder(Constants.BOOL_TRUE)
                .createUser(id)
                .createTime(DateTimeUtils.currentTimeMillis())
                .lastModifyTime(DateTimeUtils.currentTimeMillis())
                .lastModifyUser(id)
                .salt(salt)
                .clientName(clientName)
                .build();
    }

    @Override
    public R init(String clientName) throws Exception {
        if (StringUtils.isBlank(clientName)) {
            SecurityAdmin securityAdmin = iSecurityAdminDao.findByClientNameAndFounder("", Constants.BOOL_TRUE);
            if (securityAdmin == null) {
                securityAdmin = initSecurityAdmin(clientName);
                securityAdmin = iSecurityAdminDao.create(securityAdmin);
            }
            return R.result(securityAdmin);
        } else {
            if (!clientName.contains("|")) {
                SecurityAdmin securityAdmin = iSecurityAdminDao.findByClientNameAndFounder(clientName, Constants.BOOL_TRUE);
                if (securityAdmin == null) {
                    securityAdmin = initSecurityAdmin(clientName);
                    securityAdmin = iSecurityAdminDao.create(securityAdmin);
                }
                return R.result(securityAdmin);
            }
            String[] clientNameArray = clientName.split("\\|");
            List<SecurityAdmin> securityAdminList = new ArrayList<>();
            for (String clientNameStr : clientNameArray) {
                SecurityAdmin securityAdmin = iSecurityAdminDao.findByClientNameAndFounder(clientNameStr, Constants.BOOL_TRUE);
                if (securityAdmin == null) {
                    securityAdminList.add(initSecurityAdmin(clientNameStr));
                }
            }
            if (securityAdminList.isEmpty()) {
                return R.ok();
            }
            securityAdminList = iSecurityAdminDao.createAll(securityAdminList);
            return R.result(securityAdminList);
        }
    }

    @Override
    @Transaction
    public R login(String userName, String password, String clientName) throws Exception {
        ISecurityAdminHandler securityAdminHandler = SpiLoader.load(ISecurityAdminHandler.class, null);
        if (securityAdminHandler == null) {
            securityAdminHandler = new ISecurityAdminHandler.SecurityAdminHandler();
        }
        //处理登录之前的逻辑 返回成功往下走
        R loginBeforeResult = securityAdminHandler.loginBefore(userName, password);
        if (!Objects.equals(loginBeforeResult.code(), C.SUCCESS.getCode())) {
            return loginBeforeResult;
        }
        SecurityAdmin securityAdmin = iSecurityAdminDao.findByUserNameAndClientName(userName, clientName, SecurityAdmin.FIELDS.ID,
                SecurityAdmin.FIELDS.GENDER, SecurityAdmin.FIELDS.PHOTO_URI, SecurityAdmin.FIELDS.PASSWORD,
                SecurityAdmin.FIELDS.USER_NAME, SecurityAdmin.FIELDS.SALT, SecurityAdmin.FIELDS.CLIENT_NAME,
                SecurityAdmin.FIELDS.REAL_NAME, SecurityAdmin.FIELDS.LOGIN_ERROR_COUNT,
                SecurityAdmin.FIELDS.FOUNDER, SecurityAdmin.FIELDS.DISABLE_STATUS,
                SecurityAdmin.FIELDS.LOGIN_LOCK_END_TIME, SecurityAdmin.FIELDS.SESSION_TOKEN);
        if (securityAdmin == null) {
            return R.create(Code.SECURITY_ADMIN_USERNAME_NOT_EXIST.getCode()).msg(Code.SECURITY_ADMIN_USERNAME_NOT_EXIST.getMsg());
        }
        SecuritySettingDetailVO securitySettingDetailVO = iSecuritySettingService.detail(clientName);
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
        password = DigestUtils.md5Hex(Base64.encodeBase64((password + securityAdmin.getSalt()).getBytes(Constants.DEFAULT_CHARTSET)));
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

        //处理登录之后的逻辑 返回成功往下走
        R loginAfterResult = securityAdminHandler.loginAfter(securityAdmin);
        if (!Objects.equals(loginAfterResult.code(), C.SUCCESS.getCode())) {
            return loginAfterResult;
        }
        securityAdmin.setLoginErrorCount(0);
        securityAdmin.setLoginLockStatus(SecurityConstants.BOOL_FALSE);
        securityAdmin.setLoginLockStartTime(0L);
        securityAdmin.setLoginLockEndTime(0L);

        R jwtResult = JWT.attr(SecurityConstants.JWT_ADMIN_ID_KEY, securityAdmin.getId())
                .build();
        if (!Objects.equals(jwtResult.code(), C.SUCCESS.getCode())) {
            return jwtResult;
        }

        JwtBean jwtBean = jwtResult.attr("jwtToken");
        //放到缓存
        SecurityCache.JwtCache.setPara(jwtResult.attr("jwtToken"), clientName);
        SecurityCache.JwtCache.setParaByAdminId(securityAdmin.getId(), jwtResult.attr("jwtToken"), clientName);
        SecurityCache.AdminCache.setPara(securityAdmin);

        iSecurityAdminDao.update(securityAdmin, SecurityAdmin.FIELDS.LOGIN_ERROR_COUNT, SecurityAdmin.FIELDS.LOGIN_LOCK_STATUS,
                SecurityAdmin.FIELDS.LOGIN_LOCK_START_TIME, SecurityAdmin.FIELDS.LOGIN_LOCK_END_TIME, SecurityAdmin.FIELDS.SESSION_TOKEN);
        //开启日志记录
        if (Objects.equals(securitySettingDetailVO.getLoginLogStatus(), SecurityConstants.BOOL_TRUE)) {
            iSecurityAdminLogService.create(securityAdmin.getId(), clientName, StringUtils.defaultIfBlank(securityAdmin.getRealName(), securityAdmin.getUserName()));
        }

        List<MenuBean> menuBeanList = Security.get().permissionMenu(jwtBean.getToken(), clientName);
        return R.ok().attr("menu_list", menuBeanList).attr("clientName", clientName);
    }

    @Override
    public R updatePassword(String pwd, String newPwd, String reNewPwd) throws Exception {
        if (!newPwd.equals(reNewPwd)) {
            return R.create(Code.SECURITY_ADMIN_PASSWORD_NO_SAME.getCode()).msg(Code.SECURITY_ADMIN_PASSWORD_NO_SAME.getMsg());
        }
        SecurityAdmin securityAdmin = iSecurityAdminDao.findById(SecurityCache.userId(), IDBLocker.DEFAULT, SecurityAdmin.FIELDS.USER_NAME, SecurityAdmin.FIELDS.CLIENT_NAME, SecurityAdmin.FIELDS.PASSWORD, SecurityAdmin.FIELDS.ID, SecurityAdmin.FIELDS.SALT);
        if (securityAdmin == null) {
            return R.create(Code.SECURITY_ADMIN_NOT_EXIST.getCode()).msg(Code.SECURITY_ADMIN_NOT_EXIST.getMsg());
        }
        String salt = securityAdmin.getSalt();
        String oldpwd = DigestUtils.md5Hex(Base64.encodeBase64((pwd + salt).getBytes(Constants.DEFAULT_CHARTSET)));
        if (!Objects.equals(oldpwd, securityAdmin.getPassword())) {
            return R.create(Code.SECURITY_ADMIN_PASSWORD_OLD_ERROR.getCode()).msg(Code.SECURITY_ADMIN_PASSWORD_OLD_ERROR.getMsg());
        }
        pwd = DigestUtils.md5Hex(Base64.encodeBase64((newPwd + salt).getBytes(Constants.DEFAULT_CHARTSET)));
        securityAdmin.setPassword(pwd);
        securityAdmin.setLastModifyTime(DateTimeUtils.currentTimeMillis());
        securityAdmin.setLastModifyUser(SecurityCache.userId());
        securityAdmin = iSecurityAdminDao.update(securityAdmin, SecurityAdmin.FIELDS.PASSWORD, SecurityAdmin.FIELDS.LAST_MODIFY_TIME, SecurityAdmin.FIELDS.LAST_MODIFY_USER);
        if (securityAdmin != null) {
            clearLoginStatus(securityAdmin.getClientName());
        }
        return R.result(securityAdmin);
    }

    private void clearLoginStatus(String clientName) {
        HttpServletRequest request = WebContext.getRequest();
        String token = request.getHeader(JWT.JWT_CONFIG.getHeaderName());

        if (StringUtils.isBlank(token) && StringUtils.isBlank(JWT.JWT_CONFIG.getParamName())) {
            token = request.getParameter(JWT.JWT_CONFIG.getParamName());
        }
        if (StringUtils.isBlank(token)) {
            return;
        }
        SecurityCache.JwtCache.removePara(token, clientName);
        R r = JwtHelper.parse(token);
        if (!Objects.equals(r.code(), C.SUCCESS.getCode())) {
            return;
        }
        String uid = r.attr("uid");
        if (StringUtils.isBlank(uid)) {
            return;
        }
        SecurityCache.JwtCache.removeParaByAdminId(uid, clientName);
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
        SecurityAdmin securityAdmin = iSecurityAdminDao.findById(SecurityCache.userId(), IDBLocker.DEFAULT);
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
    public IResultSet<SecurityAdminListVO> list(String userName, String realName, Integer disableStatus, PageDTO pageDTO) throws Exception {
        SecurityAdmin loginAdmin = SecurityCache.AdminCache.getPara(SecurityCache.userId());
        if (loginAdmin == null) {
            return new DefaultResultSet<>(new ArrayList<>());
        }
        IResultSet<SecurityAdminListVO> list = iSecurityAdminDao.list(loginAdmin.getClientName(), userName, realName, disableStatus, pageDTO);
        Params adminIds = Params.create();
        for (SecurityAdminListVO securityAdminListVO : list.getResultData()) {
            adminIds.add(securityAdminListVO.getId());
        }
        IResultSet<SecurityAdminRoleListVO> iResultSet = iSecurityAdminRoleDao.findByAdminIds(adminIds, pageDTO);
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
        SecurityAdmin loginAdmin = SecurityCache.AdminCache.getPara(SecurityCache.userId());
        if (loginAdmin == null) {
            return R.create(Code.SECURITY_ADMIN_INVALID_OR_TIMEOUT.getCode()).msg(Code.SECURITY_ADMIN_INVALID_OR_TIMEOUT.getMsg());
        }
        SecurityAdmin securityAdmin = iSecurityAdminDao.findByUserNameAndClientName(securityAdminVO.getUserName(), loginAdmin.getClientName());
        if (securityAdmin != null) {
            return R.create(Code.SECURITY_ADMIN_EXIST.getCode()).msg(Code.SECURITY_ADMIN_EXIST.getMsg());
        }
        String salt = UUIDUtils.randomStr(6, false);
        password = DigestUtils.md5Hex(Base64.encodeBase64((password + salt).getBytes(Constants.DEFAULT_CHARTSET)));
        String finalPassword = password;
        securityAdmin = BeanUtils.copy(securityAdminVO, SecurityAdmin::new, (s, t) -> {
            t.setId(UUIDUtils.UUID());
            t.setSalt(salt);
            t.setClientName(loginAdmin.getClientName());
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
    public R create(String userName, String realName, String mobile, String password, String clientName) throws Exception {
        if (StringUtils.isBlank(userName)) {
            return R.create(Code.SECURITY_ADMIN_USER_NAME_EMPTY.getCode()).msg(Code.SECURITY_ADMIN_USER_NAME_EMPTY.getMsg());
        }
        if (StringUtils.isBlank(password)) {
            return R.create(Code.SECURITY_ADMIN_PASSWORD_EMPTY.getCode()).msg(Code.SECURITY_ADMIN_PASSWORD_EMPTY.getMsg());
        }
        if (StringUtils.isBlank(clientName)) {
            return R.create(Code.SECURITY_ADMIN_CLENT_NAME_EMPTY.getCode()).msg(Code.SECURITY_ADMIN_CLENT_NAME_EMPTY.getMsg());
        }
        SecurityAdmin securityAdmin = iSecurityAdminDao.findByUserNameAndClientName(userName, clientName);
        if (securityAdmin != null) {
            return R.create(Code.SECURITY_ADMIN_EXIST.getCode()).msg(Code.SECURITY_ADMIN_EXIST.getMsg());
        }
        String salt = UUIDUtils.randomStr(6, false);
        password = DigestUtils.md5Hex(Base64.encodeBase64((password + salt).getBytes(Constants.DEFAULT_CHARTSET)));
        securityAdmin = SecurityAdmin.builder()
                .id(UUIDUtils.UUID())
                .clientName(clientName)
                .userName(userName)
                .realName(realName)
                .password(password)
                .mobile(mobile)
                .createUser(SecurityCache.userId())
                .createTime(DateTimeUtils.currentTimeMillis())
                .lastModifyTime(DateTimeUtils.currentTimeMillis())
                .lastModifyUser(SecurityCache.userId())
                .salt(salt)
                .build();
        securityAdmin = iSecurityAdminDao.create(securityAdmin);
        return R.result(securityAdmin).data(securityAdmin);
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
        String password = DigestUtils.md5Hex(Base64.encodeBase64((userName + securityAdmin.getSalt()).getBytes(Constants.DEFAULT_CHARTSET)));
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
        int[] result = iSecurityAdminDao.delete(ids);
        return R.result(result);
    }

}
