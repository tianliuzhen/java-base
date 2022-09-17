package com.aaa.javabase.base.aqs.condition;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuzhen.tian
 * @version 1.0 TestCondition.java  2022/9/11 10:53
 */
public class PrintByOrder2 {
    public int state = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    @SneakyThrows
    public void print1() {
        lock.lockInterruptibly();
        try {
            while (state != 1) {
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + " :print1...");
            state++;
            // 唤醒第二个条件队列
            condition2.signal();
        } finally {
            lock.unlock();
        }

    }

    @SneakyThrows
    public void print2() {
        System.out.println();
        lock.lockInterruptibly();
        try {
            while (state != 2) {
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + " :print2...");
            state = 1;
            // 唤醒第二个条件队列
            condition1.signal();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        PrintByOrder2 printByOrder = new PrintByOrder2();

        new Thread(() -> {
            while (true) {
                printByOrder.print1();
            }
        }).start();


        new Thread(() -> {
            while (true) {
                printByOrder.print2();
            }
        }).start();


    }
}
