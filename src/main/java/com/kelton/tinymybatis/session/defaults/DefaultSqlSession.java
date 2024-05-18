package com.kelton.tinymybatis.session.defaults;

import com.kelton.tinymybatis.executor.Executor;
import com.kelton.tinymybatis.mapping.MappedStatement;
import com.kelton.tinymybatis.session.Configuration;
import com.kelton.tinymybatis.session.SqlSession;

import java.util.List;

/**
 * @Author zhouzekun
 * @Date 2024/5/8 10:31
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> T selectOne(String statement) {
        // 目前只是简单打印一下SQL语句，后续可以操作JDBC进行数据库操作、管理事务等
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        return (T) ("selectOne简单实现" + statement+ ", 待执行的SQL:\n\t" + mappedStatement.getBoundSql().getSql());
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        MappedStatement ms = configuration.getMappedStatement(statement);
        List<T> list = executor.query(ms, parameter, Executor.NO_RESULT_HANDLER, ms.getBoundSql());
        return list.get(0);
    }


    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public String toString() {
        return "DefaultSqlSession{" +
                "configuration=" + configuration +
                '}';
    }
}
