package com.kelton.tinymybatis.proxy;

import com.alibaba.fastjson2.JSON;
import com.kelton.tinymybatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Author zhouzekun
 * @Date 2024/5/8 9:44
 */
public class MapperProxy<T> implements InvocationHandler {

    private SqlSession sqlSession;

    private Class<T>  mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T>  mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            System.out.println("正在执行：" + JSON.toJSONString(sqlSession));
            return sqlSession.selectOne(method.getName(), args[0]);
        }
    }
}
