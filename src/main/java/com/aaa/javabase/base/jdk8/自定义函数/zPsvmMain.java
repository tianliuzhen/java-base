package com.aaa.javabase.base.jdk8.自定义函数;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Map;
import java.util.Set;

/**
 * @author liuzhen.tian
 * @version 1.0 PsvmMain.java  2021/8/26 21:09
 */
public class zPsvmMain {

    public static void main(String[] args) {
        /**
         * Function
         */
        Set<String> function = function("a", (a) -> Sets.newHashSet(a + "-1"));


        Set<String> function3 = toFunction.apply("a");

        /**
         * Consumer
         */
        toConsumer.accept("a", "b");

        converterCon("a", "b", (a, b) -> {
            System.out.println(a + b + "-");
        });


        /**
         * Supplier
         */
        Map hashMap = toSupplier(() -> {
            Map<Object, Object> map = Maps.newHashMap();
            map.put("a", "b");
            return (Map) map;
        });


        System.out.println();
    }

    /**
     * @param t        入参
     * @param function 函数
     * @return Set<R>
     */
    public static <T, R> Set<R> function(T t, SetFunction<T, R> function) {

        return function.apply(t);
    }


    public static SetFunction<String, String> toFunction = fun -> {

        return Sets.newHashSet(fun + "-3");
    };

    public static MyBiConsumer<String, String> toConsumer = (parm1, parm2) -> {
        System.out.println(parm1 + "-" + parm2);
    };

    /**
     * @param t        入参 t
     * @param u        入参 u
     * @param consumer 函数
     */
    public static <T, U> void converterCon(T t, U u, MyBiConsumer<T, U> consumer) {
        consumer.accept(t, u);
    }

    /**
     * @param supplier 函数
     * @return T
     */
    public static <T> T toSupplier(MySupplier<T> supplier) {

        return supplier.get();
    }

}
