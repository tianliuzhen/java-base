package com.aaa.javabase.multithreading.classlock;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/4/25
 */
public class Main2 {
    public static void main(String[] args) {
        //测试对象锁
        MyThread2 myThread1 = new MyThread2();
        Thread thread = new Thread(myThread1);
        Thread thread2 = new Thread(myThread1);
        thread.start();
        thread2.start();

        //测试类锁
        MyThread2 myThread2 = new MyThread2();
        Thread thread3 = new Thread(myThread2);
        thread3.start();
    }
}
