package com.aaa.javabase.spring.factoryBean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author liuzhen.tian
 * @version 1.0 MyFactoryBean.java  2023/6/14 22:33
 */
@Component
public class OrangeFactoryBean implements FactoryBean<Object> {

    @Nullable
    private Object proxy;

    @Override
    public Object getObject() {
        // 创建AppleBean的代理对象
        return Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class<?>[]{OrangeBean.class},
                new HashMapHandler());
    }

    @Override
    public Class<?> getObjectType() {
        return OrangeBean.class;
    }

    private class HashMapHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return new Object();
        }
    }
}
