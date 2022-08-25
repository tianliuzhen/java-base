package com.aaa.javabase.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 ThreadUtil.java  2022/8/22 20:43
 */
public class ThreadUtil {

    public static final ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(3, 6, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

    public static ThreadPoolExecutor getPoolExecutor() {
        return POOL_EXECUTOR;
    }

    public static void execute(Runnable command) {
        POOL_EXECUTOR.execute(command);
    }

    public static void close() {
        POOL_EXECUTOR.shutdownNow();
    }
}
