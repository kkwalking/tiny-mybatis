package com.kelton.tinymybatis.binding;

import com.kelton.tinymybatis.mapping.MappedStatement;
import com.kelton.tinymybatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Author zhouzekun
 * @Date 2024/5/8 9:44
 */
public class MapperProxy<T> implements InvocationHandler {

    private SqlSession sqlSession;

    private Class<T>  mapperInterface;

    private final Map<Method, MapperMethod> methodCache;

    public MapperProxy(SqlSession sqlSession, Class<T>  mapperInterface, Map<Method, MapperMethod> methodCache) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
        this.methodCache = methodCache;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
//            String fullMethodName = mapperInterface.getName() + "." + method.getName();
//            return sqlSession.selectOne(fullMethodName, args[0]);
            // 再封装一层MapperMethod，用于缓存
            final MapperMethod mapperMethod = cachedMapperMethod(method);
            return mapperMethod.execute(sqlSession, args);
        }
    }

    /**
     * 去缓存中找MapperMethod
     */
    private MapperMethod cachedMapperMethod(Method method) {
        MapperMethod mapperMethod = methodCache.get(method);
        if (mapperMethod == null) {
            //找不到才去new
            mapperMethod = new MapperMethod(mapperInterface, method, sqlSession.getConfiguration());
            methodCache.put(method, mapperMethod);
        }
        return mapperMethod;
    }
}
