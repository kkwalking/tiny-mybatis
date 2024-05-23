package com.kelton.tinymybatis.reflection.factory;

import java.util.List;
import java.util.Properties;

/**
 * @Author zhouzekun
 * @Date 2024/5/20 11:23
 */
public interface ObjectFactory {

    /**
     * 设置属性
     * @param properties
     */
    void setProperties(Properties properties);

    /**
     * 生产对象， 相当于无参构造器
     * @param type object type
     * @return <T>
     */
    <T> T create(Class<T> type);

    /**
     * 生产对象，相当于有参构造器
     * @param type
     * @param constructorArgTypes
     * @param constructorArgs
     * @return
     * @param <T>
     */
    <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs);

    /**
     * 是否是集合对象
     * @param type
     * @return
     * @param <T>
     */
    <T> boolean isCollection(Class<T> type);
}
