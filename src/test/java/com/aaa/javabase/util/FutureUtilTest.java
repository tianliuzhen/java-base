package com.aaa.javabase.util;

import com.alibaba.fastjson.JSONObject;
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
    public void doSupplyAsyncWithTimeout() {
        List<Supplier<Integer>> suppliers = new ArrayList<>();
        for (int i = 0; i < 19; i++) {
            int finalI = i;
            suppliers.add(() -> {
                if (finalI > 5) {
                    try {
                        TimeUnit.SECONDS.sleep(8);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                return finalI;
            });

        }
        // 第一次线程池会预热：大概是 3.6232526
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<Integer> integers = FutureUtil.doSupplyAsync(suppliers, 2, TimeUnit.SECONDS, ThreadPoolUtil.common_pool);
        stopWatch.stop();
        System.out.println("integers = " + integers);
        System.out.println("stopWatch.getTotalTimeSeconds() = " + stopWatch.getTotalTimeSeconds());
        // 第二次线程池会正常：大概是 2.0048514
        StopWatch stopWatch2 = new StopWatch();
        stopWatch2.start();
        List<Integer> integers2 = FutureUtil.doSupplyAsync(suppliers, 2, TimeUnit.SECONDS, ThreadPoolUtil.common_pool);
        stopWatch2.stop();
        System.out.println("integers = " + integers2);
        System.out.println("stopWatch.getTotalTimeSeconds() = " + stopWatch2.getTotalTimeSeconds());
    }

    @Test
    public void doSupplyAsyncTimeout() throws Exception {
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
        // StopWatch stopWatch = new StopWatch();
        // stopWatch.start();
        // FutureUtil.executeWithJoin(() -> {
        //     if (true) {
        //         while (true) {
        //             System.out.println(Thread.currentThread().getName() + ":thread1循环中..." + Thread.currentThread().isInterrupted());
        //         }
        //     }
        // }, 1000, () -> {
        //     System.out.println("executeWithJoin 超时返回");
        //     return null;
        // });
        // stopWatch.stop();

        // System.out.println("stopWatch.getTotalTimeSeconds() = " + stopWatch.getTotalTimeSeconds());

        StopWatch stopWatch2 = new StopWatch();
        stopWatch2.start();
        FutureUtil.executeWithTimeoutV2(() -> {
            if (true) {
                try {
                    JSONObject jsonObject = HttpClientUtil.doGetWithPool("http://localhost:8080/testAsyncError");
                    boolean interrupted = Thread.currentThread().isInterrupted();
                    if (interrupted) {
                        System.out.println("thread1程序终止");
                    }
                    System.out.println(1);

                    // 你的任务代码
                } catch (Exception e) {
                    e.printStackTrace();
                    // 处理中断
                }
            }
        }, 1, TimeUnit.SECONDS, () -> {
        });
        stopWatch2.stop();
        System.out.println("stopWatch.getTotalTimeSeconds() = " + stopWatch2.getTotalTimeSeconds());

        if (true) {
            while (true) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + ":thread1循环中..." + Thread.currentThread().isInterrupted());
            }
        }
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

    /**
     *
     */
    public static void main(String[] args) {
        int AC_SHIFT = 48;
        int TC_SHIFT = 32;
        long AC_MASK = 65535L << AC_SHIFT;
        long TC_MASK = 65535L << TC_SHIFT;
        long np = -8;
        long left = (np << AC_SHIFT) & AC_MASK;
        long right = (np << TC_SHIFT) & TC_MASK;
        long ctl = left | right;
        System.out.println("np << AC_SHIFT = " + Long.toBinaryString(np << AC_SHIFT));
        System.out.println("&");
        System.out.println("AC_MASK        = " + Long.toBinaryString(AC_MASK));
        System.out.println("|");
        System.out.println("np << TC_SHIFT = " + Long.toBinaryString(np << TC_SHIFT));
        System.out.println("&");
        System.out.println("TC_MASK        = " + Long.toBinaryString(TC_MASK));
        System.out.println("最终结果");
        System.out.println("ctl-left       = " + Long.toBinaryString(left));
        System.out.println("ctl-right      = " + Long.toBinaryString(right));
        System.out.println("ctl            = " + Long.toBinaryString(ctl));
        System.out.println("ctl            = " + Long.toBinaryString(-1688879925035008L));
        System.out.println("ADD_WORKER     = " + Long.toBinaryString(0x0001L << (TC_SHIFT + 15)));
        /**
         * 1111111111111001000000000000000000000000000000000000000000000000
         * |
         * 0000000000000000111111111111100100000000000000000000000000000000
         * ctl=
         * 1111111111111001111111111111100100000000000000000000000000000000
         * & ADD_WORKER
         * 0000000000000000100000000000000000000000000000000000000000000000
         */

        long AC_UNIT = 1L << AC_SHIFT; // 48
        long TC_UNIT = 1L << TC_SHIFT; // 32
        ctl = ((AC_MASK & (ctl + AC_UNIT)) | (TC_MASK & (ctl + TC_UNIT)));
        System.out.println("nc1            = " + Long.toBinaryString(ctl));
        ctl = ((AC_MASK & (ctl + AC_UNIT)) | (TC_MASK & (ctl + TC_UNIT)));
        System.out.println("nc2            = " + Long.toBinaryString(ctl));
        ctl = ((AC_MASK & (ctl + AC_UNIT)) | (TC_MASK & (ctl + TC_UNIT)));
        System.out.println("nc3            = " + Long.toBinaryString(ctl));
        ctl = ((AC_MASK & (ctl + AC_UNIT)) | (TC_MASK & (ctl + TC_UNIT)));
        System.out.println("nc4            = " + Long.toBinaryString(ctl));
        ctl = ((AC_MASK & (ctl + AC_UNIT)) | (TC_MASK & (ctl + TC_UNIT)));
        System.out.println("nc5            = " + Long.toBinaryString(ctl));
        ctl = ((AC_MASK & (ctl + AC_UNIT)) | (TC_MASK & (ctl + TC_UNIT)));
        System.out.println("nc7            = " + Long.toBinaryString(ctl));
        ctl = ((AC_MASK & (ctl + AC_UNIT)) | (TC_MASK & (ctl + TC_UNIT)));
        System.out.println("nc8            = " + Long.toBinaryString(ctl));
        /**
         * 验证ctl的自增
         nc1-left       = 1111111111111011000000000000000000000000000000000000000000000000
         nc1-right      = 0000000000000000111111111111101100000000000000000000000000000000
         nc1            = 1111111111111010111111111111101000000000000000000000000000000000
         nc2            = 1111111111111011111111111111101100000000000000000000000000000000
         nc3            = 1111111111111100111111111111110000000000000000000000000000000000
         nc4            = 1111111111111101111111111111110100000000000000000000000000000000
         nc5            = 1111111111111110111111111111111000000000000000000000000000000000
         nc7            = 1111111111111111111111111111111100000000000000000000000000000000
         nc8            = 0

         */
    }


    @Test
    public void main() {
        // testOne();

        // 会有线程安全问题
        // testError();

        // 这里有坑，核心线程就n-1个，超过就会阻塞
        testDoRunAsync();
    }

    /**
     * ForkJoinPool 的核心线程数，有没有最大值，目前不好判定
     */
    private static void testDoRunAsync() {
        List<Runnable> runnableList = new ArrayList<>();
        // 这里超过7个就会阻塞了
        for (int i = 0; i < 100; i++) {
            runnableList.add(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("Thread.currentThread().getName() 1111= " + Thread.currentThread().getName());
                    List<Runnable> arrayList2 = new ArrayList<>();
                    arrayList2.add(() -> {
                        System.err.println("Thread.currentThread().getName() 2222= " + Thread.currentThread().getName());

                        List<Runnable> arrayList3 = new ArrayList<>();
                        arrayList3.add(() -> {
                            System.err.println("Thread.currentThread().getName() 3333= " + Thread.currentThread().getName());
                        });
                        FutureUtil.doRunAsync(arrayList3);
                    });
                    FutureUtil.doRunAsync(arrayList2);
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
