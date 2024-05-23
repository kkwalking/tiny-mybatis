package com.kelton.tinymybatis.datasource.pooled;


import com.kelton.tinymybatis.datasource.unpooled.UnpooledDataSourceFactory;

/**
 * 有连接池的数据源工厂
 */
public class PooledDataSourceFactory extends UnpooledDataSourceFactory {

    public PooledDataSourceFactory() {
        this.dataSource = new PooledDataSource();
    }

}
