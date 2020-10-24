package com.aaa.javabase.multithreading.threadlocal.ttl;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 TtlSample2.java  2020/10/25 1:01
 */
public class TtlSample2 {

    static TransmittableThreadLocal<String> TTL = new TransmittableThreadLocal<>();
    static final Executor EXECUTOR = Executors.newFixedThreadPool(1);

    public static void main(String[] args) throws Exception {
        TTL.set("throwable");
        EXECUTOR.execute(TtlRunnable.get(() -> {
            System.out.println(TTL.get());
        }));
        TTL.set("doge");
        EXECUTOR.execute(TtlRunnable.get(() -> {
            System.out.println(TTL.get());
        }));
        TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
    }
}
