package com.kelton.tinymybatis.reflection.wrapper;

import com.kelton.tinymybatis.reflection.MetaObject;
import com.kelton.tinymybatis.reflection.factory.ObjectFactory;
import com.kelton.tinymybatis.reflection.property.PropertyTokenizer;

import java.util.Collection;
import java.util.List;

/**
 * @Author zhouzekun
 * @Date 2024/5/20 16:07
 */
public class CollectionWrapper extends BaseWrapper{
    // 原来的对象
    private Collection<Object> object;

    public CollectionWrapper(MetaObject metaObject, Collection<Object> object) {
        super(metaObject);
        this.object = object;
    }

    @Override
    public Object get(PropertyTokenizer prop) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(PropertyTokenizer prop, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String findProperty(String name, boolean useCamelCaseMapping) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String[] getGetterNames() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String[] getSetterNames() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<?> getSetterType(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<?> getGetterType(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasSetter(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasGetter(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MetaObject instantiatePropertyValue(String name, PropertyTokenizer prop, ObjectFactory objectFactory) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isCollection() {
        return true;
    }

    @Override
    public void add(Object element) {
        object.add(element);
    }

    @Override
    public <E> void addAll(List<E> element) {
        object.add(element);
    }
}
