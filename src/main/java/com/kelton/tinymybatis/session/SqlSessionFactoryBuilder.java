package com.kelton.tinymybatis.session;

import com.kelton.tinymybatis.builder.xml.XMLConfigBuilder;
import com.kelton.tinymybatis.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * 通过读取mybatis配置文件来构造Configuration，并设置给SqlSessionFactory
 * @Author zhouzekun
 * @Date 2024/5/8 15:12
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }
}
