package com.aaa.javabase.common.retry;

import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 RetryTemplate.java  2020/8/17 15:53
 */
@Log4j2
@Data
@Builder
public class RetryTemplate {

    private int retryTime = 1;
    /**
     * 重试的睡眠时间，默认是ms
     */
    private int sleepTime = 0;

    /**
     * 业务方法
     * java.util.concurrent.Callable
     * java.util.function.Supplier
     */
    private Callable bizTask;
    /**
     * 时间类型
     */
    private TimeUnit timeUnit = TimeUnit.MILLISECONDS;


    /**
     * @return
     */
    @SneakyThrows
    public Object execute() throws Exception {
        for (int i = 0; i < retryTime; i++) {
            try {
                return bizTask.call();
            } catch (Exception e) {
                this.timeUnit.sleep(this.sleepTime);
                log.warn("RetryTemplate.execute.重试" + (retryTime + 1) + "几次");
                log.error("RetryTemplate.execute.error", e);
            }
        }
        return null;
    }

    /**
     * 带线程池执行
     *
     * @param executorService
     * @return
     */
    public Object submit(ExecutorService executorService) {
        if (executorService == null) {
            throw new IllegalArgumentException("please choose executorService!");
        }
        return executorService.submit(this::execute);
    }


}
