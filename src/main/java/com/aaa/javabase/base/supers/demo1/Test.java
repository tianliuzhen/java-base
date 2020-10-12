package com.aaa.javabase.base.supers.demo1;

/**
 * @author liuzhen.tian
 * @version 1.0 Test.java  2020/10/10 18:16
 */
public class Test {
    public static void main(String[] args) {
        B b = new B("tom");
        System.out.println(b);

        // 调用子类构造函数的时候，会默认先调用父类的无参数构造方法，即相当于隐藏了一行代码 super();
        /**
         * 在你程序没有写构造方法时候，编译器会默认帮你写一个无参数的构造方法，只不过方法里面没有内容
         * 然而当你编写了构造函数的话，编译器就不会再帮你添加无参数的构造方法了，即使你只编写了有参数的构造方法。
         * 如果你已经写了一个有参数的构造函数，并且你需要一个没有参数的构造函数，你必须自己动手写。
         */
        B b1 = new B();
        System.out.println(b1);
    }

}
