package com.aaa.javabase.base.jdk8.函数接口.predicate;

import java.util.function.Predicate;

/**
 * @author liuzhen.tian
 * @version 1.0 TestPredicate.java  2021/3/19 22:44
 */
public class TestPredicate {
    public static void main(String[] args) {
        /**
         * Predicate<T>
         *
         *     T：入参类型；出参类型是Boolean
         *
         *     调用方法：boolean test(T t);
         *
         *     定义函数示例：Predicate<Integer> predicate = p -> p % 2 == 0;    // 判断是否、是不是偶数
         *
         *     调用函数示例：predicate.test(100);    // 运行结果true
         */
        Predicate<Integer> predicate = p -> p % 2 == 0;
        boolean test = predicate.test(100);
        System.out.println(test);
    }
}
