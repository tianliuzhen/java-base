package com.aaa.javabase.threadTest;

import java.lang.reflect.Field;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 观察：线程池中不同 ThreadLocal 实例 + 不 remove 的释放情况
 */
public class SimpleThreadLocalLeakTest {

    static ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);

    // 用于观察的大对象
    static class Data {
        byte[] bytes = new byte[5 * 1024 * 1024]; // 5MB
        String id;
        Data(String id) { this.id = id; }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("Data(" + id + ") 被GC回收了");
        }
    }

    public static void main(String[] args) throws Exception {

        // 任务1：用自己的 ThreadLocal
        pool.execute(() -> {
            ThreadLocal<Data> tl = new ThreadLocal<>();
            tl.set(new Data("A"));
            System.out.println("任务1完成, tl@" + System.identityHashCode(tl));
            // tl.remove();  // 必须调用！清除 Entry
            // 不 remove
        });

        // 任务2：用自己的 ThreadLocal
        pool.execute(() -> {
            ThreadLocal<Data> tl = new ThreadLocal<>();
            tl.set(new Data("B"));
            System.out.println("任务2完成, tl@" + System.identityHashCode(tl));
            // tl.remove();  // 必须调用！清除 Entry
            // 不 remove
        });

        Thread.sleep(2000);
        System.out.println("\n【GC前 - 检查ThreadLocalMap】");
        printAllThreadsMap("GC前");

        System.out.println("\n【手动触发GC】");
        // 注意：需要持有ThreadLocal的强引用才能观察key是否被回收
        // 所以上面的代码实际上ThreadLocal已经不可达了，GC后key会变null
        System.gc();
        Thread.sleep(2000);

        System.out.println("\n【GC后 - 观察key是否为null】");
        printAllThreadsMap("GC后");
    }


    /**
     * 打印所有线程的ThreadLocalMap状态
     */
    private static void printAllThreadsMap(String msg) throws Exception {
        System.out.println(msg + " 线程池中线程状态:");

        // 获取线程池中的所有线程
        Field workersField = ThreadPoolExecutor.class.getDeclaredField("workers");
        workersField.setAccessible(true);
        java.util.Set<?> workers = (java.util.Set<?>) workersField.get(pool);

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

                if (!(value instanceof SimpleThreadLocalLeakTest.Data)) {
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
