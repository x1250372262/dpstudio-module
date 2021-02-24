package com.dpstudio.module.mybatis.nullBean;

import org.apache.ibatis.mapping.DatabaseIdProvider;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Author: 徐建鹏.
 * @create: 2021-02-23 11:27
 * @Description:
 */
public class DatabaseIdProviderNullBean implements DatabaseIdProvider {
    @Override
    public String getDatabaseId(DataSource dataSource) throws SQLException {
        return null;
    }
}
