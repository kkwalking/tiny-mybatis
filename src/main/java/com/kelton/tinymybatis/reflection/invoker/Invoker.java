package com.kelton.tinymybatis.reflection.invoker;

/**
 * @Author zhouzekun
 * @Date 2024/5/20 10:46
 */
public interface Invoker {


    Object invoke(Object target, Object[] args) throws Exception;

    Class<?> getType();

}
