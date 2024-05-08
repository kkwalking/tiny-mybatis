package com.kelton.tinymybatis.binding;

import com.kelton.tinymybatis.session.SqlSession;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author zhouzekun
 * @Date 2024/5/8 9:45
 */
public class MapperProxyFactory<T> {

    private Class<T> mapperInterface;
    private Map<Method, MapperMethod> methodCache = new ConcurrentHashMap<Method, MapperMethod>();

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public Map<Method, MapperMethod> getMethodCache() {
        return methodCache;
    }

    public T newInstant(SqlSession sqlSession) {
        MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface, methodCache);
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
}
