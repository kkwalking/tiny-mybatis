package com.kelton.tinymybatis.session;

/**
 * @Author zhouzekun
 * @Date 2024/5/8 10:31
 */
public interface SqlSession {

    /**
     * 根据SqlId来获取一条记录
     * @param statement SqlId
     * @return 记录(是一个封装对象)
     */
    <T> T selectOne(String statement);

    /**
     * 根据SqlId来获取一条记录
     * @param statement statement SqlId
     * @param parameter 传递给sql的参数
     * @return 记录(是一个封装对象)
     */
    <T> T selectOne(String statement, Object parameter);


    /**
     * 根据type获得映射器
     * @param type
     * @return
     */
    <T> T getMapper(Class<T> type);


    /**
     * 获取配置
     * @return
     */
    Configuration getConfiguration();

}
