package com.aaa.javabase.base.线程交替打印;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuzhen.tian
 * @version 1.0 PrintOddEven.java  2025/3/27 21:28
 */
public class PrintOddEvenV3 {
    private static final Object object = new Object();

    private static volatile int count = 1;
    private static boolean  isOddTurn = true; // 标记当前是否是奇数线程的回合


    public static void main(String[] args) {
        // 创建信号量：奇数初始可获取，偶数初始不可获取
        Lock lock = new ReentrantLock();
        Condition oddCondition = lock.newCondition();
        Condition evenCondition = lock.newCondition();

        AtomicInteger counter = new AtomicInteger(1);


        Thread oddThread = new Thread(() -> {
            try {
                while (true) {
                    lock.lock();
                    try {
                        // 如果不是奇数线程的回合，则等待
                        while (!isOddTurn) {
                            oddCondition.await();
                        }
                        int value = counter.getAndIncrement();
                        if (value > 100) break; // 超过 100 停止打印
                        System.out.println("奇数: " + value);
                        isOddTurn = false; // 切换到偶数线程
                        evenCondition.signal(); // 唤醒偶数线程
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "奇数");
        oddThread.start();

        Thread even = new Thread(() -> {
            try {
                while (true) {
                    lock.lock();
                    try {
                        // 如果不是偶数线程的回合，则等待
                        while (isOddTurn) {
                            evenCondition.await();
                        }
                        int value = counter.getAndIncrement();
                        if (value > 100) break; // 超过 100 停止打印
                        System.out.println("偶数: " + value);
                        isOddTurn = true; // 切换到奇数线程
                        oddCondition.signal(); // 唤醒奇数线程
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }, "偶数");
        even.start();


    }

}
