package com.aaa.javabase.multithreading.并发执行.completableFuture;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

/**
 * @author liuzhen.tian
 * @version 1.0 SupplyAsyncText.java  2021/2/24 20:05
 */
@Slf4j
public class ThenCombineText {

    /**
     * 普通的方法同步执行
     */
    @Test
    public void testSyncLogOff() {
        int userId = 45;
        // 同步
        long begin = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append(canDo1(userId));
        sb.append(canDo2(userId));
        sb.append(canDo3(userId));
        long end = System.currentTimeMillis();
        // 每一个执行1s 同步：结果是执行应该是 > 5s 的
        log.info("noUseAsync |{}|{} s", sb.toString(), (end - begin) / 1000.0);
    }


    /**
     * 方式一：使用 thenCombine 异步执行多个方法
     */
    @Test
    public void testCanLogOff() {
        // case 设定：如果 userId > 100 则在某一个环节抛错
        int userId = 1;
        // 异步 每一个执行1s 异步并行：结果是执行应该 < 5s
        long begin2 = System.currentTimeMillis();
        String result =
                CompletableFuture.supplyAsync(() -> canDo1(userId))
                        .thenCombine(CompletableFuture.supplyAsync(() -> canDo2(userId)), (s1, s2) -> s1 + s2)
                        .thenCombine(CompletableFuture.supplyAsync(() -> canDo3(userId)), (s1, s2) -> s1 + s2)
                        .exceptionally(
                                e -> {
                                    //如果发生了异常，会在这里扑捉到
                                    log.error("exception: ", e);
                                    return e.getMessage();
                                })
                        .join();
        long end2 = System.currentTimeMillis();
        log.info("supplyAsync|{}|{} s", result, (end2 - begin2) / 1000.0);
    }




    private String canDo1(int userId) {
        try {
            System.out.println("canDo1...");
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    private String canDo2(int userId) {
        try {
            System.out.println("canDo2...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    private String canDo3(int userId) {
        try {
            System.out.println("canDo3...");
            Thread.sleep(1000);
            if (userId > 100) {
                throw new RuntimeException(String.format("用户[%s]还有订单，不可注销", userId));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
