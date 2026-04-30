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

public class SingleThreadLocalMemoryLeakDemo {

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

        // 1. 第一次set
        ThreadLocal<BigData> tl = new ThreadLocal<>();
        tl.set(new BigData("对象1"));
        printMap("第一次set后");

        // 2. tl置null，GC，这里手动tl置null，有些牵强，实际的场景是方法栈直接执行 等于tl = null
        tl = null;
        System.gc();
        Thread.sleep(1000);
        printMap("GC后（注意key为null的Entry）");

        // 3. 第二次set
        ThreadLocal<BigData> tl2 = new ThreadLocal<>();
        tl2.set(new BigData("对象2"));
        printMap("第二次set后（旧Entry还在）");

        // 4. 正确做法
        System.out.println("\n========== 正确做法 ==========");
        ThreadLocal<BigData> tl3 = new ThreadLocal<>();
        tl3.set(new BigData("对象3"));
        tl3.remove();  // 关键
        tl3 = null;
        System.gc();
        Thread.sleep(1000);
        printMap("remove后GC");
    }

    private static void printMap(String msg) throws Exception {
        Field field = Thread.class.getDeclaredField("threadLocals");
        field.setAccessible(true);
        Object map = field.get(Thread.currentThread());
        if (map == null) {
            System.out.println(msg + ": ThreadLocalMap=null");
            return;
        }

        Field tableField = map.getClass().getDeclaredField("table");
        tableField.setAccessible(true);
        Object[] table = (Object[]) tableField.get(map);

        System.out.println(msg + ":");
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                Field valueField = table[i].getClass().getDeclaredField("value");
                valueField.setAccessible(true);
                Object value = valueField.get(table[i]);

                Field referentField = Class.forName("java.lang.ref.Reference")
                        .getDeclaredField("referent");
                referentField.setAccessible(true);
                Object key = referentField.get(table[i]);

                System.out.println("  Entry[" + i + "]: key=" + key + ", value=" + value);
            }
        }
    }
}
