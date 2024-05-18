package com.kelton.tinymybatis.session.defaults;

import com.kelton.tinymybatis.executor.Executor;
import com.kelton.tinymybatis.mapping.Environment;
import com.kelton.tinymybatis.session.Configuration;
import com.kelton.tinymybatis.session.SqlSession;
import com.kelton.tinymybatis.session.SqlSessionFactory;
import com.kelton.tinymybatis.transaction.Transaction;
import com.kelton.tinymybatis.transaction.TransactionFactory;
import com.kelton.tinymybatis.transaction.TransactionIsolationLevel;

import java.sql.SQLException;

/**
 * 接收一个configuration来简单包装SqlSession
 * @Author zhouzekun
 * @Date 2024/5/8 10:32
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        Transaction tx = null;
        try {
            final Environment environment = configuration.getEnvironment();
            TransactionFactory transactionFactory = environment.getTransactionFactory();
            tx = transactionFactory.newTransaction(configuration.getEnvironment().getDataSource(), TransactionIsolationLevel.READ_COMMITTED, false);
            // 创建执行器
            final Executor executor = configuration.newExecutor(tx);
            // 创建DefaultSqlSession
            return new DefaultSqlSession(configuration, executor);
        } catch (Exception e) {
            try {
                assert tx != null;
                tx.close();
            } catch (SQLException ignore) {
            }
            throw new RuntimeException("Error opening session.  Cause: " + e);
        }
    }
}
