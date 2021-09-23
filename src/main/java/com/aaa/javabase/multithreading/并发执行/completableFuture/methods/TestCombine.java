package com.aaa.javabase.multithreading.并发执行.completableFuture.methods;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 TestCombine.java  2021/9/23 21:57
 */
public class TestCombine {
    public static void main(String[] args) {
        // 1、价格
        CompletableFuture<Double> futurePrice = CompletableFuture.supplyAsync(() -> {
            // 模拟接口数据查询时间
            // TimeUnit.SECONDS.sleep(2);
            return 100D;
        });

        // 2、折扣
        CompletableFuture<Double> futureDiscount = CompletableFuture.supplyAsync(() -> 0.88);

        // 3、计算
        CompletableFuture<Double> res = futurePrice.thenCombine(futureDiscount, (price, discount) -> price * discount);

        //最终价格为:88.0
        System.out.println("最终价格为:" + res.join());

    }
}
