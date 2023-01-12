package com.aaa.javabase.base;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuzhen.tian
 * @version 1.0 TestLock.java  2023/1/2 13:23
 */
public class TestLock {
    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static void count() {

        if (reentrantLock.tryLock()) {
            System.out.println(Thread.currentThread().getName() + ":" + "begin:" + System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // reentrantLock.unlock();
        } else {
            System.err.println(Thread.currentThread().getName() + ":" + "枪锁失败:" + System.currentTimeMillis());
        }


    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(TestLock::count).start();
        }

    }
}
