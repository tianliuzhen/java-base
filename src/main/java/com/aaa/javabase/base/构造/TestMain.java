package com.aaa.javabase.base.构造;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2024/3/28 22:35
 */
public class TestMain {
    public static void main(String[] args) {
        Son son = new Son();
        son.print();
        /**
         * 输出：
         * Son.print.age:0
         * Father.print.age:40
         * Son.print.age:18
         *
         * 结论：
         * 子类的显示赋值 Son类 private int age=18;
         * 是在Son的无参构造之后执行，而在执行之前会先执行其父类的无参构造
         *
         * 为什么：
         *  son的字节码
         */
    }
}
