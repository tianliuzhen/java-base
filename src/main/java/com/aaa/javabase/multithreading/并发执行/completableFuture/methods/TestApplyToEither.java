package com.aaa.javabase.multithreading.并发执行.completableFuture.methods;

import java.util.concurrent.CompletableFuture;

/**
 * @author liuzhen.tian
 * @version 1.0 TestApplyToEither.java  2021/9/23 22:17
 */
public class TestApplyToEither {
    public static void main(String[] args) {
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "通过方式A获取商品a";
        });
        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "通过方式B获取商品a";
        });
        CompletableFuture<String> futureC = futureA.applyToEither(futureB, product -> "结果:" + product);
        System.out.println(futureC.join()); //结果:通过方式A获取商品a
    }
}
