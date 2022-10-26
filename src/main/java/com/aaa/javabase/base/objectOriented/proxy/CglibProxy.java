package com.aaa.javabase.base.objectOriented.proxy;

/**
 * 依赖：
 * <dependency>
 * <groupId>cglib</groupId>
 * <artifactId>cglib</artifactId>
 * <version>3.2.4</version>
 * </dependency>
 *
 * @author liuzhen.tian
 * @version 1.0 JavassistProxy.java  2022/10/26 20:23
 */

import com.aaa.javabase.base.objectOriented.proxy.service.UserManager;
import com.aaa.javabase.base.objectOriented.proxy.service.UserManagerImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy {

    public static void main(String[] args) {
        // 代理 class
        UserManager user = (UserManager) createCglibProxy(new UserManagerImpl());
        user.delUser("admin");

        // 代理 class
        UserManager user3 = (UserManager) createCglibProxyV3(new UserManagerImpl());
        user3.delUser("admin3");

        // 代理 interface
        UserManager user2 = (UserManager) createCglibProxyV2(new UserManagerImpl());
        user2.delUser("admin2");
    }

    /**
     * 代理 interface
     */
    public static Object createCglibProxyV2(Object objectTarget) {
        // 为目标对象target赋值
        Enhancer enhancer = new Enhancer();
        // 设置父类为目标代理对象,
        // 因为Cglib是针对指定的类生成一个子类，所以需要指定父类
        enhancer.setInterfaces(new Class[]{UserManager.class});
        // 创建代理执行对象
        enhancer.setCallback(new CglibProxyInterceptor(objectTarget));
        Object result = enhancer.create();//创建并返回代理对象
        return result;
    }

    /**
     * 代理 class (无惨构造)
     */
    public static Object createCglibProxy(Object objectTarget) {
        // 为目标对象target赋值
        Enhancer enhancer = new Enhancer();
        // 设置父类为目标代理对象,
        // 因为Cglib是针对指定的类生成一个子类，所以需要指定父类
        enhancer.setSuperclass(objectTarget.getClass());
        // 创建代理执行对象
        enhancer.setCallback(new CglibProxyInterceptor(objectTarget));
        Object result = enhancer.create();//创建并返回代理对象
        return result;
    }

    /**
     * 代理 class (通过指定参数类型和参数对象，通过有参构造去创建对象)
     */
    public static Object createCglibProxyV3(Object objectTarget) {
        // 为目标对象target赋值
        Enhancer enhancer = new Enhancer();
        // 设置父类为目标代理对象,
        // 因为Cglib是针对指定的类生成一个子类，所以需要指定父类
        enhancer.setSuperclass(objectTarget.getClass());
        // 创建代理执行对象
        enhancer.setCallback(new CglibProxyInterceptor(objectTarget));
        // todo 注意：有参构造参数顺序不能乱
        Class<?>[] typesArray = new Class[]{Long.class, String.class,};
        Object[] valuesArray = new Object[]{1L, "tm"};
        // 通过指定参数类型和参数对象，通过有参构造去创建对象
        Object result = enhancer.create(typesArray, valuesArray);
        return result;
    }


    // Cglib动态代理，实现net.sf.cglib.proxy.MethodInterceptor
    private static class CglibProxyInterceptor implements MethodInterceptor {
        private Object target;//需要代理的目标对象

        public CglibProxyInterceptor(Object target) {
            this.target = target;
        }

        //重写拦截方法
        @Override
        public Object intercept(Object obj, Method method, Object[] arr, MethodProxy proxy) throws Throwable {
            System.out.println("Cglib动态代理，监听开始！");
            //方法执行，参数：target 目标对象 arr参数数组
            Object invoke = method.invoke(target, arr);
            System.out.println("Cglib动态代理，监听结束！");
            return invoke;
        }
    }
}
