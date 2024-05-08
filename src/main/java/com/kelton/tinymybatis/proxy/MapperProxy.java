package com.kelton.tinymybatis.proxy;

import com.alibaba.fastjson2.JSON;
import com.kelton.tinymybatis.dao.UserDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author zhouzekun
 * @Date 2024/5/8 9:44
 */
public class MapperProxy<T> implements InvocationHandler {

    private Map<String, String> sqlSession;

    private Class<T>  mapperInterface;

    public MapperProxy(Map<String, String> sqlSession,  Class<T>  mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            System.out.println("正在执行：" + JSON.toJSONString(sqlSession));
            return "被代理方法：" + method.getName() + ", 方法参数:" +  Arrays.stream(args).map(Object::toString).collect(Collectors.joining(","));
        }
    }
}
