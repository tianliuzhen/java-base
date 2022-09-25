package com.aaa.javabase.pattern.structure.proxy.demo2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author liuzhen.tian
 * @version 1.0 PhoneSaleJdkProxy.java  2022/9/25 13:54
 */
public class PhoneSaleJdkProxy implements InvocationHandler {
    // 被代理对象
    private Object phoneService;

    public PhoneSaleJdkProxy(Object phoneService) {
        this.phoneService = phoneService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = method.invoke(phoneService, args);
        System.out.println("代理对象 " + "帮贴膜...");
        return invoke;
    }
}
