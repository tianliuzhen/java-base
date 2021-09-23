package com.aaa.javabase.multithreading.并发执行.completableFuture.methods;

import java.util.concurrent.CompletableFuture;

/**
 * @author liuzhen.tian
 * @version 1.0 TestExceptionally.java  2021/9/23 22:26
 */
public class TestExceptionally {
    public static void main(String[] args) {
        CompletableFuture<String> futureA = CompletableFuture.
                supplyAsync(() -> "执行结果:" + (100 / 0))
                .thenApply(s -> "futureA result:" + s)
                .exceptionally(e -> {
                    System.out.println(e.getMessage()); //java.lang.ArithmeticException: / by zero
                    return "futureA result: 100";
                });
        CompletableFuture<String> futureB = CompletableFuture.
                supplyAsync(() -> "执行结果:" + 50)
                .thenApply(s -> "futureB result:" + s)
                .exceptionally(e -> "futureB result: 100");
        System.out.println(futureA.join());//futureA result: 100
        System.out.println(futureB.join());//futureB result:执行结果:50
    }
}
