package com.aaa.javabase.pattern.structure.proxy.demo2;

import com.aaa.javabase.pattern.structure.proxy.demo1.PhoneService;
import com.aaa.javabase.pattern.structure.proxy.demo1.PhoneServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 动态代理具体步骤：
 *
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2022/9/25 13:57
 */
public class TestMain {
    public static void main(String[] args) {
        // 被代理对象
        PhoneService phoneService = new PhoneServiceImpl();
        // 代理对象
        InvocationHandler phoneSaleJdkProxy = new PhoneSaleJdkProxy(phoneService);

        /**
         * 实现步骤:
         * 1、通过实现 InvocationHandler 接口创建自己的调用处理器；
         * 2、通过为 Proxy 类指定 ClassLoader 对象和一组 interface 来创建动态代理类；
         * 3、通过反射机制获得动态代理类的构造函数，其唯一参数类型是调用处理器接口类型；
         * 4、通过构造函数创建动态代理类实例，构造时调用处理器对象作为参数被传入。
         */
        PhoneService service = (PhoneService) Proxy.newProxyInstance(
                // 被代理对象类加载器
                PhoneServiceImpl.class.getClassLoader(),
                // 被代理对象接口
                PhoneServiceImpl.class.getInterfaces(),
                // 代理对象
                phoneSaleJdkProxy);

        service.service();
    }
}
