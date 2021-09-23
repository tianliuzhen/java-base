package com.aaa.javabase.multithreading.并发执行.completableFuture.methods;

import java.util.concurrent.CompletableFuture;

/**
 * handle 与 WhenComplete 比较类似
 * @author liuzhen.tian
 * @version 1.0 TestHandle.java  2021/9/23 22:36
 */
public class TestHandle {
    public static void main(String[] args) {
        //  supplyAsync -> exceptionally -> handle
        test1();
        //  supplyAsync ->  handle -> exceptionally
        test2();
    }

    /**
     * 根据控制台输出,可以看到先执行handle,打印了异常信息,
     * 并对接过设置了默认值500,exceptionally并没有执行,因为它得到的是handle返回给它的值,由此我们大概推测handle和whenComplete的区别
     * <p>
     * 　　　1.都是对结果进行处理,handle有返回值,whenComplete没有返回值
     * <p>
     * 　　　2.由于1的存在,使得handle多了一个特性,可在handle里实现exceptionally的功能
     */
    public static void test2() {
        CompletableFuture<String> futureA = CompletableFuture.
                supplyAsync(() -> "执行结果:" + (100 / 0))
                .thenApply(s -> "apply result:" + s)
                .handle((s, e) -> {
                    if (e == null) {
                        System.out.println(s);//未执行
                    } else {
                        System.out.println(e.getMessage());//java.lang.ArithmeticException: / by zero
                    }
                    return "handle result:" + (s == null ? "500" : s);
                })
                .exceptionally(e -> {
                    System.out.println("ex:" + e.getMessage()); //未执行
                    return "futureA result: 100";
                });
        System.out.println(futureA.join());//handle result:500
    }

    /**
     * 通过控制台,我们可以看出,最后打印的是handle result:futureA result: 100,
     * 执行exceptionally后对异常进行了"美化",返回了默认值,
     * 那么handle得到的就是一个正常的返回,我们再试下,先调用handle再调用exceptionally的情况.
     */
    public static void test1() {
        CompletableFuture<String> futureA = CompletableFuture.
                supplyAsync(() -> "执行结果:" + (100 / 0))
                .thenApply(s -> "apply result:" + s)
                .exceptionally(e -> {
                    System.out.println("ex:" + e.getMessage()); //java.lang.ArithmeticException: / by zero
                    return "futureA result: 100";
                })
                .handle((s, e) -> {
                    if (e == null) {
                        System.out.println(s);//futureA result: 100
                    } else {
                        System.out.println(e.getMessage());//未执行
                    }
                    return "handle result:" + (s == null ? "500" : s);
                });
        System.out.println(futureA.join());//handle result:futureA result: 100
    }
}
