package com.aaa.javabase.test;

/**
 * @author liuzhen.tian
 * @version 1.0 MyThread.java  2022/2/13 14:17
 */
public class MyThread {
    private final Integer obj = new Integer(1);

    public void method1() {
        synchronized (obj) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // 测试 MyThread 对象锁
        MyThread myThread = new MyThread();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                myThread.method1();
            }
        }, "线程1");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                myThread.method1();
            }
        }, "线程2");
// 测试 MyThread 类锁
        MyThread myThread2 = new MyThread();
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                myThread2.method1();
            }
        }, "线程3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
