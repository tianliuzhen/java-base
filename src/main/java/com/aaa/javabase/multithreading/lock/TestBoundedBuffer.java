package com.aaa.javabase.multithreading.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuzhen.tian
 * @version 1.0 TestBoundedBuffer.java  2022/6/4 23:01
 */
public class TestBoundedBuffer {

    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[100];

    //依次为，放入的角标、拿的角标、数组中放入的对象总数
    int putptr, takeptr, count;

    /**
     * 添加一个元素
     * （1）如果当前数组已满，则把当前"放入"线程，加入到"放入"等待队列中，并阻塞当前线程
     * （2）如果当前数组未满，则将x元素放入数组中，唤醒"拿"线程中的等待线程。
     */
    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            // 如果已满，则阻塞当前"放入"线程
            while (count == items.length) {
                notFull.await();
            }
            items[putptr] = x;
            if (++putptr == items.length) {
                putptr = 0;
            }
            ++count;
            // 唤醒"拿"线程
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 拿一个元素
     * （1）如果当前数组已空，则把当前"拿"线程，加入到"拿"等待队列中，并阻塞当前线程
     * （2）如果当前数组不为空，则把唤醒"放入"等待队列中的线程。
     */
    public Object take() throws InterruptedException {
        lock.lock();
        try {
            // 如果为空，则阻塞当前"拿"线程
            while (count == 0) {
                notEmpty.await();
            }
            Object x = items[takeptr];
            if (++takeptr == items.length) {
                takeptr = 0;
            }
            --count;
            // 唤醒"放入"线程
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}
