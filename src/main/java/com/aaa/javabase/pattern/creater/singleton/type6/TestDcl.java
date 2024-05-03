package com.aaa.javabase.pattern.creater.singleton.type6;

import com.aaa.javabase.util.FutureUtil;
import com.aaa.javabase.util.ThreadPoolUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 TestDcl.java  2024/5/1 9:09
 */
public class TestDcl {
    public static void main(String[] args) throws InterruptedException {
        List<Runnable> list = new ArrayList<>();
        List<Integer> objects = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 100; i++) {
            list.add(() -> {
                Singleton6 instance = Singleton6.getInstance();
                if (null != instance) {
                    System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName()
                            + "-" + instance.getName() + "-" + instance.hashCode());
                    objects.add(instance.hashCode());
                }
            });
        }
        FutureUtil.doRunAsync(list, ThreadPoolUtil.common_pool);
        HashSet<Integer> integers = new HashSet<>(objects);
        System.out.println();
    }
}
