package com.aaa.javabase.multithreading.threadlocal.ttl;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 TtlSample1.java  2020/10/25 0:59
 */
public class TtlSample1 {
    static TransmittableThreadLocal<String> TTL = new TransmittableThreadLocal<>();

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            // 在父线程中设置变量
            TTL.set("throwable");
            new Thread(TtlRunnable.get(() -> {
                methodFrame1();
            }), "childThread").start();
        }, "parentThread").start();
        TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
    }

    private static void methodFrame1() {
        methodFrame2();
    }

    private static void methodFrame2() {
        System.out.println(TTL.get());
    }
}
