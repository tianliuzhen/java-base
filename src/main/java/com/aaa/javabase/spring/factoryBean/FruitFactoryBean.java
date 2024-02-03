package com.aaa.javabase.spring.factoryBean;

import com.aaa.javabase.base.objectOriented.proxy.CglibProxy;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 MyFactoryBean.java  2023/6/14 22:33
 */
@Component
public class FruitFactoryBean implements FactoryBean<Object> {

    @Nullable
    private Object proxy;

    @Override
    public Object getObject() {
        // 创建AppleBean的代理对象
        return CglibProxy.createCglibProxy(new AppleBean());
    }

    @Override
    public Class<?> getObjectType() {
        return AppleBean.class;
    }
}
