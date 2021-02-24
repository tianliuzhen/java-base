package com.aaa.javabase.multithreading.并发执行.cyclicBarrier;

/**
 * @author liuzhen.tian
 * @version 1.0 CyclicBarrierExc.java  2021/2/22 20:09
 */

import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shuliangzhao
 * @Title: CyclicBarrier
 * @ProjectName design-parent
 * @Description: TODO
 * @date 2019/6/3 0:18
 */
public class CyclicBarrierExc {

    public static void main(String[] args) {
        //数组大小
        int size = 50000;
        //定义数组
        int[] numbers = new int[size];

        //随机初始化数组
        for (int i = 0; i < size; i++) {
            numbers[i] = RandomUtils.nextInt(100, 1000);
        }

        //多线程计算结果
        //定义线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //定义五个Future去保存子数组计算结果
        final int[] results = new int[5];

        //定义一个循环屏障，在屏障线程中进行计算结果合并
        CyclicBarrier barrier = new CyclicBarrier(5, () -> {
            int sums = 0;
            for (int i = 0; i < 5; i++) {
                sums += results[i];
            }
            System.out.println("多线程计算结果：" + sums);
        });

        //子数组长度
        int length = 10000;
        //定义五个线程去计算
        for (int i = 0; i < 5; i++) {
            //定义子数组
            int[] subNumbers = Arrays.copyOfRange(numbers, (i * length), ((i + 1) * length));
            //盛放计算结果
            int finalI = i;
            executorService.submit(() -> {
                for (int j = 0; j < subNumbers.length; j++) {
                    results[finalI] += subNumbers[j];
                }
                //等待其他线程进行计算
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

        //关闭线程池
        executorService.shutdown();
    }
}
