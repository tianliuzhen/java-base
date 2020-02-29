package com.aaa.javabase.multithreading.callable;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/29
 */
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableThreadTest implements Callable<Integer> {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableThreadTest ctt = new CallableThreadTest();
        FutureTask<Integer> ft = new FutureTask<>(ctt);
        new Thread(ft, "有返回值的线程").start();
        System.out.println("子线程的返回值" + ft.get());
    }

    @Override
    public Integer call() {
        int i;
        for (i = 0; i < 10; i += 2) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        return i;
    }
}
