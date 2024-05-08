package com.kelton.tinymybatis.proxy;

import com.kelton.tinymybatis.session.SqlSession;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @Author zhouzekun
 * @Date 2024/5/8 9:45
 */
public class MapperProxyFactory<T> {

    private Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstant(SqlSession sqlSession) {
        MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
}
