package com.aaa.javabase.util;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.HashMap;
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
                futureList.add(CompletableFuture.supplyAsync(supplier, poolExecutor));
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
                futureList.add(CompletableFuture.runAsync(runnable, poolExecutor));
            }

            // 2、阻塞线程直到所有线程完成
            CompletableFuture[] completableFutures = futureList.toArray(new CompletableFuture[futureList.size()]);
            CompletableFuture.allOf(completableFutures).get();
        } catch (Exception e) {
            log.error("[doRunAsync is error]", e);
        }
        return results;
    }

    /**
     * 并发执行，带返回值
     * 谨慎使用，此方法会默认的核心线程是 n-1 = Runtime.getRuntime().availableProcessors() - 1
     * java.util.concurrent.ForkJoinPool#makeCommonPool()
     *
     * @param runnableList 函数
     * @param <T>          泛型返回值
     * @return List<T>
     */
    public static <T> List<T> doRunAsync(List<Runnable> runnableList) {
        List<T> results = new ArrayList<>();
        List<CompletableFuture<Void>> futureList = new ArrayList<>();
        try {
            // 1、构建Future线程
            for (Runnable runnable : runnableList) {
                futureList.add(CompletableFuture.runAsync(runnable));
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
        // testOne();

        // 会有线程安全问题
        // testError();

        // 这里有坑，核心线程就n-1个，超过就会阻塞
        testDoRunAsync();
    }

    private static void testDoRunAsync() {
        List<Runnable> runnableList = new ArrayList<>();
        // 这里超过7个就会阻塞了
        for (int i = 0; i < 8; i++) {
            runnableList.add(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

        }
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<Object> objects = doRunAsync(runnableList);
        stopWatch.stop();
        double totalTimeSeconds = stopWatch.getTotalTimeSeconds();

        System.out.println(totalTimeSeconds);
    }

    /**
     * 测试普通方法
     */
    private static void testOne() {
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

    /**
     * 测试普通方法
     */
    private static void testError() {
        List<Runnable> runnables = new ArrayList<>();
        //  todo 这里是全局变量
        HashMap<String, Object> result = new HashMap<>();
        for (long i = 0; i < 10; i++) {
            long finalI1 = i;
            runnables.add(() -> {
                result.put("result", (Lists.newArrayList(finalI1)));
                testError(result);
            });
        }
        // 并发执行会影响 result
        FutureUtil.doRunAsync(runnables, null);
    }

    public static void testError(HashMap<String, Object> result) {
        System.out.println(result);
    }
}
