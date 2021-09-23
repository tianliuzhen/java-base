package com.aaa.javabase.multithreading.并发执行.completableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author liuzhen.tian
 * @version 1.0 TestDemo1.java  2021/9/23 22:46
 */
public class TestDemo1 {
    public static void main(String[] args) {

        // 模拟 调用的外部接口
        List<String> listDomain = Arrays.asList("www.baidu.com", "www.mp.weixin.qq.com", "www.alibaba.com");

        // 同步方法
        List<String> res = listDomain
                .stream()
                .map(domain -> getDataByDomain(domain))
                .collect(Collectors.toList());

        // 异步方法
        List<CompletableFuture<String>> res2 = listDomain.stream()
                .map(domain -> CompletableFuture.supplyAsync(() -> getDataByDomain(domain)))
                .collect(Collectors.toList());

        // 无参
        CompletableFuture.allOf(res2.toArray(new CompletableFuture[res2.size()]))
                .join();

        // 有参
        // List<String> res3 = res2.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    public static String getDataByDomain(String domain) {
        try {
            // 模拟调用时间是 2s
            TimeUnit.SECONDS.sleep(2);
            System.out.println("domain :" + domain + "数据请求中 ...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return domain;
    }
}
