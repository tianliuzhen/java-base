package com.aaa.javabase.base.protecteds.demo2.p22;

import com.aaa.javabase.base.protecteds.demo2.p2.MyObject2;

/**
 * @author liuzhen.tian
 * @version 1.0 Test2.java  2020/10/12 15:21
 */
public class Test2 {
    public static void main(String[] args) throws CloneNotSupportedException {
        MyObject2 myObject2 = new MyObject2();
        myObject2.clone();
        /**
         * clone()方法来自于类Test2，因此其可见性为包p22及其子类MyObject2，而（1）正是在p33的类Test3中调用，属于同一包，编译通过。
         */

    }
}
