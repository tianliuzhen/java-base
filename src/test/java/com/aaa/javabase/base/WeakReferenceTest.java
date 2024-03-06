package com.aaa.javabase.base;

import com.aaa.javabase.h2.Model.User;
import org.junit.jupiter.api.Test;

import java.lang.ref.WeakReference;

/**
 * @author liuzhen.tian
 * @version 1.0 WeakReferenceTest.java  2024/3/2 10:56
 */
public class WeakReferenceTest {
    public static ThreadLocal<String> referent = new ThreadLocal<>();
    /**
     * 测试全局变量，弱引用时对象被回收情况
     */
    @Test
    public void test1() {
        String s = referent.get();
        WeakReference weakReference = new WeakReference(referent);
        //   Object o = weakRerference.get(); 如果有值来接收，那么  System.gc(); 依然不会被回收
        weakReference.get();
        referent = null;
        System.gc();
        weakReference.get();
    }


    /**
     没有手动赋值时 referent = null, GC后也不会回收
     */
    @Test
    public void test1_1() {
        WeakReference weakReference = new WeakReference(referent);
        weakReference.get();
        System.gc();
        weakReference.get();
    }

    /**
     weakReference.get() 有新的强引用, GC后也不会回收
     */
    @Test
    public void test1_2() {
        WeakReference weakReference = new WeakReference(referent);
        Object o = weakReference.get();
        referent = null;
        System.gc();
        weakReference.get();
    }

    /**
     * 测试局部变量，弱引用时对象被回收情况
     */
    @Test
    public void test2() {
        ThreadLocal<String> userInfo = new ThreadLocal<>();
        userInfo.get();
        userInfo.set("123");
        WeakReference weakRerference = new WeakReference(userInfo);
        //   Object o = weakRerference.get(); 如果有值来接收，那么  System.gc(); 依然不会被回收
        weakRerference.get();
        userInfo = null;
        System.gc();
        weakRerference.get();
    }

    public static void main(String[] args) {
        User user = new User();
        user.setName("aaa");

        extracted(user);

        System.out.println();
    }

    private static void extracted(User user) {
        User user1 = user;
        user1.setName("11");

    }
}
