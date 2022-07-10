package com.aaa.javabase.base.类加载器;

/**
 * @author liuzhen.tian
 * @version 1.0 ClassNamespaceTest.java  2022/7/10 9:53
 */
public class ClassNamespaceTest {
    private final static String COMMON_PATH = "F:/WorkSpace/ClassLoaderPath/";

    public static void main(String[] args) throws Exception {
        MyClassLoader l1 = new MyClassLoader(COMMON_PATH);
        MyClassLoader l2 = new MyClassLoader(COMMON_PATH);

        String className = "com.aaa.javabase.config.Device2";
        Class<?> c1 = Class.forName(className, false, l1);
        Class<?> c2 = Class.forName(className, false, l2);
        Object o1 = c1.newInstance();

        System.out.println("c1的classLoader是" + c1.getClassLoader());
        System.out.println("c2的classLoader是" + c2.getClassLoader());

        System.out.println("c1类型是否等于c2类型？" + c1.equals(c2));
        System.out.println("对象o1是否是c2类型？" + (c2.isInstance(o1)));
        /**
         * c1类型是否等于c2类型？true
         * 对象o1是否是c2类型？true
         */
    }
}
