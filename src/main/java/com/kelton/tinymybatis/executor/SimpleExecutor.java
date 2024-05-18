package com.kelton.tinymybatis.executor;


import com.kelton.tinymybatis.executor.statement.StatementHandler;
import com.kelton.tinymybatis.mapping.BoundSql;
import com.kelton.tinymybatis.mapping.MappedStatement;
import com.kelton.tinymybatis.session.Configuration;
import com.kelton.tinymybatis.session.ResultHandler;
import com.kelton.tinymybatis.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 简单执行器
 */

public class SimpleExecutor extends BaseExecutor {

    private final static Logger LOGGER = LoggerFactory.getLogger(SimpleExecutor.class);

    public SimpleExecutor(Configuration configuration, Transaction transaction) {
        super(configuration, transaction);
    }

    @Override
    protected <E> List<E> doQuery(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("正在执行doQuery");
            }
            Configuration configuration = ms.getConfiguration();
            StatementHandler handler = configuration.newStatementHandler(this, ms, parameter, resultHandler, boundSql);
            Connection connection = transaction.getConnection();
            Statement stmt = handler.prepare(connection);
            handler.parameterize(stmt);
            return handler.query(stmt, resultHandler);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
