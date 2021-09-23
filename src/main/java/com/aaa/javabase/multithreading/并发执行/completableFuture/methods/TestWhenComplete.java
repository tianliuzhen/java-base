package com.aaa.javabase.multithreading.并发执行.completableFuture.methods;

import java.util.concurrent.CompletableFuture;

/**
 * @author liuzhen.tian
 * @version 1.0 TestWhenComplete.java  2021/9/23 22:28
 */
public class TestWhenComplete {
    public static void main(String[] args) {
        // supplyAsync->whenComplete->exceptionally
        test1();

        // supplyAsync->exceptionally->whenComplete
        test2();
    }

    /**
     * 根据控制台,我们可以看出执行流程是这样,supplyAsync->exceptionally->whenComplete,
     * 代码先执行了exceptionally后执行whenComplete,
     * 可以发现,由于在exceptionally中对异常进行了处理,并返回了默认值,whenComplete中接收到的结果是一个正常的结果,
     * 被exceptionally美化过的结果,这一点需要留意一下.
     */
    public static void test2() {
        CompletableFuture<String> futureA = CompletableFuture.
                supplyAsync(() -> "执行结果:" + (100 / 0))
                .thenApply(s -> "apply result:" + s)
                .exceptionally(e -> {
                    System.out.println("ex:" + e.getMessage()); //ex:java.lang.ArithmeticException: / by zero
                    return "futureA result: 100";
                })
                .whenComplete((s, e) -> {
                    if (e == null) {
                        System.out.println(s);//futureA result: 100
                    } else {
                        System.out.println(e.getMessage());//未执行
                    }
                });
        System.out.println(futureA.join());//futureA result: 100
    }

    /**
     * 根据控制台,我们可以看出执行流程是这样,supplyAsync->whenComplete->exceptionally,
     * 可以看出并没有进入thenApply执行,
     * 原因也显而易见,在supplyAsync中出现了异常,thenApply只有当正常返回时才会去执行.
     * 而whenComplete不管是否正常执行,还要注意一点,whenComplete是没有返回值的.
     */
    public static void test1() {
        CompletableFuture<String> futureA = CompletableFuture.
                supplyAsync(() -> "执行结果:" + (100 / 0))
                .thenApply(s -> "apply result:" + s)
                .whenComplete((s, e) -> {
                    if (s != null) {
                        System.out.println(s);//未执行
                    }
                    if (e == null) {
                        System.out.println(s);//未执行
                    } else {
                        System.out.println(e.getMessage());//java.lang.ArithmeticException: / by zero
                    }
                }).exceptionally(e -> {
                    System.out.println("ex" + e.getMessage()); //ex:java.lang.ArithmeticException: / by zero
                    return "futureA result: 100";
                });
        System.out.println(futureA.join());//futureA result: 100
    }
}
