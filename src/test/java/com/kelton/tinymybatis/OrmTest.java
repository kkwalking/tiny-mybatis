package com.kelton.tinymybatis;

import com.kelton.tinymybatis.dao.AdminDao;
import com.kelton.tinymybatis.dao.UserDao;
import com.kelton.tinymybatis.proxy.MapperProxyFactory;
import com.kelton.tinymybatis.proxy.MapperRegistry;
import com.kelton.tinymybatis.session.SqlSession;
import com.kelton.tinymybatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * @Author zhouzekun
 * @Date 2024/5/8 10:09
 */
public class OrmTest {


    @Test
    public void test_MapperProxyFactory() {
        MapperRegistry mapperRegistry = new MapperRegistry();
        mapperRegistry.addMappers("com.kelton.tinymybatis.dao");

        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(mapperRegistry);
        SqlSession sqlSession = defaultSqlSessionFactory.openSession();

        UserDao userDao = sqlSession.getMapper(UserDao.class);
        AdminDao adminDao = sqlSession.getMapper(AdminDao.class);

        String username = userDao.findByUsername("kelton");
        String adminName = adminDao.getAdminName("1");
        System.out.println("测试结果: " + username);
        System.out.println("测试结果: " + adminName);
    }
}
