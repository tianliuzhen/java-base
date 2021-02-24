package com.aaa.javabase.multithreading.并发执行.countdownlatch.learn;


import com.aaa.javabase.multithreading.并发执行.countdownlatch.learn.thread.BaseCheckThread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;

public class CheckFutureTask extends FutureTask<Boolean>{

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
            if(!get()){
                //有一个失败，取消所有的线程
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
    private void afterFail(){
        for(int i = 0 ; i < number - 1 ; i++){
            latch.countDown();
        }
    }
}
