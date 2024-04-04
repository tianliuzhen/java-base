package com.aaa.javabase.base.构造;

/**
 * @author liuzhen.tian
 * @version 1.0 Son.java  2024/3/28 22:34
 */
public class Son extends Father {
    private int age=18;

    Son() {
        // age = 18;
        print();
    }

    public void print() {
        System.out.println("Son.print.age:" + age);
    }
}
