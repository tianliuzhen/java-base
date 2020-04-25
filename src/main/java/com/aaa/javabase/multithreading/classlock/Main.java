package com.aaa.javabase.multithreading.classlock;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/4/25
 */
public class Main {
    public static void main(String[] args) {
        // 测试 MyThread 对象锁
        MyThread myThread = new MyThread();
        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                myThread.method1();
            }
        },"线程1");
        Thread thread2=   new Thread(new Runnable() {
            @Override
            public void run() {
                myThread.method1();
            }
        },"线程2");
        // 测试 MyThread 类锁
        MyThread myThread2 = new MyThread();
        Thread thread3=   new Thread(new Runnable() {
            @Override
            public void run() {
                myThread2.method1();
            }
        },"线程3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
