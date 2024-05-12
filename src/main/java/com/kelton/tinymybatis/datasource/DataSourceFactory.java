package com.kelton.tinymybatis.datasource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 数据源工厂
 * datasource直接用JDK提供的
 */
public interface DataSourceFactory {

    void setProperties(Properties props);

    DataSource getDataSource();

}
