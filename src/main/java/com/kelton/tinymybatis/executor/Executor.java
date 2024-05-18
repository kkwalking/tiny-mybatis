package com.kelton.tinymybatis.executor;


import com.kelton.tinymybatis.mapping.BoundSql;
import com.kelton.tinymybatis.mapping.MappedStatement;
import com.kelton.tinymybatis.session.ResultHandler;
import com.kelton.tinymybatis.transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

/**
 * 执行器
 */
public interface Executor {

    ResultHandler NO_RESULT_HANDLER = null;

    <E> List<E> query(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql);

    Transaction getTransaction();

    void commit(boolean required) throws SQLException;

    void rollback(boolean required) throws SQLException;

    void close(boolean forceRollback);

}