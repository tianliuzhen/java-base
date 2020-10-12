package com.aaa.javabase.base.protecteds.demo1.p1;


import com.aaa.javabase.base.protecteds.demo1.p2.Son2;

/**
 * @author liuzhen.tian
 * @version 1.0 Test1.java  2020/10/12 14:36
 */
public class Test1 {
    public static void main(String[] args) {
        Son1 son1 = new Son1();
        son1.f();
        Son2 son2 = new Son2();
        son2.f();
        Father1 father1 = new Father1();
        father1.f();
    }
}
