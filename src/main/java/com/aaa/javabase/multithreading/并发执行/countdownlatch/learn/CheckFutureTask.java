package com.aaa.javabase.multithreading.并发执行.countdownlatch.learn;


import com.aaa.javabase.multithreading.并发执行.countdownlatch.learn.thread.BaseCheckThread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;

public class CheckFutureTask extends FutureTask<Boolean> {

    private volatile CountDownLatch latch;

    private final int number;

    public CheckFutureTask(BaseCheckThread checkThread, CountDownLatch latch, int number) {
        super(checkThread);
        this.latch = latch;
        this.number = number;
    }

    @Override
    protected void done() {
        try {
            if (!get()) {
                // 有一个失败，取消所有的线程
                afterFail();
            }
        } catch (Exception e) {
            afterFail();
        } finally {
            latch.countDown();
        }
    }

    /**
     * 在失败后调用，取消所有
     */
    private void afterFail() {
        for (int i = 0; i < number - 1; i++) {
            latch.countDown();
        }
    }

    public static void main(String[] args) {
        // 线程1：
        Object obj = new Object();  // (1) 分配内存 → (2) 初始化 → (3) 赋值（可能被重排序）
        boolean initialized = true;  // (4) volatile 写

        // 线程2：
        while (!initialized) {
            ; // 等待
        }
        obj.toString();   // 可能看到未初始化的 obj！
    }
}
