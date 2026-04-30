package com.aaa.javabase.threadTest;

/**
 * @author liuzhen.tian
 * @version 1.0 SingleThreadLocalMemoryLeakDemo.java  2026/2/15 22:48
 */
/**
 * 单线程环境下的 ThreadLocal 内存泄漏演示
 * 不涉及线程池，更容易理解内存泄漏的根本原因
 */

import java.lang.reflect.Field;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SingleThreadGlobaMemoryLeakDemo {
    static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    static ThreadLocal<BigData> tl = new ThreadLocal<>();

    static class BigData {
        private byte[] data = new byte[10 * 1024 * 1024]; // 10MB
        private String name;

        public BigData(String name) {
            this.name = name;
            System.out.println(name + " 创建");
        }

        @Override
        protected void finalize() {
            System.out.println(name + " 回收");
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("========== ThreadLocal内存泄漏演示 ==========");
        for (int i = 0; i < 8; i++) {
            int finalI = i;
            executor.execute(() -> {
                tl.set(new BigData("对象：" + finalI));
            });
        }
        // 等待执行完毕
        Thread.sleep(2000);

        printAllThreadsMap("GC后（注意key为null的Entry）");

        // 手动触发GC
        System.gc();

        Thread.sleep(2000);
        printAllThreadsMap("GC后（注意key为null的Entry）");

        // 验证 executor 里面的线程的printMap todo

    }

    /**
     * 打印所有线程的ThreadLocalMap状态
     */
    private static void printAllThreadsMap(String msg) throws Exception {
        System.out.println(msg + " 线程池中线程状态:");

        // 获取线程池中的所有线程
        Field workersField = ThreadPoolExecutor.class.getDeclaredField("workers");
        workersField.setAccessible(true);
        java.util.Set<?> workers = (java.util.Set<?>) workersField.get(executor);

        if (workers != null) {
            for (Object worker : workers) {
                // 获取Worker中的线程
                Field threadField = worker.getClass().getDeclaredField("thread");
                threadField.setAccessible(true);
                Thread thread = (Thread) threadField.get(worker);

                System.out.println("线程: " + thread.getName());
                printThreadLocalMap(thread, "    ");
            }
        }
    }
    /**
     * 打印指定线程的ThreadLocalMap
     */
    private static void printThreadLocalMap(Thread thread, String prefix) throws Exception {
        Field field = Thread.class.getDeclaredField("threadLocals");
        field.setAccessible(true);
        Object map = field.get(thread);

        if (map == null) {
            System.out.println(prefix + "ThreadLocalMap=null");
            return;
        }

        Field tableField = map.getClass().getDeclaredField("table");
        tableField.setAccessible(true);
        Object[] table = (Object[]) tableField.get(map);


        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                Field valueField = table[i].getClass().getDeclaredField("value");
                valueField.setAccessible(true);
                Object value = valueField.get(table[i]);

                if (!(value instanceof BigData)) {
                   continue;
                }
                Field referentField = Class.forName("java.lang.ref.Reference")
                        .getDeclaredField("referent");
                referentField.setAccessible(true);
                Object key = referentField.get(table[i]);
                if (key == null) {
                    System.out.println(prefix + "  Entry[" + i + "]: key=null, value=" + value + "  ← 内存泄漏的Entry");
                } else {
                    System.out.println(prefix + "  Entry[" + i + "]: key=" + key + ", value=" + value);
                }
            }
        }

    }
}
