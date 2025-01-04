package com.aaa.javabase.base.jdk8.自定义函数;

/**
 * 自定义函数 MySupplier：无入参，一个返回值
 *
 * @author liuzhen.tian
 * @version 1.0 MySupplier.java  2021/8/26 22:06
 */

@FunctionalInterface
public interface MySupplier<T> {

    T get();
}
