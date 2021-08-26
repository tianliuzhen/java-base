package com.aaa.javabase.jdk8.自定义函数;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.function.Function;

/**
 * @author liuzhen.tian
 * @version 1.0 PsvmMain.java  2021/8/26 21:09
 */
public class PsvmMain {

    public static void main(String[] args) {

        Set<String> converter = converter("a", (a) -> Sets.newHashSet(a + "-1"));

        String converter2 = converter2("a", (a) -> a + "-2");

        Set<String> con1= converter3.apply("a");

        System.out.println();
    }

    public static <T, R> Set<R> converter(T t, SetFunction<T, R> function) {

        return function.apply(t);
    }

    public static <T, R> R converter2(T t, Function<T, R> function) {

        return function.apply(t);
    }

    public static SetFunction<String, String> converter3 = fun -> {

        return Sets.newHashSet(fun + "-3");
    };
}
