package com.aaa.javabase.multithreading.classlock;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/4/25
 */
public class ClassLockMain {
    public static void main(String[] args) {


        /**
         * ClassLockThread 这里是 类锁
         * 虽然是创建了两个实力对象 classLockThread1、classLockThread2，进行测试，但是只有一个锁
         * 所以下面三个  thread1 thread2  thread3 都会被阻塞一个一个执行
         */

        // 测试 MyThread 对象锁
        ClassLockThread classLockThread1 = new ClassLockThread();
        Thread thread1=new Thread(classLockThread1,"线程1");
        Thread thread2=   new Thread(classLockThread1,"线程2");


        // 测试 MyThread 类锁
        ClassLockThread classLockThread2 = new ClassLockThread();
        Thread thread3=   new Thread(classLockThread2,"线程3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
