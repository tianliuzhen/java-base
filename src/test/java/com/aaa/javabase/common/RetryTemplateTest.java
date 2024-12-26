package com.aaa.javabase.common;

import com.aaa.javabase.common.retry.RetryTemplate;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 RetryTemplateTest.java  2024/12/26 21:07
 */
public class RetryTemplateTest {

    @Test
    public void test1() throws Exception {
        String execute = new RetryTemplate<String>()
                .retryCount(3)
                .sleepTime(1000)
                .timeUnit(TimeUnit.MILLISECONDS)
                .bizTask(() -> {
                    // HttpClientUtil.doPostWithNoPool("http://127.0.0.1", null)
                    int a = 1 / 0;

                    return UUID.randomUUID().toString();
                })
                .execute();
    }

    @Test
    public void test2() {
        String result = null;
        try {
            result = RetryTemplate.<String>builder()
                    .bizTask(() -> {
                        System.out.println("开始执行");
                        // HttpClientUtil.doPostWithNoPool("http://127.0.0.1", null)
                        if (true) {
                            // error 异常
                            throw new RuntimeException("自定义重试异常");
                        }
                        return UUID.randomUUID().toString();
                    })
                    .invoke();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        System.out.println(result);
    }
}
