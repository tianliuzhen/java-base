package com.aaa.javabase.base.jdk8.自定义函数;

/**
 * 自定义函数 MyBiConsumer：俩个入参，无返回值
 *
 * @author liuzhen.tian
 * @version 1.0 MyConsumer.java  2021/8/26 21:39
 */

@FunctionalInterface
public interface MyBiConsumer<T, U> {

    void accept(T t, U u);
}
