package com.aaa.javabase.multithreading.interrupt;

/**
 * @author liuzhen.tian
 * @version 1.0 Main.java  2020/9/3 10:39
 */
public class InterruptTest {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.setName("myThread");
        myThread.start();

        //1. 这里中断的是  myThread 这个线程给 myThread 设置中断标识
        myThread.interrupt();
        System.out.println("第一次调用  myThread.interrupt()：" +myThread.isInterrupted()+" 线程名："+myThread.getName());
        System.out.println("第二次调用  myThread.interrupt()：" +myThread.isInterrupted()+" 线程名："+myThread.getName());
        //    测试 interrupted() 方法
        System.out.println("第一次调用  myThread.interrupted()：" +myThread.interrupted()+" 线程名："+myThread.getName());
        System.out.println("第二次调用  myThread.interrupted()：" +myThread.interrupted()+" 线程名："+myThread.getName());

        /**
         * 第一次调用  myThread.interrupt()：true 线程名：myThread
         * 第二次调用  myThread.interrupt()：true 线程名：myThread
         * 第一次调用  myThread.interrupted()：false 线程名：myThread
         * 第二次调用  myThread.interrupted()：false 线程名：myThread
         * i = 0
         * i = 1
         * i = 2
         *
         * 从输出结果看，可能会有疑惑，为什么后面两个interrupted方法输出的都是false，
         * 而不是预料中的一个true一个false？
         * 注意！！！这是一个坑！！！上面说到，interrupted（）方法测试的是当前线程是否被中断，当前线程！！！当前线程！！！
         * 这里当前线程是main线程，而thread.interrupt(）中断的是thread线程，这里的此线程就是thread线程。
         * 所以当前线程main从未被中断过，尽管interrupted（）方法是以thread.interrupted（）的形式被调用，
         * 但它检测的仍然是main线程而不是检测thread线程，
         * 所以thread.interrupted（）在这里相当于main.interrupted（）。对于这点，下面我们参考  MainTest  进行测试。
         */
    }
}
