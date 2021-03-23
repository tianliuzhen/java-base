package com.aaa.javabase.jdk8.optional;

import com.aaa.javabase.jdk8.函数接口.function.Dto1;

import java.util.Optional;

/**
 * Optional相当于一个容器,可以一个接收的对象value,用泛型指定类型,并且该对象有可能为空.
 * Supplier是一个函数式接口,只定义了一个返回值为T类型的get()方法,用于产生(提供)对象,可以使用lambda表达式定义实现
 * orElse(T other) 不论容器是否为空,只要调用该方法, 则对象other一定存在，但是只有当容器为空时,才会返回值。
 * orElseGet(Supplier<? extends T> supplier) 只有当容器为空时,才调用supplier.get()方法产生对象
 *
 * @author liuzhen.tian
 * @version 1.0 TestOptional.java  2020/12/8 15:37
 */
public class TestOrElseAndOrElseGet {
    public static void main(String[] args) {
        /**
         * orElse ：(总会执行)
         * 一般与of或者ofNullable配合使用，但需要注意的是，orElse不管前面的of或者ofNullable是否满足条件，都会进行执行，
         *
         *  orElseGet ：(为空才会执行)
         *  如果要走类似 if..else..的逻辑，需要使用orElseGet来替代orElse。
         *
         *  最重要一点：
         *  其实比较坑，orElse 虽然总会执行，但是只有当容器为空时，才会返回值。
         */

        testOrElse();
        // testOrElseGet();
    }

    private static void testOrElse() {

        // case1: 容器为空会执行，并返回值
        String orElse = Optional.ofNullable(null).orElse(getDefaultValue("orElse")).toString();
        System.out.println(orElse + " _1");

        // case2: 容器不为空会执行，但不会返回值
        String orElse2 = Optional.ofNullable("我有值").orElse(getDefaultValue("orElse2"));
        System.out.println(orElse2 + " _2");

    }

    public static String getDefaultValue(String name) {
        System.out.println(name + "：调用了 getDefaultValue ");
        return "返回值是：" + name;
    }

    private static void testOrElseGet() {

        // case1: 容器为空执行
        Object orElseGet = Optional.ofNullable(null).orElseGet(() -> {
            Dto1 dto1 = new Dto1("456");
            System.out.println(456);
            return dto1;
        });
        System.out.println(orElseGet);

        // case2: 容器不为空不执行
        Object orElseGet2 = Optional.of(new Dto1("我有值")).orElseGet(() -> {
            Dto1 dto2 = new Dto1("789");
            System.out.println(789);
            return dto2;
        });
        System.out.println(orElseGet2);
    }
}
