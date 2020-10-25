package com.aaa.javabase.multithreading.threadlocal.inheritableThreadLocal;

import java.util.HashMap;

/**
 * hreadLocal和InheritableThreadLocal之间的区别可以结合源码分析一下（见下一小节）。
 * 前面的分析听起来如果觉得抽象的话，可以自己写几个类推敲一下，
 * 假如线程其实叫ThrowableThread，
 * 而线程本地变量叫ThrowableThreadLocal，那么它们之间的关系如下：
 * @author liuzhen.tian
 * @version 1.0 Actor.java  2020/10/25 23:34
 */
public class Actor {

    static ThrowableThreadLocal THREAD_LOCAL = new ThrowableThreadLocal();

    public static void main(String[] args) throws Exception {
        ThrowableThread throwableThread = new ThrowableThread() {

            @Override
            public void run() {
                methodFrame1();
            }
        };
        throwableThread.start();
    }

    private static void methodFrame1() {
        THREAD_LOCAL.set("throwable");
        methodFrame2();
    }

    private static void methodFrame2() {
        System.out.println(THREAD_LOCAL.get());
    }

    /**
     * 这个类暂且认为是java.lang.Thread
     */
    private static class ThrowableThread implements Runnable {

        ThrowableThreadLocal.ThrowableThreadLocalMap threadLocalMap;

        @Override
        public void run() {

        }

        // 这里模拟VM的实现,返回ThrowableThread自身,大家先认为不是返回NULL
        public static ThrowableThread getCurrentThread() {
//            return new ThrowableThread();
            return null;   // <--- 假设这里在VM的实现里面返回的不是NULL而是当前的ThrowableThread
        }

        public void start() {
            run();
        }
    }
    /**
     * 这个类暂且认为是java.lang.ThreadLocal
     */
    private static class ThrowableThreadLocal {

        public ThrowableThreadLocal() {

        }

        public void set(Object value) {
            ThrowableThread currentThread = ThrowableThread.getCurrentThread();
            assert null != currentThread;
            ThrowableThreadLocalMap threadLocalMap = currentThread.threadLocalMap;
            if (null == threadLocalMap) {
                threadLocalMap = currentThread.threadLocalMap = new ThrowableThreadLocalMap();
            }
            threadLocalMap.put(this, value);
        }

        public Object get() {
            ThrowableThread currentThread = ThrowableThread.getCurrentThread();
            assert null != currentThread;
            ThrowableThreadLocalMap threadLocalMap = currentThread.threadLocalMap;
            if (null == threadLocalMap) {
                return null;
            }
            return threadLocalMap.get(this);
        }

        // 这里其实在ThreadLocal中用的是WeakHashMap
        public static class ThrowableThreadLocalMap extends HashMap<ThrowableThreadLocal, Object> {

        }
    }
}
