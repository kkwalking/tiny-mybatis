package com.kelton.tinymybatis.reflection.wrapper;

import com.kelton.tinymybatis.reflection.MetaObject;

public interface ObjectWrapperFactory {

    /**
     * 判断有没有包装器
     */
    boolean hasWrapperFor(Object object);

    /**
     * 得到包装器
     */
    ObjectWrapper getWrapperFor(MetaObject metaObject, Object object);

}