package com.kelton.tinymybatis;

import com.alibaba.fastjson2.JSON;
import com.kelton.tinymybatis.builder.xml.XMLConfigBuilder;
import com.kelton.tinymybatis.dao.UserDao;
import com.kelton.tinymybatis.io.Resources;
import com.kelton.tinymybatis.po.User;
import com.kelton.tinymybatis.session.Configuration;
import com.kelton.tinymybatis.session.SqlSession;
import com.kelton.tinymybatis.session.SqlSessionFactory;
import com.kelton.tinymybatis.session.SqlSessionFactoryBuilder;
import com.kelton.tinymybatis.session.defaults.DefaultSqlSession;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;

/**
 * @Author zhouzekun
 * @Date 2024/5/8 10:09
 */
public class OrmTest {

    private Logger logger = LoggerFactory.getLogger(OrmTest.class);

    @Test
    public void test_MapperProxyFactory() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);

        // 3. 测试验证
        for (int i = 0; i < 50; i++) {
            User user = userDao.queryUserInfoById(1L);
            logger.info("测试结果：{}", JSON.toJSONString(user));
        }
        reader.close();
    }

    @Test
    public void test_selectOne() throws IOException {
        // 解析 XML
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        Configuration configuration = xmlConfigBuilder.parse();

        // 获取 DefaultSqlSession
        SqlSession sqlSession = new DefaultSqlSession(configuration);

        // 执行查询：默认是一个集合参数
        Object[] req = {1L};
        Object res = sqlSession.selectOne("com.kelton.tinymybatis.dao.UserDao.queryUserInfoById", req);
        logger.info("测试结果：{}", JSON.toJSONString(res));
    }
}
