package com.aaa.javabase.multithreading.threadlocal.inheritableThreadLocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author liuzhen.tian
 * @version 1.0 TestTransmittableThreadLocal.java  2024/5/26 12:55
 */
public class TestTransmittableThreadLocal {

    /**
     * https://blog.csdn.net/qq_18300037/article/details/128776124
     * TransmittableThreadLocal 简单使用
     *
     * @param args
     */
    public static void main(String[] args) {
        // 原理
        // testAtomicReference();


        //使用TTL
        TransmittableThreadLocal<Map<String, String>> USER_CONTEXT = new TransmittableThreadLocal<>();

        USER_CONTEXT.set(new HashMap<String, String>() {{
            put("name", "tom");
        }});

        //将普通的Runnable包装成TtlRunnable
        TtlRunnable ttlRunnable = TtlRunnable.get(() -> {
            System.out.println(USER_CONTEXT.get().get("name"));
        });
        new Thread(ttlRunnable).start();

    }

    private static void testAtomicReference() {
        AtomicReference<Object> capturedRef = new AtomicReference<>();
        capturedRef.set(new HashMap<String, String>() {{
            put("a", "a1");
        }});
        Object o = capturedRef.get();
        boolean b = capturedRef.compareAndSet(o, null);
    }
}
