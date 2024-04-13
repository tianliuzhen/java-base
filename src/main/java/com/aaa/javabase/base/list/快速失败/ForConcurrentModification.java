package com.aaa.javabase.base.list.快速失败;

/**
 * fori时，遇到 add remove clear 不会触发 ConcurrentModificationException ，但是会使下标错误
 * 而增强for循环和迭代器，会触发 ConcurrentModificationException
 *
 * @author liuzhen.tian
 * @version 1.0 ForIConcurrentModification.java  2024/4/13 9:07
 */
import java.util.ArrayList;
import java.util.List;

public class ForConcurrentModification {
    public static void main(String[] args) throws InterruptedException {
        final List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        // 线程1：遍历列表并打印元素
        Thread thread1 = new Thread(() -> {
            for (Integer integer : list) {
                System.out.println(integer);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
            }
        });

        // 线程2：修改列表（移除元素）
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

            }
            list.clear();
        });

        thread1.start();
        thread2.start();

        // 等待两个线程完成
        thread1.join();
        thread2.join();
    }
}
