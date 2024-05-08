package com.kelton.tinymybatis;

import com.kelton.tinymybatis.dao.AdminDao;
import com.kelton.tinymybatis.dao.UserDao;
import com.kelton.tinymybatis.binding.MapperRegistry;
import com.kelton.tinymybatis.io.Resources;
import com.kelton.tinymybatis.session.SqlSession;
import com.kelton.tinymybatis.session.SqlSessionFactory;
import com.kelton.tinymybatis.session.SqlSessionFactoryBuilder;
import com.kelton.tinymybatis.session.defaults.DefaultSqlSessionFactory;
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
        String res = userDao.findByUsername("kelton");
        logger.info("测试结果：{}", res);
    }
}
