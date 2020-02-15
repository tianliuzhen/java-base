package com.aaa.javabase.designpattern.createrpatten.singleton.type3;

/**
 * description:  懒汉式(线程不安全)[不可用]
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/10
 */
public class Singleton3 {
    private Singleton3(){};
    private static Singleton3 singleton3;
    public static Singleton3 getInstance(){
        if (singleton3 == null) {
            singleton3 = new Singleton3();
        }
        return singleton3;
    }
}

