package com.kelton.tinymybatis.reflection;

import com.kelton.tinymybatis.reflection.factory.DefaultObjectFactory;
import com.kelton.tinymybatis.reflection.factory.ObjectFactory;
import com.kelton.tinymybatis.reflection.wrapper.DefaultObjectWrapperFactory;
import com.kelton.tinymybatis.reflection.wrapper.ObjectWrapperFactory;

/**
 * @Author zhouzekun
 * @Date 2024/5/20 10:44
 */
public class SystemMetaObject {

    public static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    public static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    public static final MetaObject NULL_META_OBJECT = MetaObject.forObject(NullObject.class, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);

    private SystemMetaObject() {
        // Prevent Instantiation of Static Class
    }

    /**
     * 空对象
     */
    private static class NullObject {
    }

    public static MetaObject forObject(Object object) {
        return MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
    }
}
