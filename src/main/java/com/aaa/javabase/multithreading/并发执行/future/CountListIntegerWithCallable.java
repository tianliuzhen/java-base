package com.aaa.javabase.multithreading.并发执行.future;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Callable  实现list 求和
 * @author liuzhen.tian
 * @version 1.0 CountListIntegerWithCallable.java  2021/2/22 19:58
 */
public class CountListIntegerWithCallable {
    public static void main(String[] args) {
        int threadNum = 10;

        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        List<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i <100; ++i) {
            list.add(i);
        }
        int len = list.size() / threadNum;
        if (0 == len) {
            threadNum = list.size();
            len = 1;
        }

        List<Callable<Long>> callableList = new LinkedList<Callable<Long>>();

        for (int i = 0; i < threadNum; ++i) {
            List<Integer> subList = new LinkedList<Integer>();
            if (i == threadNum - 1) {//最后一个线程处理剩下所有任务
                subList = list.subList(i * len, list.size());
            } else {
                subList = list.subList(i * len, (i + 1) * len);
            }
            final List<Integer> finalSubList = subList;
            callableList.add(new Callable<Long>() {
                public Long call() throws Exception {
                    long sum = 0;
                    for (int j = 0; j < finalSubList.size(); j++) {
                        sum += finalSubList.get(j);
                    }
                    System.out.println("分配给线程："+Thread.currentThread().getName()+" list元素部分计数和为："+sum);
                    return sum;
                }
            });
        }

        try {
//            这个不用自己控制等待，invokeAll执行给定的任务，当所有任务完成时，返回保持任务状态和结果的 Future 列表
            List<Future<Long>> futureList = executorService.invokeAll(callableList);
            for (Future<Long> future : futureList) {
                System.out.println(future.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
