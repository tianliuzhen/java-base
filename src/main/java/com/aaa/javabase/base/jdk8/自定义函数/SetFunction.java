package com.aaa.javabase.base.jdk8.自定义函数;

import java.util.Set;

/**
 * 自定义函数接口 SetFunction：一个入参，一个返回值
 *
 * @author liuzhen.tian
 * @version 1.0 SetFuction.java  2021/8/26 21:06
 */
@FunctionalInterface
public interface SetFunction<T, R> {

    /**
     * @param t 入参
     * @return Set<R>
     */
    Set<R> apply(T t);
}
