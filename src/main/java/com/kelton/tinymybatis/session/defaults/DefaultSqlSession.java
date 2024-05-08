package com.kelton.tinymybatis.session.defaults;

import com.kelton.tinymybatis.proxy.MapperRegistry;
import com.kelton.tinymybatis.session.SqlSession;

/**
 * @Author zhouzekun
 * @Date 2024/5/8 10:31
 */
public class DefaultSqlSession implements SqlSession {

    private MapperRegistry mapperRegistry;

    public DefaultSqlSession(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T)("selectOne简单实现" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T)("不带参的selectOne简单实现" + statement + "," + parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return mapperRegistry.getMapper(type, this);
    }

    @Override
    public String toString() {
        return "DefaultSqlSession{" +
                "mapperRegistry=" + mapperRegistry +
                '}';
    }
}
