package com.aaa.javabase.base.构造;

/**
 * @author liuzhen.tian
 * @version 1.0 Father.java  2024/3/28 22:32
 */
    public class Father {
        private int age;

        Father() {
            age = 40;
            print2();
        }

        public void print2() {
            System.out.println("Father.print.age:" + age);
        }
    }
