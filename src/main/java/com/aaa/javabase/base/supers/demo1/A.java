package com.aaa.javabase.base.supers.demo1;

/**
 * @author liuzhen.tian
 * @version 1.0 A.java  2020/10/10 18:09
 */
public class A {
    String s = "";
    public A(String s){
        System.out.println("父类的有参构造: "+s);
    }

    public A() {
        System.out.println("父类的无参构造");
    }
}
