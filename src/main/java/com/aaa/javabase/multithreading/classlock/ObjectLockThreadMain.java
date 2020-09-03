package com.aaa.javabase.multithreading.classlock;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/4/25
 */
public class ObjectLockThreadMain {
    public static void main(String[] args) {

        /**
         * thread1 和  thread2 这里是针对同一个对象  myThread1
         * 所以，线程1和线程2 会有一个被阻塞，线程3 不会被阻塞
         *
         * 所以结果有两种可能 ,线程2和3 或者 线程1和3 同时执行，两秒后再执行线程1或2
         *    1、   线程2    2、 线程1
         *         线程3        线程3
         *
         *         线程1        线程2
         *
         */

        //测试对象锁
        ObjectLockThread myThread1 = new ObjectLockThread();
        Thread thread1 = new Thread(myThread1,"线程1");
        Thread thread2 = new Thread(myThread1,"线程2");
        thread1.start();
        thread2.start();

        //测试类锁
        ObjectLockThread myThread2 = new ObjectLockThread();
        Thread thread3 = new Thread(myThread2,"线程3");
        thread3.start();
    }
}
