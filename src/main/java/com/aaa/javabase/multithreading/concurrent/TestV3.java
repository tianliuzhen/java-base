package com.aaa.javabase.multithreading.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * 同一个实例多线程 测试 CountDownLatch
 * @author liuzhen.tian
 * @version 1.0 TestV4.java  2020/10/19 21:06
 */
public class TestV3 {

    public static void main(String[] args) {

        Long startTime=System.currentTimeMillis();
        CountDownLatch countDownLatch=new CountDownLatch(5);
        LatchThread latchThread=new LatchThread(countDownLatch);

        for(int j=1;j<=5;j++){
            new Thread(latchThread, "thread" + j).start();
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


    static class  LatchThread implements  Runnable{

        private CountDownLatch latch;
        public LatchThread(CountDownLatch countDownLatch) {
            latch=countDownLatch;
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
}


