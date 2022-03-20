package com.aaa.javabase.multithreading.并发执行.AQS.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 解释：
 * 字面意思是循环可循环（cylic）使用的屏障（barrier）。
 * 他要做的事情就是让一组线程到达一个屏障时被阻塞，直到最后一个线程到达屏障时，屏障才会开门。
 *
 *
 * @author liuzhen.tian
 * @version 1.0 TestCyclicBarrier.java  2022/3/20 17:24
 */
public class TestCyclicBarrier {
    public static void main(String[] args) {
        // 表示设置 循环栅栏的阈值为5，等于5的时候，会触发一个阈值事件。
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> System.out.println("满人发车"));

        // 这里设置10个线程，则会 5个线程一批。分两批去执行。
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        /**
         * 控制台输出：
         * 满人发车
         * 满人发车
         *
         */
    }
}
