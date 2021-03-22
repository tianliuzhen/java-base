package com.aaa.javabase.jdk8.optional;

import com.aaa.javabase.jdk8.optional.domain.Car;

import java.util.Optional;

/**
 * @author liuzhen.tian
 * @version 1.0 TestOptionGet.java  2021/3/22 23:13
 */
public class TestOptionGet {
    public static void main(String[] args) {

        /**
         * get() 是这些方法中最简单但又最不安全的方法。如果变量存在，它直接返回封装的变量
         * 值，否则就抛出一个 NoSuchElementException 异常。
         * 所以，除非你非常确定 Optional变量一定包含值，否则使用这个方法是个相当糟糕的主意。
         * 此外，这种方式即便相对于嵌套式的 null检查，也并未体现出多大的改进。
         */
        Optional<Car> optionalPerson = Optional.ofNullable(null);

        System.out.println(optionalPerson.get());
    }
}
