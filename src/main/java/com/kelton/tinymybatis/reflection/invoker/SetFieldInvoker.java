package com.kelton.tinymybatis.reflection.invoker;

import java.lang.reflect.Field;

/**
 * @Author zhouzekun
 * @Date 2024/5/20 10:50
 */
public class SetFieldInvoker implements Invoker{

    private Field field;

    public SetFieldInvoker(Field field) {
        this.field = field;
    }

    @Override
    public Object invoke(Object target, Object[] args) throws Exception {
        field.set(target, args);
        return null;
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }
}
