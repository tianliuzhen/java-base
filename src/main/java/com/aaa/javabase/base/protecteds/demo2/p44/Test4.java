package com.aaa.javabase.base.protecteds.demo2.p44;


import com.aaa.javabase.base.protecteds.demo2.p4.MyObject4;

/**
 * @author liuzhen.tian
 * @version 1.0 Test4.java  2020/10/12 15:25
 */
public class Test4 {
    public static void main(String[] args) throws CloneNotSupportedException {
        MyObject4 myObject4 = new MyObject4();
        // myObject4.clone(); //编译错误
        /**
         * clone()方法来自于类MyObject4，因此其可见性为包p4及其子类(此处没有子类)，而类Test4却在包p44中，因此不满足可见性，编译不通过。
         */

    }
}
