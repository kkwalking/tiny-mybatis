package com.kelton.tinymybatis.session;

/**
 * @Author zhouzekun
 * @Date 2024/5/8 10:31
 */
public interface SqlSessionFactory {


    SqlSession openSession();
}
