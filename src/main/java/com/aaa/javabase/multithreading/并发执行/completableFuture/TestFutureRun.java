package com.aaa.javabase.multithreading.并发执行.completableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author liuzhen.tian
 * @version 1.0 Test.java  2021/3/1 22:41
 */
public class TestFutureRun {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(() -> 1);
        futureTask.run();
        System.out.println(futureTask.get());
    }
}
