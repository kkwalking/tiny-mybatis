package com.kelton.tinymybatis.session;

import com.kelton.tinymybatis.binding.MapperRegistry;
import com.kelton.tinymybatis.datasource.druid.DruidDataSourceFactory;
import com.kelton.tinymybatis.datasource.pooled.PooledDataSourceFactory;
import com.kelton.tinymybatis.datasource.unpooled.UnpooledDataSourceFactory;
import com.kelton.tinymybatis.executor.Executor;
import com.kelton.tinymybatis.executor.SimpleExecutor;
import com.kelton.tinymybatis.executor.resultset.DefaultResultSetHandler;
import com.kelton.tinymybatis.executor.resultset.ResultSetHandler;
import com.kelton.tinymybatis.executor.statement.PreparedStatementHandler;
import com.kelton.tinymybatis.executor.statement.StatementHandler;
import com.kelton.tinymybatis.mapping.BoundSql;
import com.kelton.tinymybatis.mapping.Environment;
import com.kelton.tinymybatis.mapping.MappedStatement;
import com.kelton.tinymybatis.transaction.Transaction;
import com.kelton.tinymybatis.transaction.jdbc.JdbcTransactionFactory;
import com.kelton.tinymybatis.type.TypeAliasRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhouzekun
 * @Date 2024/5/8 15:11
 */
public class Configuration {
    /**
     * 类型别名注册机
     */
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

    /**
     * 环境
     */
    protected Environment environment;

    /**
     * 映射注册机
     */
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    /**
     * 映射的语句，存在Map里
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("UNPOOLED", UnpooledDataSourceFactory.class);
    }

    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }


    /**
     * 创建结果集处理器
     */
    public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement mappedStatement, BoundSql boundSql) {
        return new DefaultResultSetHandler(executor, mappedStatement, boundSql);
    }

    /**
     * 生产执行器
     */
    public Executor newExecutor(Transaction transaction) {
        return new SimpleExecutor(this, transaction);
    }

    /**
     * 创建语句处理器
     */
    public StatementHandler newStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        return new PreparedStatementHandler(executor, mappedStatement, parameter, resultHandler, boundSql);
    }
}
