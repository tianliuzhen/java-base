package com.aaa.javabase.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author liuzhen.tian
 * @version 1.0 FutureUtil.java  2023/2/27 21:31
 */
@Slf4j
public class FutureUtil {

    /**
     * 并发执行，带返回值
     *
     * @param suppliers    函数
     * @param poolExecutor 线程池
     * @param <T>          泛型返回值
     * @return List<T>
     */
    public static <T> List<T> doSupplyAsync(List<Supplier<T>> suppliers, ThreadPoolExecutor poolExecutor) {
        List<T> results = new ArrayList<>();
        List<CompletableFuture<T>> futureList = new ArrayList<>();
        try {
            // 1、构建Future线程
            for (Supplier<T> supplier : suppliers) {
                if (null == poolExecutor) {
                    futureList.add(CompletableFuture.supplyAsync(supplier));
                } else {
                    futureList.add(CompletableFuture.supplyAsync(supplier, poolExecutor));
                }
            }

            // 2、阻塞线程直到所有线程完成
            CompletableFuture[] completableFutures = futureList.toArray(new CompletableFuture[futureList.size()]);
            CompletableFuture.allOf(completableFutures).get();

            // 3、返回结果
            for (CompletableFuture<T> future : futureList) {
                if (future.get() != null) {
                    results.add(future.get());
                }
            }
        } catch (Exception e) {
            log.error("[doSupplyAsync is error]", e);
        }
        return results;
    }

    /**
     * 并发执行，带返回值
     *
     * @param runnableList 函数
     * @param poolExecutor 线程池
     * @param <T>          泛型返回值
     * @return List<T>
     */
    public static <T> List<T> doRunAsync(List<Runnable> runnableList, ThreadPoolExecutor poolExecutor) {
        List<T> results = new ArrayList<>();
        List<CompletableFuture<Void>> futureList = new ArrayList<>();
        try {
            // 1、构建Future线程
            for (Runnable runnable : runnableList) {
                if (null == poolExecutor) {
                    futureList.add(CompletableFuture.runAsync(runnable));
                } else {
                    futureList.add(CompletableFuture.runAsync(runnable, poolExecutor));
                }

            }

            // 2、阻塞线程直到所有线程完成
            CompletableFuture[] completableFutures = futureList.toArray(new CompletableFuture[futureList.size()]);
            CompletableFuture.allOf(completableFutures).get();
        } catch (Exception e) {
            log.error("[doRunAsync is error]", e);
        }
        return results;
    }

    public static void main(String[] args) {
        List<Supplier<String>> suppliers = new ArrayList<>();
        suppliers.add(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "2";
        });
        suppliers.add(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "1";
        });
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<String> list = FutureUtil.doSupplyAsync(suppliers, null);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeSeconds());
        System.out.println(list.toString());
    }
}
