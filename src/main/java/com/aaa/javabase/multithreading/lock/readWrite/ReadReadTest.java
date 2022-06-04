package com.aaa.javabase.multithreading.lock.readWrite;

/**
 * 读读共享
 *
 * @author liuzhen.tian
 * @version 1.0 ReadReadTest.java  2022/6/4 13:09
 */


import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadReadTest {
    public static void main(String[] args) {

        final MyTask2 myTask = new MyTask2();

        Thread t1 = new Thread(() -> myTask.read(), "t1");
        Thread t2 = new Thread(() -> myTask.read(), "t2");

        t1.start();
        t2.start();
    }
}

class MyTask2 {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read() {
        try {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " start");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }
}

