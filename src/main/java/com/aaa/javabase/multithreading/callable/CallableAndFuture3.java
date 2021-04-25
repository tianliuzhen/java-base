package com.aaa.javabase.multithreading.callable;

import java.util.concurrent.*;

/**
 * 使用Callable+Future获取执行结果
 *
 * @author liuzhen.tian
 * @version 1.0 CallableAndFuture3.java  2021/4/25 23:09
 */
public class CallableAndFuture3 {
        public static void main(String[] args) {
            ExecutorService executor = Executors.newCachedThreadPool();
            Task3 task3 = new Task3();
            Future<Integer> result = executor.submit(task3);
            executor.shutdown();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            System.out.println("主线程在执行任务");

            try {
                System.out.println("task运行结果"+result.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            System.out.println("所有任务执行完毕");
        }
    }
    class  Task3 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("子线程在进行计算");
            Thread.sleep(3000);
            int sum = 0;
            for(int i=0;i<100;i++)
                sum += i;
            return sum;
        }
}
