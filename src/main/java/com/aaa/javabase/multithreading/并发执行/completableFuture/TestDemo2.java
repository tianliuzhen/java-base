package com.aaa.javabase.multithreading.并发执行.completableFuture;

import com.aaa.javabase.util.CompletableFutureTimeout;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author liuzhen.tian
 * @version 1.0 TestDemo1.java  2021/9/23 22:46
 */
public class TestDemo2 {
    public static void main(String[] args) throws Exception {
        long begin = System.currentTimeMillis();

        // 模拟 调用的外部接口
        List<String> listDomain = Arrays.asList("www.baidu.com", "www.mp.weixin.qq.com", "www.alibaba.com");

        // 方法1：异步方法 jdk9版本以上的方法可直接用 orTimeout
        // List<CompletableFuture<String>> futures = listDomain.stream()
        //         .map(domain -> CompletableFuture.supplyAsync(() -> getDataByDomain(domain))
        //                 // 超时处理
        //                 .completeOnTimeout("我是默认值", 3, TimeUnit.SECONDS))
        //         .collect(Collectors.toList());

        // 方法2：自定义实现
        List<CompletableFuture<String>> futures = listDomain.stream()
                .map(domain -> CompletableFutureTimeout.completeOnTimeout(
                        "我是默认值",
                        CompletableFuture.supplyAsync(() -> getDataByDomain(domain)),
                        // 允许超时时间
                        3,
                        TimeUnit.SECONDS))
                .collect(Collectors.toList());

        // join等待所有线程执行完成
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();

        //
        for (CompletableFuture<String> future : futures) {
            System.out.println("future.get() = " + future.get());
        }

        long end = System.currentTimeMillis();
        System.out.println("耗时毫秒：" + (end - begin));
    }

    public static String getDataByDomain(String domain) {
        try {
            // 模拟调用时间是 2s
            if ("www.baidu.com".equals(domain)) {
                TimeUnit.SECONDS.sleep(4);
            }
            TimeUnit.SECONDS.sleep(2);

            System.out.println("domain :" + domain + "数据请求中 ...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return domain;
    }
}
