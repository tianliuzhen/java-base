package com.aaa.javabase.base.aqs;


import java.util.UUID;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuzhen.tian
 * @version 1.0 TestAqsCondition.java  2022/9/10 21:30
 */
public class TestAqsCondition {
    final Lock lock = new ReentrantLock();
    // condition 依赖于 lock 来产生
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[100];
    // 临时变量用于记录 put
    int putptr;
    // 临时变量用于记录 take
    int takeptr;
    int count;


    // 生产
    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();  // 队列已满，等待，直到 not full 才能继续生产
            }
            items[putptr] = x;
            if (++putptr == items.length) {
                putptr = 0;
            }
            ++count;
            System.out.println(Thread.currentThread().getName() + "put: " + count);
            notEmpty.signal(); // 生产成功，队列已经 not empty 了，发个通知出去
        } finally {
            lock.unlock();
        }
    }

    // 消费
    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await(); // 队列为空，等待，直到队列 not empty，才能继续消费
            }
            Object x = items[takeptr];
            if (++takeptr == items.length) {
                takeptr = 0;
            }
            --count;
            System.out.println(Thread.currentThread().getName() + "take: " + count);
            notFull.signalAll(); // 被我消费掉一个，队列 not full 了，发个通知出去
            return x;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {


        TestAqsCondition testAqsCondition = new TestAqsCondition();

        // 模拟5个生产者
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        testAqsCondition.put(UUID.randomUUID());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }


        //  模拟1个消费者
        new Thread(() -> {
            while (true) {
                try {
                    testAqsCondition.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
