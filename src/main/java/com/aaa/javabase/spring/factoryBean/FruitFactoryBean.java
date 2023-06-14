package com.aaa.javabase.spring.factoryBean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 MyFactoryBean.java  2023/6/14 22:33
 */
@Component
public class FruitFactoryBean implements FactoryBean<AppleBean> {

    @Override
    public AppleBean getObject() throws Exception {
        return new AppleBean();
    }

    @Override
    public Class<?> getObjectType() {
        return AppleBean.class;
    }
}
