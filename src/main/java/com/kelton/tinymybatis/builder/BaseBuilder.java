package com.kelton.tinymybatis.builder;

import com.kelton.tinymybatis.session.Configuration;
import com.kelton.tinymybatis.type.TypeAliasRegistry;

/**
 * @Author zhouzekun
 * @Date 2024/5/8 15:12
 */
public abstract class BaseBuilder {

    protected final Configuration configuration;
    protected final TypeAliasRegistry typeAliasRegistry;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
        this.typeAliasRegistry = this.configuration.getTypeAliasRegistry();
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
