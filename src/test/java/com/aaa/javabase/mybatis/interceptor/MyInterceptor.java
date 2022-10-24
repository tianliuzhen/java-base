package com.aaa.javabase.mybatis.interceptor;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

/**
 * @author liuzhen.tian
 * @version 1.0 TestInterceptor.java  2022/10/24 18:25
 */

@Intercepts(value = {@Signature(type = MyPlugin.class,method = "sayHello",args = {String.class})})
public class MyInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.err.println("proceed before... ");
        Object proceed = invocation.proceed();
        System.err.println("proceed after... ");
        return proceed;
    }
}
