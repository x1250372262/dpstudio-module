package com.dpstudio.module.mybatis.transaction;

import net.ymate.platform.persistence.jdbc.IDatabaseConnectionHolder;
import net.ymate.platform.persistence.jdbc.JDBC;
import net.ymate.platform.persistence.jdbc.transaction.Transactions;
import org.apache.ibatis.transaction.Transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author: 徐建鹏.
 * @create: 2021-02-23 11:43
 * @Description:
 */
public class YMPTransaction implements Transaction {

    private IDatabaseConnectionHolder databaseConnectionHolder;

    private IDatabaseConnectionHolder transactionDatabaseConnectionHolder;

    @Override
    public Connection getConnection() {
        try {
            databaseConnectionHolder = JDBC.get().getDefaultConnectionHolder();
            if (Transactions.get() != null) {
                transactionDatabaseConnectionHolder = Transactions.get().getConnectionHolder(databaseConnectionHolder.getDataSourceConfig().getName());
            }
            Connection connection = databaseConnectionHolder.getConnection();
            connection.setAutoCommit(false);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void commit() throws SQLException {
        if (this.databaseConnectionHolder != null && transactionDatabaseConnectionHolder == null) {
            this.databaseConnectionHolder.getConnection().commit();
        }
    }

    @Override
    public void rollback() throws SQLException {
        if (this.databaseConnectionHolder != null && transactionDatabaseConnectionHolder == null) {
            this.databaseConnectionHolder.getConnection().rollback();
        }
    }

    @Override
    public void close() throws SQLException {
        if (this.databaseConnectionHolder != null && transactionDatabaseConnectionHolder == null) {
            this.databaseConnectionHolder.getConnection().close();
        }
    }

    @Override
    public Integer getTimeout() throws SQLException {
        return null;
    }
}
