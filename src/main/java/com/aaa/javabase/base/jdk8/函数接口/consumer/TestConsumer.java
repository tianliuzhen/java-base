package com.aaa.javabase.base.jdk8.函数接口.consumer;

import java.util.function.Consumer;

/**
 *
 *
 * @author liuzhen.tian
 * @version 1.0 TestConsumer.java  2021/3/19 22:19
 */
public class TestConsumer {
    public static void main(String[] args) {

        /**
         *  这个接口，接收一个泛型的参数T，然后调用accept，对这个参数做一系列的操作，没有返回值；
         *  看到这里，是不是很懵，下面用一个简单的小例子
         *  其实等价与  y=f(x)
         *  用途： 因为没有出参，常用于打印、发送短信等消费动作
         */
        Consumer<Integer> consumer = x -> {
            int a = x + 2;
            System.out.println(a + "_");// 12_
        };
        consumer.accept(10);
    }
}
