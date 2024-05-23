package com.kelton.tinymybatis.reflection.invoker;

import java.lang.reflect.Method;

/**
 * @Author zhouzekun
 * @Date 2024/5/20 10:52
 */
public class MethodInvoker implements Invoker{

    private Method method;

    private Class<?> type;

    public MethodInvoker(Method method) {
        this.method = method;

        if (method.getParameterTypes().length == 1) {
            this.type =  method.getParameterTypes().getClass();
        } else {
            this.type = method.getReturnType();
        }
    }

    @Override
    public Object invoke(Object target, Object[] args) throws Exception {
        return method.invoke(target, args);
    }

    @Override
    public Class<?> getType() {
        return method.getReturnType();
    }
}
