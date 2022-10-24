package com.aaa.javabase.mybatis.interceptor;

import org.apache.ibatis.plugin.Plugin;

/**
 * @author liuzhen.tian
 * @version 1.0 Test.java  2022/10/24 18:30
 */
public class Test {
    public static void main(String[] args) {
        // wrap 方法是对 jdk 动态代理的封装
        MyPlugin wrap = (MyPlugin)Plugin.wrap(new MyPlugin() {
            @Override
            public void sayHello(String str) {
                System.out.println(str);
            }
        }, new MyInterceptor());

        wrap.sayHello("hello word");
    }
}
