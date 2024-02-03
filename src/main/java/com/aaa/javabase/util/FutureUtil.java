package com.aaa.javabase.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @author liuzhen.tian
 * @version 1.0 FutureUtil.java  2023/2/27 21:31
 */
@Slf4j
public class FutureUtil {
    /**
     * 超时专用线程池
     */
    public static final ThreadPoolExecutor execute_timeout_pool =
            new ThreadPoolExecutor(3,
                    6,
                    60,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(10),
                    new ThreadFactoryBuilder().setNameFormat("execute_timeout_pool").build());

    static {
        execute_timeout_pool.allowCoreThreadTimeOut(true);
    }

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

    /**
     * 执行超时返回（基于thread.join）
     *
     * @param bizTask       业务逻辑
     * @param timeout       超时时间：ms
     * @param fallbackLogic 兜底逻辑
     * @param <T>
     * @return
     */
    public static <T> T executeWithJoin(Runnable bizTask, long timeout, Callable<T> fallbackLogic) {
        Thread thread = new Thread(bizTask);
        thread.start();

        try {
            thread.join(timeout);
            if (thread.isAlive()) {
                return fallbackLogic.call();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 执行超时返回（CompletableFuture.get）
     *
     * @param task
     * @param timeout
     * @param unit
     * @param fallbackLogic
     * @param <T>
     */
    public static <T> void executeWithTimeout(Runnable task, long timeout, TimeUnit unit, Runnable fallbackLogic) {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                task.run();
            } catch (Exception e) {
                throw new CompletionException(e);
            }
        });
        try {
            future.get(timeout, unit);
        } catch (TimeoutException e) {
            fallbackLogic.run();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // 尝试中断正在执行的任务
            future.cancel(true);
        }
    }
    static  ExecutorService executor = Executors.newSingleThreadExecutor();
    /**
     * 执行超时返回（基于Future.get）
     *
     * @param task
     * @param timeout
     * @param unit
     * @param fallbackLogic
     */
    public static void executeWithTimeoutV2(Runnable task, long timeout, TimeUnit unit, Runnable fallbackLogic) {
        Future<?> future = executor.submit(task);
        try {
            future.get(timeout, unit);
        } catch (TimeoutException e) {
            fallbackLogic.run();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // 尝试中断正在执行的任务
            future.cancel(true); // 底层调用：java.lang.Thread.interrupt
            // executor.shutdownNow();
        }
    }


    /**
     * 执行超时返回（CompletableFuture.get）
     *
     * @param task
     * @param timeout
     * @param unit
     * @param fallbackLogic
     * @param <T>
     */
    public static <T> T executeWithTimeout(Callable<T> task, long timeout, TimeUnit unit, Callable<T> fallbackLogic) throws Exception {
        CompletableFuture<T> future = CompletableFuture.supplyAsync(() -> {
            try {
                return task.call();
            } catch (Exception e) {
                throw new CompletionException(e);
            }
        });
        try {
            return future.get(timeout, unit);
        } catch (TimeoutException e) {
            return fallbackLogic.call();
        } catch (InterruptedException | ExecutionException e) {
            log.error("executeWithTimeout.err", e);
        } finally {
            // 尝试中断正在执行的任务
            future.cancel(true);
        }
        return null;
    }


}
