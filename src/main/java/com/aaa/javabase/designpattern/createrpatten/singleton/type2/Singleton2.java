package com.aaa.javabase.designpattern.createrpatten.singleton.type2;

/**
 * description: 饿汉式（静态代码块）[可用]
 *  这种方式和上面的方式其实类似，只不过将类实例化的过程放在了静态代码块中，
 *  也是在类装载的时候，就执行静态代码块中的代码，初始化类的实例。
 *  优缺点和上面singleton1 是一样的。
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/10
 */
public class Singleton2 {
    private static Singleton2 singleton2;
    static {
        singleton2= new Singleton2();
    }
    private  Singleton2(){

    }
    public static Singleton2 getInstance(){
        return singleton2;
    }

}
