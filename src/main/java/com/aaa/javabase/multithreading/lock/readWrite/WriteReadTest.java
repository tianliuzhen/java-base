package com.aaa.javabase.multithreading.lock.readWrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写互斥
 *
 * @author liuzhen.tian
 * @version 1.0 WriteReadTest.java  2022/6/4 13:06
 */
public class WriteReadTest {
    public static void main(String[] args) {
        final MyTask myTask = new MyTask();
        Thread t1 = new Thread(() -> myTask.read(), "t1");
        Thread t2 = new Thread(() -> myTask.write(), "t2");
        t2.start();
        t1.start();
    }
}

class MyTask {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public void read() {
        try {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " start");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void write() {
        try {
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + " start");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }
}

