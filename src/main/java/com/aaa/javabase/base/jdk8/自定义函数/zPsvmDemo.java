package com.aaa.javabase.base.jdk8.自定义函数;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Map;
import java.util.Set;

/**
 * @author liuzhen.tian
 * @version 1.0 PsvmMain.java  2021/8/26 21:09
 */
public class zPsvmDemo {

    public static void main(String[] args) {
        /**
         * 1、Function
         */
        // 直接调用函数
        Set<String> function = function("a", (a) -> {
            return Sets.newHashSet(a + "-1");
        });

        // 函数写进方法里面
        Set<String> function3 = toFunction.apply("a");

        /**
         * 2、Consumer
         */
        // 直接调用函数
        consumer(1, 2, (a, b) -> {
            System.out.println("(a + b ) = " + (a + b));
        });

        // 函数写进方法里面
        toConsumer.accept("a", "b");

        /**
         * 3、Supplier
         */
        Map hashMap = toSupplier(() -> {
            Map<Object, Object> map = Maps.newHashMap();
            map.put("a", "b");
            return map;
        });


        System.out.println();
    }

    /**
     * @param t        入参
     * @param function 函数
     * @return Set<R>
     */
    public static Set<String> function(String t, SetFunction<String, String> function) {

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
    public static void consumer(Integer t, Integer u, MyBiConsumer<Integer, Integer> consumer) {
        consumer.accept(t, u);
    }

    /**
     * @param supplier 函数
     * @return T
     */
    public static Map toSupplier(MySupplier<Map> supplier) {

        return supplier.get();
    }

}
