package com.aaa.javabase.base.jdk8.optional;

import com.aaa.javabase.base.jdk8.optional.domain.Car;

import java.util.Optional;

/**
 * @author liuzhen.tian
 * @version 1.0 TestOrElseThrow.java  2021/3/22 23:15
 */
public class TestOrElseThrow {
    public static void main(String[] args) {
        // 写法一
        Optional<Car> optional = Optional.ofNullable(null);
        optional.orElseThrow( ()-> new RuntimeException("运行时异常"));

        // 写法二
        optional.orElseThrow(RuntimeException::new);
    }
}
