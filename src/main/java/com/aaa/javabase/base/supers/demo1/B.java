package com.aaa.javabase.base.supers.demo1;

/**
 * @author liuzhen.tian
 * @version 1.0 B.java  2020/10/10 18:09
 */
public class B extends A {
    public B(String s) {
        super(s);
        System.out.println("子类的有参构造: "+s);
    }

    public B() {
        super(); //可以 默认不写，编译器会自动生成一个  super();
        System.out.println("子类的无参构造");
    }

    public void test(){

    }
}
