package com.aaa.javabase.pattern.creater.singleton;

import com.aaa.javabase.pattern.creater.singleton.type8.Singleton8;

/**
 * description:
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/10
 */
public class test {
    public static void main(String[] args) {
//        Singleton1 instance = Singleton1.getInstance();
//        Singleton1 instance2 = Singleton1.getInstance();
//        System.out.println(instance == instance2);


        Singleton8 instance = Singleton8.SPRING;
        Singleton8 instance8 = Singleton8.SPRING;
        System.out.println(instance.hashCode());
        System.out.println(instance8.hashCode());
        System.out.println(instance == instance8);



    }
}
