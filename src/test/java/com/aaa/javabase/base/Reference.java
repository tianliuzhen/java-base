package com.aaa.javabase.base;

import org.junit.jupiter.api.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Java 中一共有 4 种类型的引用 : StrongReference、 SoftReference、 WeakReference 以及 PhantomReference (传说中的幽灵引用 呵呵),
 * 这 4 种类型的引用与 GC 有着密切的关系,  让我们逐一来看它们的定义和使用场景 :
 *
 * @author liuzhen.tian
 * @version 1.0 Reference.java  2022/2/19 20:24
 */
public class Reference {

    public static void main(String[] args) {
        softReference();
    }


    /**
     * 1、 Strong Reference 【强引用】
     * StrongReference 是 Java 的默认引用实现,  它会尽可能长时间的存活于 JVM 内， 当没有任何对象指向它时 GC 执行后将会被回收
     */
    @Test
    public void strongReference() {
        Object referent = new Object();
        /**
         * 通过赋值创建 StrongReference
         */
        Object strongReference = referent;

        assertSame(referent, strongReference);

        referent = null;
        System.gc();
        /**
         * StrongReference 在 GC 后不会被回收
         */
        assertNotNull(strongReference);
    }


    /**
     * 2、SoftReference 【软引用】
     * java.lang.ref.SoftReference 于 WeakReference 的特性基本一致，
     * 最大的区别在于 SoftReference 会尽可能长的保留引用直到 JVM 内存不足时才会被回收(虚拟机保证),
     * 这一特性使得 SoftReference 非常适合缓存应用
     * <p>
     * 诸位可能要问, 一个永远返回 null 的 reference 要来何用,
     * 请注意构造 PhantomReference 时的第二个参数 ReferenceQueue(事实上 WeakReference & SoftReference 也可以有这个参数)，
     * PhantomReference 唯一的用处就是跟踪 referent  何时被 enqueue 到 ReferenceQueue 中.
     */
    @Test
    public static void softReference() {
        // 1、定义大小为10M
        SoftReference<Byte[]> softRerference = new SoftReference(new Byte[1024 * 1024 * 10]);

        // 触发一次gc
        System.gc();

        // 这次打印还有数据
        System.out.println(softRerference.get());

        // 2、设置 vm option 参数：【-Xms100M -Xmx10M】

        // 3、再次创建一个10m的数组对象，内存满了。
        /**
         * todo 这里不知道内存是怎么分配的，回收应该是有阈值的，这里我设置总内存为100m，前后创建俩个 10m 数组会达到阈值再次触发gc
         */
        Byte[] bytes2 = new Byte[1024 * 1024 * 10];

        /**
         *  soft references 只有在 jvm OutOfMemory 之前才会被回收, 所以它非常适合缓存应用
         */
        // 再次打印就没了
        System.out.println(softRerference.get());
    }

    /**
     * 3、 WeakReference 【虚引用】
     * <p>
     * WeakReference， 顾名思义,  是一个虚引用,
     * 当所引用的对象在 JVM 内不再有强引用时,GC 后 weak reference 将会被自动回收
     * <p>
     * 场景：WeakReference & WeakHashMap
     */
    @Test
    public void weakReference() {
        Object referent = new Object();
        WeakReference weakRerference = new WeakReference(referent);
        assertSame(referent, weakRerference.get());
        referent = null;
        System.gc();
        /**
         * 一旦没有指向 referent 的强引用, weak reference 在 GC 后会被自动回收
         */
        assertNull(weakRerference.get());
    }

    /**
     * WeakHashMap 使用 WeakReference 作为 key， 一旦没有指向 key 的强引用, WeakHashMap 在 GC 后将自动删除相关的 entry
     */
    @Test
    public void weakHashMap() throws InterruptedException {
        Map weakHashMap = new WeakHashMap();
        Object key = new Object();
        Object value = new Object();
        weakHashMap.put(key, value);

        assertTrue(weakHashMap.containsValue(value));

        key = null;
        System.gc();

        /**
         * 等待无效 entries 进入 ReferenceQueue 以便下一次调用 getTable 时被清理
         */
        Thread.sleep(1000);

        /**
         * 一旦没有指向 key 的强引用, WeakHashMap 在 GC 后将自动删除相关的 entry
         */
        assertFalse(weakHashMap.containsValue(value));
    }


    /**
     * 4、PhantomReference 【幽灵引用】
     * 作为本文主角， Phantom Reference(幽灵引用) 与 WeakReference 和 SoftReference 有很大的不同,
     * 因为它的 get() 方法永远返回 null, 这也正是它名字的由来
     */
    @Test
    public void phantomReferenceAlwaysNull() {
        Object referent = new Object();
        ReferenceQueue referenceQueue = new ReferenceQueue();
        PhantomReference phantomReference = new PhantomReference(referent, referenceQueue);

        /**
         * phantom reference 的 get 方法永远返回 null
         */
        assertNull(phantomReference.get());
    }

    /**
     * 当一个 WeakReference 开始返回 null 时， 它所指向的对象已经准备被回收， 这时可以做一些合适的清理工作.
     * 将一个 ReferenceQueue 传给一个 Reference 的构造函数， 当对象被回收时， 虚拟机会自动将这个对象插入到 ReferenceQueue 中，
     * WeakHashMap 就是利用 ReferenceQueue 来清除 key 已经没有强引用的 entries.
     */
    @Test
    public void referenceQueue() throws InterruptedException {
        Object referent = new Object();
        ReferenceQueue referenceQueue = new ReferenceQueue();
        WeakReference weakReference = new WeakReference(referent, referenceQueue);
        assertFalse(weakReference.isEnqueued());
    }
}
