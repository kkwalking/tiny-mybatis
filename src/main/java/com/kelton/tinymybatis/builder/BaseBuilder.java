package com.kelton.tinymybatis.builder;

import com.kelton.tinymybatis.session.Configuration;

/**
 * @Author zhouzekun
 * @Date 2024/5/8 15:12
 */
public abstract class BaseBuilder {

    protected final Configuration configuration;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
