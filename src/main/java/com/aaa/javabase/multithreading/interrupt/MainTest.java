package com.aaa.javabase.multithreading.interrupt;

/**
 * 这里没有别的线程只是 main线程
 * @author liuzhen.tian
 * @version 1.0 Do.java  2020/9/3 10:57
 */
public class MainTest {
    public static void main(String[] args) {
        // 1. interrupt() 这里中断的是 main 线程，实际上只是给线程设置一个中断标志，线程仍会继续运行。
        Thread.currentThread().interrupt();
        System.out.println("执行线程中断操作，只是设置中断标志");

        // 2. isInterrupted() 作用是只测试此线程是否被中断 ，不清除中断状态。
        System.out.println("第一次调用  thread.interrupt()：" +Thread.currentThread().isInterrupted() + "  线程名：" + Thread.currentThread().getName());
        System.out.println("第二次调用  thread.interrupt()：" +Thread.currentThread().isInterrupted() + "  线程名：" + Thread.currentThread().getName());

        // 3. interrupted() 作用是测试当前线程是否被中断（检查中断标志），
        // 返回一个boolean并清除中断状态，第二次再调用时中断状态已经被清除，将返回一个false。
        System.out.println("第一次调用  thread.interrupt()：" +Thread.interrupted() + "  线程名：" + Thread.currentThread().getName());
        System.out.println("第二次调用  thread.interrupt()：" + Thread.interrupted() + "  线程名：" + Thread.currentThread().getName());
    }
}
