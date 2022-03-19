package com.aaa.javabase.multithreading.并发执行.CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author liuzhen.tian
 * @version 1.0 AtomicTest.java  2021/3/1 22:51
 */
public class AtomicStampedReferenceTest {

    /**
     * initialRef    初始值
     * initialStamp  初始标记状态
     */
    static AtomicStampedReference<Integer> stampRef
                            = new AtomicStampedReference(10, 1);
    public static void main(String[] args) {

        //模拟 ABA ，在线程执行期间，另外一个线程，修改数据。
        new Thread(() -> {
            int stamp = stampRef.getStamp();
            System.out.println(Thread.currentThread().getName()
                    + " 第1次版本号： " + stamp);
            stampRef.compareAndSet(10, 11,stampRef.getStamp(),stampRef.getStamp()+1);
            System.out.println(Thread.currentThread().getName()
                    + " 第2次版本号： " + stampRef.getStamp());
            stampRef.compareAndSet(11, 10,stampRef.getStamp(),stampRef.getStamp()+1);
            System.out.println(Thread.currentThread().getName()
                    + " 第3次版本号： " + stampRef.getStamp());
        },"张三").start();

        // 真正执行的线程
        new Thread(() -> {
            try {
                int stamp = stampRef.getStamp();
                System.out.println(Thread.currentThread().getName()
                        + " 第1次版本号： " + stamp);
                // 增加该线程的执行时间，等待上面线程先执行完
                TimeUnit.SECONDS.sleep(2);
                // 这里 先比对  expectedStamp  是不是跟初始值  initialStamp ，一样表示执行成功。
                boolean isSuccess =stampRef.compareAndSet(10, 12,
                        1,stampRef.getStamp()+1);
                System.out.println(Thread.currentThread().getName()
                        + " 修改是否成功： "+ isSuccess+" 当前版本 ：" + stampRef.getStamp());
                System.out.println(Thread.currentThread().getName()
                        + " 当前实际值： " + stampRef.getReference());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"李四").start();
    }
}

