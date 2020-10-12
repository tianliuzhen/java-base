package com.aaa.javabase.base.protecteds.demo2.p7;

/**
 * @author liuzhen.tian
 * @version 1.0 MyObject7.java  2020/10/12 15:37
 */
public class MyObject7 extends Test7 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Test7 test7 = new Test7();
        // test7.clone();  // 编译错误
        /**
         * clone()方法来自于类Object，因此该clone()方法可见性为包java.lang及其子类Test7
         * 由于类MyObject7不在此范围内，因此不满足可见性，编译不通过。
         */

        MyObject7 myObject7 = new MyObject7();
        myObject7.clone();
        /**
         * clone()方法来自于类Test7，因此其可见性为包p7及其子类MyObject7，
         * 而类Test7也在包p7中，因此满足可见性，编译通过。
         */

    }
}
