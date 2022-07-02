package com.aaa.javabase.multithreading.并发执行.completableFuture.methods;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liuzhen.tian
 * @version 1.0 WithThreadPool.java  2022/7/2 14:29
 */
public class WithThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CompletableFuture<String> future = CompletableFuture.
                supplyAsync(() -> {
                    // 带线程池执行业务
                    return "执行结果:" + (100 * 10);
                }, executorService)
                .thenApply(s -> {
                    // 只有当正常返回时才会去执行 thenApply
                    return "result:" + s;
                })
                .whenComplete((s, throwable) -> {
                    // 执行完成时
                    if (throwable == null) {
                        System.out.println(s);//future result: 1000
                    } else {
                        System.out.println(throwable.getMessage());//未执行
                    }
                });
        // future.join() 也能取返回任务返回值
        System.out.println(future.join());//future result: 1000
    }
}
