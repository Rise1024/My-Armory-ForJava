package com.example.springbean.regist.factory;

import com.example.springbean.regist.domain.Cherry;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author zds
 */
public class CherryFactoryBean implements FactoryBean<Cherry> {
    @Override
    public Cherry getObject() {
        return new Cherry();
    }

    @Override
    public Class<?> getObjectType() {
        return Cherry.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
