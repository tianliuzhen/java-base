package com.aaa.javabase.multithreading.volatiles;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/27
 */
public class Test1 {
    public static void main(String[] args) {
        MyThread1 thread1 = new MyThread1();
        thread1.start();
        MyThread2 thread2 = new MyThread2();
        thread2.start();
    }
}
class Test {
    static int i = 0, j = 0;
   synchronized static void one() { i++;j++; }
   synchronized static void two() {
        System.out.println("i=" + i + " j=" + j);
    }
}
class MyThread1 extends Thread {
    @Override
    public void run() {
        while (true) {
            Test.one();
        }
    }
}
class MyThread2 extends Thread{
    @Override
    public void run() {
        while (true) {
            Test.two();
        }
    }
}
