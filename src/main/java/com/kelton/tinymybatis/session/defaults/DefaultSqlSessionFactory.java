package com.kelton.tinymybatis.session.defaults;

import com.kelton.tinymybatis.proxy.MapperRegistry;
import com.kelton.tinymybatis.session.SqlSession;
import com.kelton.tinymybatis.session.SqlSessionFactory;

/**
 * @Author zhouzekun
 * @Date 2024/5/8 10:32
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
