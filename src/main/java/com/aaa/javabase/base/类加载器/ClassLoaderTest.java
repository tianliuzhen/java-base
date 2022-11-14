package com.aaa.javabase.base.类加载器;

import java.util.HashMap;

/**
 * @author liuzhen.tian
 * @version 1.0 ClassLoaderTest.java  2022/7/9 21:40
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        /**
         * 默认类加载器
         * sun.misc.Launcher$AppClassLoader
         * sun.misc.Launcher$ExtClassLoader
         *
         */
        System.out.println("ClassLoaderTest.class.getClassLoader() = " + ClassLoaderTest.class.getClassLoader());
        // System.out.println("DoubleConstant.class.getClassLoader() = " + DoubleConstant.class.getClassLoader());
        //  BootStrap ClassLoader,在jvm中通常使用c/c++ 语言原生实现，不能体现为java类,因此打印出来是 null
        System.out.println("HashMap.class.getClassLoader() = " + HashMap.class.getClassLoader());

        System.out.println("System.getProperty(\"java.class.path\") = " + System.getProperty("java.class.path"));
        System.out.println("System.getProperty(\"java.ext.dirs\") = " + System.getProperty("java.ext.dirs"));
    }
}
