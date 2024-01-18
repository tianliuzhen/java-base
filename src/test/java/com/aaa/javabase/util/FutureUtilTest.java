package com.aaa.javabase.util;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author liuzhen.tian
 * @version 1.0 FutureUtil.java  2024/1/18 21:12
 */
public class FutureUtilTest {
    @Test
    public void executeWithTimeout() throws Exception {
        // 不带返回值的【超时返回】
        FutureUtil.executeWithTimeout(() -> {
        }, 3, TimeUnit.SECONDS, () -> {

        });
        // 带返回值的【超时返回】
        FutureUtil.executeWithTimeout(() -> {
            return "";
        }, 3, TimeUnit.SECONDS, () -> {
            return "";
        });
    }

    @Test
    public void executeWithTimeout2() throws Exception {
        FutureUtil.executeWithTimeout(() -> {
            return "";
        }, 3, TimeUnit.SECONDS, () -> {
            return "";
        });
    }


    @Test
    public void executeWithJoin() {
        // 带返回值的【超时返回】
        for (int i = 0; i < 10; i++) {
            FutureUtil.executeWithJoin(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + ":executeWithJoin 开始执行...");
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getName() + ":executeWithJoin 结束执行...");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, 3000, () -> {
                System.out.println("executeWithJoin 超时返回");
                return null;
            });
        }
    }

    @Test
    public void main() {
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
        List<Object> objects = FutureUtil.doRunAsync(runnableList);
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
