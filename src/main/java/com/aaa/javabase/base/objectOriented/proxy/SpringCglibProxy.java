package com.aaa.javabase.base.objectOriented.proxy;


import com.aaa.javabase.base.objectOriented.proxy.service.UserManager;
import com.aaa.javabase.base.objectOriented.proxy.service.UserManagerImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author liuzhen.tian
 * @version 1.0 SpringCglibProxy.java  2023/8/25 21:45
 */
public class SpringCglibProxy {
    public static void main(String[] args) {
        // 代理 class
        UserManager user = (UserManager) createCglibProxy(new UserManagerImpl());
        user.delUser("admin");
    }

    public static Object createCglibProxy(Object objectTarget) {
        // 为目标对象target赋值
        Enhancer enhancer = new Enhancer();
        // 设置父类为目标代理对象,
        // 因为Cglib是针对指定的类生成一个子类，所以需要指定父类
        enhancer.setSuperclass(objectTarget.getClass());
        // 创建代理执行对象
        enhancer.setCallback(new SpringCglibProxy.CglibProxyInterceptor(objectTarget));
        Object result = enhancer.create();//创建并返回代理对象
        return result;
    }

    //org.springframework.cglib.proxy.MethodProxy
    private static class CglibProxyInterceptor implements MethodInterceptor {
        private Object target;//需要代理的目标对象

        public CglibProxyInterceptor(Object target) {
            this.target = target;
        }

        //重写拦截方法
        @Override
        public Object intercept(Object obj, Method method, Object[] arr, MethodProxy proxy) throws Throwable {
            System.out.println("org.springframework.cglib-动态代理，监听开始！");
            //方法执行，参数：target 目标对象 arr参数数组
            Object invoke = method.invoke(target, arr);
            System.out.println("org.springframework.cglib-动态代理，监听结束！");
            return invoke;
        }
    }
}
