/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dpstudio.module.security.model;

import net.ymate.platform.core.beans.annotation.PropertyState;
import net.ymate.platform.core.persistence.IShardingable;
import net.ymate.platform.core.persistence.annotation.*;
import net.ymate.platform.persistence.jdbc.IDatabase;
import net.ymate.platform.persistence.jdbc.IDatabaseConnectionHolder;
import net.ymate.platform.persistence.jdbc.support.BaseEntity;

/**
 * SecuritySetting generated By EntityMojo on 2020/11/09 10:09:05
 *
 * @author YMP (https://www.ymate.net/)
 */
@Entity(SecuritySetting.TABLE_NAME)
public class SecuritySetting extends BaseEntity<SecuritySetting, String> {

    private static final long serialVersionUID = 1L;

    @Id
    @Property(name = FIELDS.ID, nullable = false, length = 32)
    @Comment("")
    @PropertyState(propertyName = FIELDS.ID)
    private String id;


    @Property(name = FIELDS.LOGIN_LOG_STATUS, length = 1)
    @Default("0")
    @Comment("是否记录登录日志")
    @PropertyState(propertyName = FIELDS.LOGIN_LOG_STATUS)
    private Integer loginLogStatus;


    @Property(name = FIELDS.LOGIN_ERROR_COUNT, length = 4)
    @Default("0")
    @Comment("登录错误锁定次数")
    @PropertyState(propertyName = FIELDS.LOGIN_ERROR_COUNT)
    private Integer loginErrorCount;


    @Property(name = FIELDS.LOGIN_ERROR_TIME, length = 4)
    @Default("0")
    @Comment("登录错误锁定时间(单位分钟)")
    @PropertyState(propertyName = FIELDS.LOGIN_ERROR_TIME)
    private Integer loginErrorTime;


    @Property(name = FIELDS.LOGIN_ERROR_STATUS, length = 1)
    @Default("0")
    @Comment("是否开启错误次数")
    @PropertyState(propertyName = FIELDS.LOGIN_ERROR_STATUS)
    private Integer loginErrorStatus;


    @Property(name = FIELDS.LOGIN_NOT_IP_STATUS, length = 1)
    @Default("0")
    @Comment("是否开启异地登录")
    @PropertyState(propertyName = FIELDS.LOGIN_NOT_IP_STATUS)
    private Integer loginNotIpStatus;


    @Property(name = FIELDS.LOGIN_NOT_IP_NOTICE, length = 1)
    @Default("0")
    @Comment("异地登录是否提醒")
    @PropertyState(propertyName = FIELDS.LOGIN_NOT_IP_NOTICE)
    private Integer loginNotIpNotice;

    @Property(name = FIELDS.LAST_MODIFY_TIME, length = 13)
    @Default("0")
    @Comment("最后修改时间")
    @PropertyState(propertyName = FIELDS.LAST_MODIFY_TIME)
    private Long lastModifyTime;


    @Property(name = FIELDS.LAST_MODIFY_USER, length = 32)
    @Comment("最后修改人")
    @PropertyState(propertyName = FIELDS.LAST_MODIFY_USER)
    private String lastModifyUser;


    public SecuritySetting() {
    }

    public SecuritySetting(IDatabase dbOwner) {
        super(dbOwner);
    }

    public SecuritySetting(String id) {
        this.id = id;
    }

    public SecuritySetting(IDatabase dbOwner, String id) {
        super(dbOwner);
        this.id = id;
    }

    public SecuritySetting(String id, Integer loginLogStatus, Integer loginErrorCount, Integer loginErrorTime, Integer loginErrorStatus, Integer loginNotIpStatus, Integer loginNotIpNotice, Long lastModifyTime, String lastModifyUser) {
        this.id = id;
        this.loginLogStatus = loginLogStatus;
        this.loginErrorCount = loginErrorCount;
        this.loginErrorTime = loginErrorTime;
        this.loginErrorStatus = loginErrorStatus;
        this.loginNotIpStatus = loginNotIpStatus;
        this.loginNotIpNotice = loginNotIpNotice;
        this.lastModifyTime = lastModifyTime;
        this.lastModifyUser = lastModifyUser;
    }

    public SecuritySetting(IDatabase dbOwner, String id, Integer loginLogStatus, Integer loginErrorCount, Integer loginErrorTime, Integer loginErrorStatus, Integer loginNotIpStatus, Integer loginNotIpNotice, Long lastModifyTime, String lastModifyUser) {
        super(dbOwner);
        this.id = id;
        this.loginLogStatus = loginLogStatus;
        this.loginErrorCount = loginErrorCount;
        this.loginErrorTime = loginErrorTime;
        this.loginErrorStatus = loginErrorStatus;
        this.loginNotIpStatus = loginNotIpStatus;
        this.loginNotIpNotice = loginNotIpNotice;
        this.lastModifyTime = lastModifyTime;
        this.lastModifyUser = lastModifyUser;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Integer getLoginLogStatus() {
        return loginLogStatus;
    }

    public void setLoginLogStatus(Integer loginLogStatus) {
        this.loginLogStatus = loginLogStatus;
    }

    public Integer getLoginErrorCount() {
        return loginErrorCount;
    }

    public void setLoginErrorCount(Integer loginErrorCount) {
        this.loginErrorCount = loginErrorCount;
    }

    public Integer getLoginErrorTime() {
        return loginErrorTime;
    }

    public void setLoginErrorTime(Integer loginErrorTime) {
        this.loginErrorTime = loginErrorTime;
    }

    public Integer getLoginErrorStatus() {
        return loginErrorStatus;
    }

    public void setLoginErrorStatus(Integer loginErrorStatus) {
        this.loginErrorStatus = loginErrorStatus;
    }

    public Integer getLoginNotIpStatus() {
        return loginNotIpStatus;
    }

    public void setLoginNotIpStatus(Integer loginNotIpStatus) {
        this.loginNotIpStatus = loginNotIpStatus;
    }

    public Integer getLoginNotIpNotice() {
        return loginNotIpNotice;
    }

    public void setLoginNotIpNotice(Integer loginNotIpNotice) {
        this.loginNotIpNotice = loginNotIpNotice;
    }

    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getLastModifyUser() {
        return lastModifyUser;
    }

    public void setLastModifyUser(String lastModifyUser) {
        this.lastModifyUser = lastModifyUser;
    }


    public Builder bind() {
        return new Builder(this);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(IDatabase dbOwner) {
        return new Builder(dbOwner);
    }

    public static class Builder {

        private final SecuritySetting targetEntity;

        public Builder() {
            targetEntity = new SecuritySetting();
        }

        public Builder(IDatabase dbOwner) {
            targetEntity = new SecuritySetting(dbOwner);
        }

        public Builder(SecuritySetting targetEntity) {
            this.targetEntity = targetEntity;
        }

        public SecuritySetting build() {
            return targetEntity;
        }

        public IDatabaseConnectionHolder connectionHolder() {
            return targetEntity.getConnectionHolder();
        }

        public Builder connectionHolder(IDatabaseConnectionHolder connectionHolder) {
            targetEntity.setConnectionHolder(connectionHolder);
            return this;
        }

        public String dataSourceName() {
            return targetEntity.getDataSourceName();
        }

        public Builder dataSourceName(String dataSourceName) {
            targetEntity.setDataSourceName(dataSourceName);
            return this;
        }

        public IShardingable shardingable() {
            return targetEntity.getShardingable();
        }

        public Builder shardingable(IShardingable shardingable) {
            targetEntity.setShardingable(shardingable);
            return this;
        }


        public String id() {
            return targetEntity.getId();
        }

        public Builder id(String id) {
            targetEntity.setId(id);
            return this;
        }

        public Integer loginLogStatus() {
            return targetEntity.getLoginLogStatus();
        }

        public Builder loginLogStatus(Integer loginLogStatus) {
            targetEntity.setLoginLogStatus(loginLogStatus);
            return this;
        }

        public Integer loginErrorCount() {
            return targetEntity.getLoginErrorCount();
        }

        public Builder loginErrorCount(Integer loginErrorCount) {
            targetEntity.setLoginErrorCount(loginErrorCount);
            return this;
        }

        public Integer loginErrorTime() {
            return targetEntity.getLoginErrorTime();
        }

        public Builder loginErrorTime(Integer loginErrorTime) {
            targetEntity.setLoginErrorTime(loginErrorTime);
            return this;
        }

        public Integer loginErrorStatus() {
            return targetEntity.getLoginErrorStatus();
        }

        public Builder loginErrorStatus(Integer loginErrorStatus) {
            targetEntity.setLoginErrorStatus(loginErrorStatus);
            return this;
        }

        public Integer loginNotIpStatus() {
            return targetEntity.getLoginNotIpStatus();
        }

        public Builder loginNotIpStatus(Integer loginNotIpStatus) {
            targetEntity.setLoginNotIpStatus(loginNotIpStatus);
            return this;
        }

        public Integer loginNotIpNotice() {
            return targetEntity.getLoginNotIpNotice();
        }

        public Builder loginNotIpNotice(Integer loginNotIpNotice) {
            targetEntity.setLoginNotIpNotice(loginNotIpNotice);
            return this;
        }

        public Long lastModifyTime() {
            return targetEntity.getLastModifyTime();
        }

        public Builder lastModifyTime(Long lastModifyTime) {
            targetEntity.setLastModifyTime(lastModifyTime);
            return this;
        }

        public String lastModifyUser() {
            return targetEntity.getLastModifyUser();
        }

        public Builder lastModifyUser(String lastModifyUser) {
            targetEntity.setLastModifyUser(lastModifyUser);
            return this;
        }
    }

    public interface FIELDS {
        String ID = "id";
        String LOGIN_LOG_STATUS = "login_log_status";
        String LOGIN_ERROR_COUNT = "login_error_count";
        String LOGIN_ERROR_TIME = "login_error_time";
        String LOGIN_ERROR_STATUS = "login_error_status";
        String LOGIN_NOT_IP_STATUS = "login_not_ip_status";
        String LOGIN_NOT_IP_NOTICE = "login_not_ip_notice";
        String LAST_MODIFY_TIME = "last_modify_time";
        String LAST_MODIFY_USER = "last_modify_user";
    }

    public static final String TABLE_NAME = "security_setting";
}
