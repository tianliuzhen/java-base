package com.aaa.javabase.common.retry;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 RetryTemplate.java  2020/8/17 15:53
 */
@Log4j2
@Data
public class RetryTemplate<T> {

    /**
     * 重试次数
     */
    private int retryCount = 3;
    /**
     * 重试的睡眠时间，默认是ms
     */
    private int sleepTime = 2000;

    /**
     * 业务方法
     * java.util.concurrent.Callable
     * java.util.function.Supplier
     */
    private Callable<T> bizTask;
    /**
     * 时间类型
     */
    private TimeUnit timeUnit = TimeUnit.MILLISECONDS;

    // 提供访问构建器的静态方法
    public static <T> RetryTemplate<T> builder() {
        return new RetryTemplate<>();
    }


    public RetryTemplate<T> retryCount(final int retryCount) {
        this.retryCount = retryCount;
        return this;
    }

    public RetryTemplate<T> sleepTime(final int sleepTime) {
        this.sleepTime = sleepTime;
        return this;
    }

    public RetryTemplate<T> bizTask(final Callable<T> bizTask) {
        this.bizTask = bizTask;
        return this;
    }

    public RetryTemplate<T> timeUnit(final TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
        return this;
    }


    /**
     * 执行 - 丢弃异常
     *
     * @return
     */
    public T execute() {
        for (int i = 0; i < retryCount; i++) {
            try {
                return bizTask.call();
            } catch (Throwable e) {
                try {
                    this.timeUnit.sleep(this.sleepTime);
                } catch (InterruptedException ignored) {
                }
                log.error("RetryTemplate.execute.重试" + (i + 1) + "几次");
            }
        }
        return null;

    }

    /**
     * 执行 - 最后一次抛出异常
     *
     * @return
     */
    public T invoke() throws Throwable {
        for (int i = 0; i < retryCount; i++) {
            try {
                return bizTask.call();
            } catch (Throwable e) {
                if (i == retryCount - 1) {
                    throw e;
                }
                try {
                    this.timeUnit.sleep(this.sleepTime);
                } catch (InterruptedException ignored) {
                }
            }
        }
        return null;
    }


}
