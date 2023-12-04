package com.aaa.javabase.multithreading.threadpool.delayed;

/**
 * @author liuzhen.tian
 * @version 1.0 BoundedScheduledThreadPoolExecutor.java  2023/11/28 22:42
 */

import java.lang.reflect.Field;
import java.util.concurrent.*;

public class BoundedScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {
    public BoundedScheduledThreadPoolExecutor(int corePoolSize, RejectedExecutionHandler handler, int queueCapacity) {
        super(corePoolSize, handler);
        //
        super.setMaximumPoolSize(corePoolSize);
        super.setKeepAliveTime(0, TimeUnit.MILLISECONDS);
        ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(queueCapacity);
        try {
            Field workQueueField = ThreadPoolExecutor.class.getDeclaredField("workQueue");
            workQueueField.setAccessible(true);

            workQueueField.set(this, queue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
