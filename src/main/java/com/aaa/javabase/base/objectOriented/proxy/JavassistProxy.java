package com.aaa.javabase.base.objectOriented.proxy;

import com.aaa.javabase.base.objectOriented.proxy.service.UserManager;
import com.aaa.javabase.base.objectOriented.proxy.service.UserManagerImpl;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;
import lombok.SneakyThrows;
import org.apache.ibatis.executor.ExecutorException;

import java.lang.reflect.Method;

/**
 * 依赖：
 *   <dependency>
 *             <groupId>org.javassist</groupId>
 *             <artifactId>javassist</artifactId>
 *             <version>${javassist.version}</version>
 *             <optional>false</optional>  // optional=true 表示这个 Jar 不能被传递依赖
 *    </dependency>
 *
 * @author liuzhen.tian
 * @version 1.0 JavassistProxy.java  2022/10/26 20:23
 */
public class JavassistProxy {
    @SneakyThrows
    public static void main(String[] args) {

        // 代理 interface
        UserManager javassistDynamicProxy = createJavassistProxy();
        javassistDynamicProxy.delUser("ddd");

        // 代理 class （无参构造）
        UserManager javassistDynamicProxyV2 = createJavassistProxyV2();
        javassistDynamicProxyV2.delUser("xxx");

        // 代理 class (通过指定参数类型和参数对象，通过有参构造去创建对象)
        UserManager javassistDynamicProxyV3 = createJavassistProxyV3();
        javassistDynamicProxyV3.delUser("ccc");
    }

    /**
     * 代理 interface
     */
    private static UserManager createJavassistProxy() throws Exception {
        ProxyFactory proxyFactory = new ProxyFactory();
        // 设置实现的接口
        proxyFactory.setInterfaces(new Class[]{UserManager.class});
        Class<?> proxyClass = proxyFactory.createClass();
        // 无参构造代理对象
        UserManager javassistProxy = (UserManager) proxyClass.getDeclaredConstructor().newInstance();
        // 代理对象设置目标对象
        JavassistInterceptor methodHandler = new JavassistInterceptor(new UserManagerImpl());
        ((Proxy) javassistProxy).setHandler(methodHandler);
        proxyFactory.setHandler(methodHandler);
        return javassistProxy;
    }

    /**
     * 代理 class （无参构造）
     */
    private static UserManagerImpl createJavassistProxyV2() throws Exception {
        ProxyFactory proxyFactory = new ProxyFactory();
        // 目标对象
        proxyFactory.setSuperclass(UserManagerImpl.class);
        // 代理对象
        proxyFactory.setHandler(new JavassistInterceptor(new UserManagerImpl()));

        Class clazz = proxyFactory.createClass();
        Object proxy = clazz.newInstance();
        return (UserManagerImpl) proxy;
    }

    /**
     * 代理 class (通过指定参数类型和参数对象，通过有参构造去创建对象)
     */
    private static UserManagerImpl createJavassistProxyV3() throws Exception {
        ProxyFactory proxyFactory = new ProxyFactory();
        // 目标对象
        proxyFactory.setSuperclass(UserManagerImpl.class);

        Object enhanced;
        try {
            // mybatis 懒加载片段
            // final List<Class<?>> constructorArgTypes = Lists.newArrayList(String.class);
            // final List<Object> constructorArgs = Lists.newArrayList("tm");
            // Class<?>[] typesArray = constructorArgTypes.toArray(new Class[constructorArgTypes.size()]);
            // Object[] valuesArray = constructorArgs.toArray(new Object[constructorArgs.size()]);

            Class<?>[] typesArray = new Class[]{String.class};
            Object[] valuesArray = new Object[]{"tm"};
            // 通过指定参数类型和参数对象，通过有参构造去创建对象
            enhanced = proxyFactory.create(typesArray, valuesArray);
        } catch (Exception e) {
            throw new ExecutorException("Error creating lazy proxy.  Cause: " + e, e);
        }
        // 代理对象
        ((Proxy) enhanced).setHandler(new JavassistInterceptor(new UserManagerImpl()));

        return (UserManagerImpl) enhanced;
    }

    // 代理对象接口是：javassist.util.proxy.MethodHandler，同名接口比较多，注意别导错了
    private static class JavassistInterceptor implements MethodHandler {
        // 被代理对象
        private Object delegate;

        private JavassistInterceptor(Object delegate) {
            this.delegate = delegate;
        }

        /**
         * @param self    创建的代理对象
         * @param m       被代理方法
         * @param proceed 如果代理接口，此参数为null，如果代理类，此参数为父类的方法
         * @param args    方法参数
         */
        @Override
        public Object invoke(Object self, Method m, Method proceed, Object[] args) throws Throwable {
            System.out.println("javassist proxy before sing");
            Object ret = m.invoke(delegate, args);
            System.out.println("javassist proxy after sing");
            return ret;
        }
    }
}
