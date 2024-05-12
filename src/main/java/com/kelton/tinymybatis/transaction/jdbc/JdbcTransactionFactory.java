package com.kelton.tinymybatis.transaction.jdbc;


import com.kelton.tinymybatis.transaction.Transaction;
import com.kelton.tinymybatis.transaction.TransactionFactory;
import com.kelton.tinymybatis.transaction.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * JdbcTransaction 工厂
 */
public class JdbcTransactionFactory implements TransactionFactory {

    @Override
    public Transaction newTransaction(Connection conn) {
        return new JdbcTransaction(conn);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(dataSource, level, autoCommit);
    }

}
