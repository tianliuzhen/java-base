package com.aaa.javabase.web;

import com.aaa.javabase.util.ThreadPoolUtil;
import com.aaa.javabase.util.UserContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 ThreadPoolTestController.java  2023/11/19 18:32
 */
@RestController
public class ThreadPoolTestController {
    // 模拟并发请求
    private static final int requestSize = 1000;

    // 模型tomcat线程池
    final static ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 10,
            5, TimeUnit.MINUTES, new ArrayBlockingQueue<>(100000));

    // 模型每个请求创建的对象
    static class UserInfoBigObj {
        private byte[] a = new byte[1024 * 1024 * 2];/*2M大小的数组*/
    }

    // 每个线程缓存的自己的副本变量
    static ThreadLocal<UserInfoBigObj> userInfoLocal = new ThreadLocal<>();


    /**
     * 测试 set 并且调用 remove
     */
    @GetMapping(value = "/testSetAndRemove")
    public void test() throws InterruptedException {
        for (int i = 0; i < requestSize; ++i) {
            pool.execute(() -> {
                userInfoLocal.set(new UserInfoBigObj());
                userInfoLocal.remove();
            });
            // Thread.sleep(100);
        }
    }

    /**
     * 测试 set 没有调用 remove
     */
    @GetMapping(value = "/testSetNoRemove")
    public void testSet() throws InterruptedException {
        for (int i = 0; i < requestSize; ++i) {
            pool.execute(() -> {
                userInfoLocal.set(new UserInfoBigObj());
            });
            // Thread.sleep(100);
        }
    }

    /**
     * 测试没有set的情况
     */
    @GetMapping(value = "/testNoSet")
    public void test2() throws InterruptedException {
        for (int i = 0; i < requestSize; ++i) {
            pool.execute(() -> new UserInfoBigObj());
            Thread.sleep(100);
        }
    }

    /**
     * 手动触发GC
     */
    @GetMapping(value = "/gc")
    public void gc() {
        System.gc();
    }

    /**
     * 测试setUserName
     */
    @GetMapping(value = "/setUserName")
    public void setUserName() throws InterruptedException {
        for (int i = 0; i < 20; ++i) {
            int finalI = i;
            ThreadPoolUtil.common_pool.execute(() -> {
                UserContextHolder.setUserName("name:" + finalI);

                ThreadPoolUtil.starmap_pool.execute(() -> {
                    System.out.println(UserContextHolder.getUserName());
                });

            });
            Thread.sleep(100);
        }
    }


    /**
     * 测试没有set的情况
     */
    @GetMapping(value = "/getUserName")
    public void getUserName() throws InterruptedException {
        for (int i = 0; i < 20; ++i) {
            int finalI = i;
            ThreadPoolUtil.common_pool.execute(() -> {
                System.out.println(UserContextHolder.getUserName());
            });
            Thread.sleep(100);
        }
    }
}
