package com.aaa.javabase.multithreading.concurrent;

/**
 * jdk提供的一个同步辅助类,在完成一组在在其他线程中执行的操作前，允许一个或者多个其他的线程等待，
 * 通过调用 await() 方法阻塞，直到由于 countDown() 方法的调用而导致当前计数达到零，之后所有等待线程被释放。
 */

import java.util.concurrent.CountDownLatch;
public class TestV3 {

    public static void main(String[] args) {

        Long startTime=System.currentTimeMillis();
        CountDownLatch countDownLatch=new CountDownLatch(5);
        LatchThread latchThread=new LatchThread(countDownLatch);

        for(int j=1;j<=5;j++){
            new Thread(latchThread,"thread"+j).start();
        }

        try {
            countDownLatch.await();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long endTime=System.currentTimeMillis();

        System.out.println("over 耗时："+(endTime-startTime));
    }

}

 class  LatchThread implements  Runnable{

    private CountDownLatch latch;
    public LatchThread(CountDownLatch l) {
        latch=l;
    }

    @Override
    public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + "----" + i);
                    }
                }
            }
            finally {
                latch.countDown();
            }
    }
}
