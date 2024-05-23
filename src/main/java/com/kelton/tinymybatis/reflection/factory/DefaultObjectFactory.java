package com.kelton.tinymybatis.reflection.factory;

import java.lang.reflect.Constructor;
import java.util.*;

/**
 * @Author zhouzekun
 * @Date 2024/5/20 11:27
 */
public class DefaultObjectFactory implements ObjectFactory {
    @Override
    public void setProperties(Properties properties) {
//        目前的对象工厂无参数可设置
    }

    @Override
    public <T> T create(Class<T> type) {
        return create(type, null, null);
    }

    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        // 1. 解析接口
        Class<?> classToCreate = resolveInterface(type);
        // 2. 类实例化
        return (T) instantiateClass(classToCreate, constructorArgTypes, constructorArgs);

    }

    private <T> T instantiateClass(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        try {
            Constructor<T> constructor;
            if (constructorArgTypes == null || constructorArgs == null) {
                // 无参构造函数
                constructor = type.getDeclaredConstructor();
                if (constructor.isAccessible()) {
                    constructor.setAccessible(true);
                }
                return constructor.newInstance();

            }
            // 否则，使用有参构造器
            constructor = type.getDeclaredConstructor(constructorArgTypes.toArray(new Class[constructorArgTypes.size()]));
            if (constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance(constructorArgs.toArray(new Object[constructorArgs.size()]));
        } catch (Exception e) {
            // 如果出错
            StringBuilder argTypes = new StringBuilder();
            if (constructorArgTypes != null) {
                for (Class<?> argType : constructorArgTypes) {
                    argTypes.append(argType.getSimpleName());
                    argTypes.append(",");
                }
            }
            StringBuilder argValues = new StringBuilder();
            if (constructorArgs != null) {
                for (Object argValue : constructorArgs) {
                    argValues.append(argValue);
                    argValues.append(",");
                }
            }
            throw new RuntimeException("Error instantiating " + type + " with invalid types (" + argTypes + ") or values (" + argValues + "). Cause: " + e, e);
        }


    }

    /**
     * 解析接口，将 interface 转换为实际的 class 类
     * 传过来的可能是接口，将接口化为具体的实现类，比如List转为ArrayList
     */
    private <T> Class<?> resolveInterface(Class<T> type) {
        Class<?> classToCreate;
        if (type == List.class || type == Collection.class || type == Iterable.class) {
            // List|Collection|Iterable-->ArrayList
            classToCreate = ArrayList.class;
        } else if (type == Map.class) {
            // Map->HashMap
            classToCreate = HashMap.class;
        } else if (type == SortedSet.class) {
            // SortedSet->TreeSet
            classToCreate = TreeSet.class;
        } else if (type == Set.class) {
            // Set->HashSet
            classToCreate = HashSet.class;
        } else {
            // 除此以外，就用原来的类型
            classToCreate = type;
        }
        return classToCreate;
    }

    @Override
    public <T> boolean isCollection(Class<T> type) {
        return Collection.class.isAssignableFrom(type);
    }
}
