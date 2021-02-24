package com.aaa.javabase.multithreading.并发执行.completableFuture;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author liuzhen.tian
 * @version 1.0 Test1.java  2021/2/24 19:51
 */
@Slf4j
public class AllOf {


    /**
     * 方式二：使用 CompletableFuture.allOf(...) 异步执行多个方法
     */
    @Test
    public void testCanLogOffV2() throws ExecutionException, InterruptedException {
        long begin2 = System.currentTimeMillis();
        CompletableFuture<String> one = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });
        CompletableFuture<String> two = CompletableFuture.supplyAsync(() -> {
            try {
                int a = 1 / 0;
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });
        // 等待 one 和 two 都执行完之后才去执行下面的逻辑
        try {
            CompletableFuture.allOf(one, two).exceptionally(e->{
                //如果发生了异常，会在这里扑捉到
                log.error("exception: ", e);
                return null;
            }).get(3L, TimeUnit.SECONDS);
            // 这里的 get(3L, TimeUnit.SECONDS); 类似 future 的get() 机制
            // 这里也可以使用 join() , return reportJoin((r = result) == null ? waitingGet(false) : r);
            // get()    ,return reportGet((r = result) == null ? waitingGet(true) : r);
        } catch (TimeoutException e) {
            // 这里超时要扑捉异常
            e.printStackTrace();
        }

        long end2 = System.currentTimeMillis();
        System.out.println("执行时间：" + (end2 - begin2) / 1000.0);
        System.out.println("end...");
    }

}
