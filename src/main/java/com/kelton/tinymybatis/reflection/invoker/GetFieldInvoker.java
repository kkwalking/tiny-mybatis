package com.kelton.tinymybatis.reflection.invoker;

import java.lang.reflect.Field;

/**
 * @Author zhouzekun
 * @Date 2024/5/20 10:47
 */
public class GetFieldInvoker implements Invoker {

    private Field field;

    public GetFieldInvoker(Field field) {
        this.field = field;
    }

    @Override
    public Object invoke(Object target, Object[] args) throws Exception {
        return field.get(target);
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }
}
