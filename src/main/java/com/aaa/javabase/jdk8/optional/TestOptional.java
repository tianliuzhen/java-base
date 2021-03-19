package com.aaa.javabase.jdk8.optional;

import com.aaa.javabase.jdk8.函数接口.function.Dto1;

import java.util.Optional;

/**
 * Optional相当于一个容器,可以一个接收的对象value,用泛型指定类型,并且该对象有可能为空.
 * Supplier是一个函数式接口,只定义了一个返回值为T类型的get()方法,用于产生(提供)对象,可以使用lambda表达式定义实现
 * orElse(T other) 不论容器是否为空,只要调用该方法, 则对象other一定存在
 * orElseGet(Supplier<? extends T> supplier) 只有当容器为空时,才调用supplier.get()方法产生对象
 *
 * @author liuzhen.tian
 * @version 1.0 TestOptional.java  2020/12/8 15:37
 */
public class TestOptional {
    public static void main(String[] args) {
        /**
         * 总的来说。感觉
         *  orElse ：总会执行
         *  orElseGet ：为空才会执行
         */

        // testString();
        testDto();
    }
    private static void testDto() {
        Optional<Dto1> opt = Optional.ofNullable(null);
        opt.orElse( new Dto1("123"));

        System.out.println("---以上为orElse调用,以下为orElseGet调用---");
        opt.orElseGet(() -> {
            Dto1 dto1 = new Dto1("456");
            System.out.println(456);
            return dto1;
        });
    }
    private static void testString() {
        Optional<String> opt = Optional.ofNullable(null);
        opt.orElse( getDefaultValue() );

        System.out.println("---以上为orElse调用,以下为orElseGet调用---");
        opt.orElseGet(() -> getDefaultValue());
    }

    public static String getDefaultValue(){  //远程方法调用
        System.out.println("我被调用了!");
        return "我是默认值";
    }
}
