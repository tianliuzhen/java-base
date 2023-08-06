package com.aaa.javabase.common.retry;

import com.aaa.javabase.util.HttpClientUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 Test.java  2020/8/17 15:54
 */
public class TestMain {
    public static void main(String[] args) throws InterruptedException {
        //方法1. 基于线程
        retryDemo();

        //方法2. 基于反射
        mainMethod();
    }


    public static void retryDemo() {
        Object result = null;
        try {
            result = RetryTemplate.builder()
                    .retryTime(3).sleepTime(1000).timeUnit(TimeUnit.MILLISECONDS)
                    .bizTask(() -> HttpClientUtil.sendPostByUrlEncoder("http://127.0.0.1", null))
                    .build()
                    .execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(result);
    }

    public static void mainMethod() {
        subMethod("123", "456");
    }

    public static String subMethod(String param1, String param2) {
        Object retry = RetryUtil.setRetryTimes(1).retry(param1, param2);

        System.out.println(param1 + param2);
        return "sss";
    }
}
