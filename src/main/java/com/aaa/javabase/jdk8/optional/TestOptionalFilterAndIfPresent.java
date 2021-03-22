package com.aaa.javabase.jdk8.optional;

import com.aaa.javabase.jdk8.optional.domain.Car;

import java.util.Optional;

/**
 * @author liuzhen.tian
 * @version 1.0 TestOptionalFilter.java  2021/3/22 23:25
 */
public class TestOptionalFilterAndIfPresent {
    public static void main(String[] args) {

        /**
         * 测试  filter
         */
        Optional<Car> optCar = Optional.of(new Car("奥迪"));
        // 和stream 类似
        // filter 方法接受一个谓词作为参数。如果 Optional 对象的值存在，并且它符合谓词的条件filter 方法就返回其值；
        // 否则它就返回一个空的 Optional 对象。
        optCar.filter(car ->
                "奥迪".equals(car.getCarName())).ifPresent(System.out::println);
        System.out.println(optCar);

        /**
         * 测试  ifPresent
         */
        Optional<String> stringOptional = Optional.ofNullable("artisan");
        // 如果存在 这执行打印
        stringOptional.ifPresent(System.out::println);

        Optional<String> stringOptional2 = Optional.ofNullable(null);
        // 如果存在 这执行打印
        stringOptional2.ifPresent(System.out::println);

    }
}
