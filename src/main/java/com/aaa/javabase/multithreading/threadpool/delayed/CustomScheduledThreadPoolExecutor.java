package com.aaa.javabase.multithreading.threadpool.delayed;

/**
 * 为什么要重写 ScheduledThreadPoolExecutor？
 * 原生的 ScheduledThreadPoolExecutor 缺陷
 * 1. 默认的DelayedWorkQueue 属于无界队列，可能会有oom风险
 * 2. 不支持allowCoreThreadTimeOut 无法对核心线程数回收
 *
 * @author liuzhen.tian
 * @version 1.0 CustomScheduledThreadPoolExecutor.java  2023/11/28 22:42
 */

import java.util.concurrent.*;

public class CustomScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {
    private int queueCapacity;

    /**
     * 支持可定义的各种参数的线程池
     *
     * @param corePoolSize  核心线程
     * @param handler       拒绝策略
     * @param queueCapacity 最大队列数
     * @param time          线程存活时间
     * @param unit          时间单位
     */
    public CustomScheduledThreadPoolExecutor(int corePoolSize,
                                             RejectedExecutionHandler handler,
                                             int queueCapacity,
                                             long time,
                                             TimeUnit unit) {
        /*
         *  @see java.util.concurrent.ScheduledThreadPoolExecutor.ScheduledThreadPoolExecutor(int, java.util.concurrent.RejectedExecutionHandler)
         */
        super(corePoolSize, handler);

        // 最大队列数
        this.queueCapacity = queueCapacity;

        // 设置最大线程数
        super.setMaximumPoolSize(corePoolSize);
        // 设置线程的存活时间
        super.setKeepAliveTime(time, unit);

        // 设置可回收所有线程
        this.allowCoreThreadTimeOut(true);

        try {
            // 可以重定义定时Queue，然后反射方式去赋值，达到覆盖原有逻辑
            // ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(queueCapacity);
            // Field workQueueField = ThreadPoolExecutor.class.getDeclaredField("workQueue");
            // workQueueField.setAccessible(true);
            // workQueueField.set(this, queue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * 重写此方法（为了校验最大队列数,让无界队列变有界队列）
     *
     * @param command the task to execute
     * @param delay   the time from now to delay execution
     * @param unit    the time unit of the delay parameter
     * @return
     */
    @Override
    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        System.out.println("this.getQueue().size() = " + this.getQueue().size());
        if (this.getQueue().size() >= queueCapacity) {
            this.getRejectedExecutionHandler().rejectedExecution(command, this);
        }

        return super.schedule(command, delay, unit);
    }
}
