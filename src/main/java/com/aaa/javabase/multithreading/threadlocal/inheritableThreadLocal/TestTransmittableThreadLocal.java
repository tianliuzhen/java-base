package com.aaa.javabase.multithreading.threadlocal.inheritableThreadLocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * InheritableThreadLocal 虽然可以解决线程池父子线程传递变量的问题，
 * 但是不能解决常驻线程的问题
 *
 * @author liuzhen.tian
 * @version 1.0 TestTransmittableThreadLocal.java  2024/5/26 12:55
 * @see com.aaa.javabase.multithreading.threadlocal.inheritableThreadLocal.InheritableThreadLocalTest1
 * 因只有一个线程1，线程激活后，再次set ，子线程无法感知
 * 而，TransmittableThreadLocal 的核心作用，就是为了解决这个问题
 * <p>
 * 我在工作中一般不使用TransmittableThreadLocal，
 * 但原理都是类似的，每次线程池提交之前，会包装一下 runnable 把主线程ThreadLocal变量传递过来
 */
public class TestTransmittableThreadLocal {
    static TransmittableThreadLocal<Map<String, String>> USER_CONTEXT = new TransmittableThreadLocal<>();
    static ExecutorService executorService = Executors.newFixedThreadPool(1);

    /**
     * https://blog.csdn.net/qq_18300037/article/details/128776124
     * TransmittableThreadLocal 简单使用
     *
     * @param args
     */
    public static void main(String[] args) {
        //使用TTL
        USER_CONTEXT.set(new HashMap<String, String>() {{
            put("name", "tom");
        }});

        executorService.submit(TtlRunnable.get(() -> {
            System.out.println(USER_CONTEXT.get().get("name"));
        }));

        //使用TTL
        USER_CONTEXT.set(new HashMap<String, String>() {{
            put("name", "allen");
        }});

        executorService.submit(TtlRunnable.get(() -> {
            System.out.println(USER_CONTEXT.get().get("name"));
        }));
    }

    // 原理
    // testAtomicReference();
    private static void testAtomicReference() {
        AtomicReference<Object> capturedRef = new AtomicReference<>();
        capturedRef.set(new HashMap<String, String>() {{
            put("a", "a1");
        }});
        Object o = capturedRef.get();
        boolean b = capturedRef.compareAndSet(o, null);
    }
}
