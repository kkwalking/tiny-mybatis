package com.kelton.tinymybatis;

import com.kelton.tinymybatis.dao.UserDao;
import com.kelton.tinymybatis.proxy.MapperProxy;
import com.kelton.tinymybatis.proxy.MapperProxyFactory;

import java.util.HashMap;

/**
 * @Author zhouzekun
 * @Date 2024/5/8 9:06
 */
public class Main {
    public static void main(String[] args) {
        HashMap<String, String> sqlSession = new HashMap<>();
        sqlSession.put("com.kelton.tinymybatis.dao.UserDao.findByUsername", "findByUsername模拟从数据库获取内容");

        MapperProxyFactory<UserDao> userDaoMapperProxyFactory = new MapperProxyFactory<>(UserDao.class);
        UserDao userDao = userDaoMapperProxyFactory.newInstant(sqlSession);

        System.out.println(userDao.findByUsername("kelton"));
    }
}
