package com.aaa.javabase.multithreading.lock.readWrite;

/**
 * 写写互斥
 *
 * @author liuzhen.tian
 * @version 1.0 WriteWriteTest.java  2022/6/4 13:08
 */

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WriteWriteTest {
    public static void main(String[] args) {

        final MyTask1 myTask = new MyTask1();

        Thread t1 = new Thread(() -> myTask.read(), "t1");
        Thread t2 = new Thread(() -> myTask.read(), "t2");

        t1.start();
        t2.start();
    }
}

class MyTask1 {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read() {
        try {
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + " start");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }
}
