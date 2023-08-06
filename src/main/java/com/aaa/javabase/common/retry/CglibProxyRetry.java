package com.aaa.javabase.common.retry;

import lombok.extern.log4j.Log4j2;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 CglibProxyRetry.java  2023/8/6 17:26
 */
@Log4j2
public class CglibProxyRetry {
    /**
     * cglib 代理重试
     */
    private static class CglibRetryInterceptor implements MethodInterceptor {
        private Object target;
        private int retryCount = 3;

        public CglibRetryInterceptor(Object target, int retryCount) {
            this.target = target;
            this.retryCount = retryCount;
        }

        @Override
        public Object intercept(Object obj, Method method, Object[] arr, MethodProxy proxy) throws Throwable {
            System.out.println("CglibRetryInterceptor动态代理，监听开始！");
            //方法执行，参数：target 目标对象 arr参数数组
            Object invoke = null;
            for (int i = 0; i < retryCount; i++) {
                boolean isSkip = true;
                try {
                    // 代理执行
                    invoke = method.invoke(target, arr);
                } catch (Exception e) {
                    isSkip = false;
                    TimeUnit.SECONDS.sleep(1);
                    log.error("CglibRetry.intercept.重试" + (i + 1) + "几次");
                }
                if (isSkip) {
                    break;
                }
            }
            System.out.println("CglibRetryInterceptor动态代理，监听结束！");
            return invoke;
        }
    }

    /**
     * 代理 class (无惨构造)
     */
    public static Object createCglibProxy(Object objectTarget, int retryCount) {
        // 为目标对象target赋值
        Enhancer enhancer = new Enhancer();
        // 设置父类为目标代理对象,
        // 因为Cglib是针对指定的类生成一个子类，所以需要指定父类
        enhancer.setSuperclass(objectTarget.getClass());
        // 创建代理执行对象
        enhancer.setCallback(new CglibProxyRetry.CglibRetryInterceptor(objectTarget, retryCount));
        Object result = enhancer.create();//创建并返回代理对象
        return result;
    }

    public static void main(String[] args) {

        RetryBean.RetryBeanServiceImpl cglibProxy = (RetryBean.RetryBeanServiceImpl) CglibProxyRetry.createCglibProxy(new RetryBean.RetryBeanServiceImpl(), 3);
        cglibProxy.say();
    }
}
