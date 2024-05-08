package com.kelton.tinymybatis.binding;

import com.kelton.tinymybatis.session.Configuration;
import com.kelton.tinymybatis.session.SqlSession;
import com.kelton.tinymybatis.util.ClassScanner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author zhouzekun
 * @Date 2024/5/8 10:37
 */
public class MapperRegistry {

    private final Configuration configuration;
    private Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public MapperRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("Type " + type + "is not known to the MapperRegistry.");
        }
        try {
            return mapperProxyFactory.newInstant(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException("Error getting mapper instance. Cause: " + e, e);
        }
    }

    public <T> void addMapper(Class<T> type) {
        if (type.isInterface()) {
            if (hasMapper(type)) {
                throw new RuntimeException("Type " + type + " is already been add to MapperRegistry.");
            }
            knownMappers.put(type, new MapperProxyFactory<>(type));
        }
    }

    public  <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }

    public void addMappers(String packageName) {
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        for (Class<?> type : mapperSet){
            addMapper(type);
        }
    }
}
