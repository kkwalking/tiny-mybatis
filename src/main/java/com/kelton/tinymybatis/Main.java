package com.kelton.tinymybatis;

import com.kelton.tinymybatis.dao.UserDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Author zhouzekun
 * @Date 2024/5/8 9:06
 */
public class Main {
    public static void main(String[] args) {
        UserDao o = (UserDao) Proxy.newProxyInstance(Main.class.getClassLoader(), new Class[]{UserDao.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

                return "被代理方法：" + method.getName() + ", objects:" +  Arrays.stream(objects).map(e -> e.toString()).collect(Collectors.joining(","));
            }
        });

        System.out.println(o.findByUsername("kelton"));
    }
}
