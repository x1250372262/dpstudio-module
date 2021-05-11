package com.mx.module.mybatis.nullBean;

import org.apache.ibatis.mapping.DatabaseIdProvider;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Author: mengxiang.
 * @create: 2021-02-23 11:27
 * @Description:
 */
public class DatabaseIdProviderNullBean implements DatabaseIdProvider {
    @Override
    public String getDatabaseId(DataSource dataSource) throws SQLException {
        return null;
    }
}
