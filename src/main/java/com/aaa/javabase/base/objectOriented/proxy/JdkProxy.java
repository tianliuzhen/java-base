package com.aaa.javabase.base.objectOriented.proxy;

/**
 * JdkProxy
 *
 * @author liuzhen.tian
 * @version 1.0 JavassistProxy.java  2022/10/26 20:23
 */

import com.aaa.javabase.base.objectOriented.proxy.service.UserManager;
import com.aaa.javabase.base.objectOriented.proxy.service.UserManagerImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy {

    public static void main(String[] args) {
        //实例化JDKProxy对象
        JdkProxy jdkProxy = new JdkProxy();
        //获取代理对象
        UserManager user = (UserManager) jdkProxy.getJDKProxy(new UserManagerImpl());
        //JDK代理 直接使用  UserManagerImpl 会出现问题，所以jdk代理只能代理接口
        //执行新增方法
        user.addUser("admin", "123123");
    }

    /**
     * 仅支持接口代理
     *
     * @param targetObject 目标对象
     */
    private Object getJDKProxy(Object targetObject) {
        //JDK动态代理只能针对实现了接口的类进行代理，newProxyInstance 函数所需参数就可看出
        return Proxy.newProxyInstance(
                targetObject.getClass().getClassLoader(),// 目标对象类加载器
                targetObject.getClass().getInterfaces(), // 目标对象类接口类型
                new JDkProxyInvocationHandler(targetObject)); // 代理对象
    }


    // JDK动态代理实现 java.lang.reflect.InvocationHandler
    private static class JDkProxyInvocationHandler implements InvocationHandler {
        private Object target;//需要代理的目标对象

        public JDkProxyInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("JDK动态代理，监听开始！");
            Object result = method.invoke(target, args);
            System.out.println("JDK动态代理，监听结束！");
            return result;
        }
    }
}
