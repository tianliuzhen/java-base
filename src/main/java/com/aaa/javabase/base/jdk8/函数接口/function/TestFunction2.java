package com.aaa.javabase.base.jdk8.函数接口.function;

import java.util.function.Function;

/**
 * @author liuzhen.tian
 * @version 1.0 Test.java  2020/8/8 16:44
 */
public class TestFunction2 {
    public static void main(String[] args) {
        Function<Integer, Integer> test1 = i -> i + 1;
        Function<Integer, Integer> test2 = i -> i * 2;

        /**
         * case1 . test1.apply(test2.apply(8))
         *         先计算括号内，再计算括号外 =  8 *2 +1 =17
         */
        System.out.println(test1.apply(test2.apply(8)));


        /**
         * case2 .  andThen  参数 把计算函数的顺序颠倒
         *
         */
        System.out.println(test1.andThen(test2).apply(8));

        /**
         * case3 .  compose 的相反数=》  andThen
         *
         */
        System.out.println(test1.compose(test2).apply(8));

    }
}
