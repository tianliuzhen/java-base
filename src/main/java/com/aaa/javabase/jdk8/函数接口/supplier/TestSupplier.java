package com.aaa.javabase.jdk8.函数接口.supplier;

import java.util.function.Supplier;

/**
 * @author liuzhen.tian
 * @version 1.0 TestSupplier.java  2021/3/19 22:42
 */
public class TestSupplier {
    public static void main(String[] args) {

        /**
         * Supplier<T>
         *     T：出参类型；没有入参
         *
         *     调用方法：T get();
         *
         *     定义函数示例：Supplier<Integer> supplier= () -> 100;    // 常用于业务“有条件运行”时，符合条件再调用获取结果的应用场景；运行结果须提前定义，但不运行。
         *
         *     调用函数示例：supplier.get();
         */
        Supplier<Integer> supplier= () -> 100;

        System.out.println(supplier);
    }
}
