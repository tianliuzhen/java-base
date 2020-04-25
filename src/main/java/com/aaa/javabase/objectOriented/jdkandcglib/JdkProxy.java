package com.aaa.javabase.objectOriented.jdkandcglib;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/4/24
 */

import com.aaa.javabase.objectOriented.jdkandcglib.service.UserManager;
import com.aaa.javabase.objectOriented.jdkandcglib.service.UserManagerImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//JDK动态代理实现InvocationHandler接口
public class JdkProxy implements InvocationHandler {
    private Object target ;//需要代理的目标对象

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK动态代理，监听开始！");
        Object result = method.invoke(target, args);
        System.out.println("JDK动态代理，监听结束！");
        return result;
    }
    //定义获取代理对象方法
    private Object getJDKProxy(Object targetObject){
        //为目标对象target赋值
        this.target = targetObject;
        //JDK动态代理只能针对实现了接口的类进行代理，newProxyInstance 函数所需参数就可看出
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), this);
    }

    public static void main(String[] args) {
        //实例化JDKProxy对象
        JdkProxy jdkProxy = new JdkProxy();
        //获取代理对象
        UserManager user = (UserManager) jdkProxy.getJDKProxy(new UserManagerImpl());
        //JDK代理 直接使用  UserManagerImpl 会出现问题，所以jdk代理只能代理接口
        //执行新增方法
        user.addUser("admin", "123123");
    }

}
