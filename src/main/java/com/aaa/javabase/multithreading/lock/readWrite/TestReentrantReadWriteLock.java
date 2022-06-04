package com.aaa.javabase.multithreading.lock.readWrite;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写互斥之饥饿写测试
 *
 * @author liuzhen.tian
 * @version 1.0 WriteHungerTest.java  2022/6/3 23:18
 */
public class WriteHungerTest {

    //读写锁
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(false);

    //写锁
    private final static Lock writeLock = readWriteLock.writeLock();

    //读锁
    private final static Lock readLock = readWriteLock.readLock();

    private final static List<Long> longs = new ArrayList<>();

    /**
     * 饥饿锁测试，如果读锁的线程比较多，写锁的线程，可能就会很久才能抢到线程。
     *
     * @param args
     * @throws InterruptedException
     */
    public final static void main(String[] args) throws InterruptedException {
        // 模拟写线程的线程为1个
        new Thread(WriteHungerTest::write).start();
        TimeUnit.SECONDS.sleep(1);

        // 模拟读线程的线程为500个
        for (int i = 0; i < 500; i++) {
            new Thread(WriteHungerTest::read).start();
        }
    }

    public static void write() {
        try {
            writeLock.lock();
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName() + " write ");
            longs.add(System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " writeLock.unlock ");
            writeLock.unlock();
        }
    }

    public static void read() {
        try {
            System.err.println(Thread.currentThread().getName() + " readLock.lock()");
            readLock.lock();
            TimeUnit.SECONDS.sleep(1);
            longs.forEach(x -> System.out.println(x));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
            System.err.println(Thread.currentThread().getName() + " readLock.unlock()");

        }
    }
}
