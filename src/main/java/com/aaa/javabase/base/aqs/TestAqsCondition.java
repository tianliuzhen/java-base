package com.aaa.javabase.base.aqs;


import com.google.common.collect.Lists;

import java.util.List;
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
    int putptr, takeptr, count;

     List<Long> list = Lists.newArrayList();

    // 生产
    public  void put(Object x) throws InterruptedException {
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
            notFull.signal(); // 被我消费掉一个，队列 not full 了，发个通知出去
            return x;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {



        TestAqsCondition testAqsCondition = new TestAqsCondition();

        List<Long> list2 = testAqsCondition.list;
        list2 = Lists.newArrayList(1L);


        testAqsCondition.take();
        testAqsCondition.put(1);


    }



}
