package com.aaa.javabase.common.retry;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 JdkProxyRetry.java  2023/8/6 17:25
 */
@Slf4j
public class JdkProxyRetry {
    // JDK动态代理实现 java.lang.reflect.InvocationHandler
    private static class JDkRetryInvocationHandler implements InvocationHandler {
        private Object target;
        private int retryCount = 3;

        public JDkRetryInvocationHandler(Object target, int retryCount) {
            this.target = target;
            this.retryCount = retryCount;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("JDK动态代理，监听开始！");
            Object result = null;
            int count = 0;
            while (count < retryCount) {
                boolean isSkip = true;
                try {
                    result = method.invoke(target, args);
                } catch (Exception e) {
                    isSkip = false;
                    TimeUnit.SECONDS.sleep(1);
                    log.error("JDKRetry.intercept.重试" + (count + 1) + "几次");
                }
                if (isSkip) {
                    break;
                }
                count++;
            }

            System.out.println("JDK动态代理，监听结束！");
            return result;
        }
    }

    /**
     * 仅支持接口代理
     *
     * @param targetObject 目标对象
     */
    public static Object getJDKProxy(Object targetObject, int retryCount) {
        //JDK动态代理只能针对实现了接口的类进行代理，newProxyInstance 函数所需参数就可看出
        return Proxy.newProxyInstance(
                targetObject.getClass().getClassLoader(),// 目标对象类加载器
                targetObject.getClass().getInterfaces(), // 目标对象类接口类型
                new JdkProxyRetry.JDkRetryInvocationHandler(targetObject, retryCount)); // 代理对象
    }

    public static void main(String[] args) {
        RetryBean.RetryBeanService jdkProxy = (RetryBean.RetryBeanService) JdkProxyRetry.getJDKProxy(new RetryBean.RetryBeanServiceImpl(), 3);
        jdkProxy.say();
    }

}
