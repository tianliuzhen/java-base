package com.aaa.javabase.multithreading.并发执行.completableFuture.methods;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 TestThen.java  2021/9/23 21:35
 */
public class TestThenRun {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("执行任务A");
            return "null";
        }).thenRun(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行任务B");
        });
    }
}
