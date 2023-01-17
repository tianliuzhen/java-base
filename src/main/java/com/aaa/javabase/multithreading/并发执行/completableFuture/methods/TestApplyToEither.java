package com.aaa.javabase.multithreading.并发执行.completableFuture.methods;

import java.util.concurrent.CompletableFuture;

/**
 * 哪个执行快，先返回哪个
 * <p>
 * public <U> CompletableFuture<U> applyToEither(CompletionStage<? extends T> other, Function<? super T, U> fn);
 * public CompletableFuture<Void> acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action);
 * public CompletableFuture<Void> runAfterEither(CompletionStage<?> other,Runnable action);
 * public static CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs);
 * <p>
 * CompletableFuture：任务同时执行，且只取最先完成的那个任务
 *
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
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "通过方式B获取商品a";
        });
        // applyToEither
        CompletableFuture<String> futureC = futureA.applyToEither(futureB, product -> "结果A:" + product);
        System.out.println(futureC.join());

        // acceptEither
        CompletableFuture<Void> futureD = futureA.acceptEither(futureB, product -> {
            String str = "结果B:" + product;
            System.out.println(str);
        });

        // runAfterEither
        CompletableFuture<Void> futureE = futureA.runAfterEither(futureB, () -> {
            String str = "结果C:";
            System.out.println(str);
        });

        // anyOf
        CompletableFuture<Object> futureF = CompletableFuture.anyOf(futureA, futureB);
        System.out.println(futureF.join());
    }
}
