package com.kelton.tinymybatis.session.defaults;

import com.kelton.tinymybatis.binding.MapperRegistry;
import com.kelton.tinymybatis.session.Configuration;
import com.kelton.tinymybatis.session.SqlSession;
import com.kelton.tinymybatis.session.SqlSessionFactory;

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
        return new DefaultSqlSession(configuration);
    }
}
