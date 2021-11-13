package com.aaa.javabase.base.protecteds.demo1.p2;


import com.aaa.javabase.base.protecteds.demo1.p1.Father1;
import com.aaa.javabase.base.protecteds.demo1.p1.Son1;

/**
 * 子类与基类不在同一包中：那么在子类中，子类实例可以访问其从基类继承而来的 protected 方法，而不能访问基类实例的protected方法。
 *
 * @author liuzhen.tian
 * @version 1.0 Test1.java  2020/10/12 14:36
 */
public class Test2 {
    public static void main(String[] args) {
        Son1 son1 = new Son1();
        // son1.f();  // 编译错误
        son1.getF();

        Son2 son2 = new Son2();
        // son2.f();  // 编译错误

        Father1 father1 = new Father1();
        // father1.f(); // 编译错误

    }
}
