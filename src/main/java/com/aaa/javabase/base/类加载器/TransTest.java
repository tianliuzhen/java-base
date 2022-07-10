package com.aaa.javabase.base.类加载器;

import com.aaa.javabase.base.类加载器.impl.UserServiceImpl;

/**
 * @author liuzhen.tian
 * @version 1.0 TransTest.java  2022/7/10 10:09
 */
public class TransTest {
    public static void main(String[] args) throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader("F:\\WorkSpace\\ClassLoaderPath");
        Class<?> serviceClass = Class.forName(
                "com.aaa.javabase.base.类加载器.impl.UserServiceImpl",
                false,
                myClassLoader);
        UserServiceImpl service = (UserServiceImpl) serviceClass.newInstance();
        service.getUser("");


    }
}
