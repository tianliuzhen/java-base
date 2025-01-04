package com.aaa.javabase.base.jdk8.函数接口;

import com.aaa.javabase.base.jdk8.函数接口.function.Dto1;
import com.aaa.javabase.base.jdk8.函数接口.function.Dto2;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author liuzhen.tian
 * @version 1.0 ProcessServiece.java  2021/3/19 21:43
 */
public class MixService {

    /**
     * @param model
     * @param stringFunction
     * @param mapConsumer
     * @param <T>
     * @param <M>
     * @return
     */
    public static <T, M> String start(M model, Function<M, String> stringFunction, Consumer<Map> mapConsumer) {

        //1. Function 处理
        Dto2 dto2 = new Dto2();
        String apply = stringFunction.apply(model);
        System.out.println(apply);

        //2. Consumer 处理
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("cc", "33");
        mapConsumer.accept(hashMap);

        System.out.println(hashMap);

        return dto2.getName2();
    }

    public static void main(String[] args) {
        Dto1 dto1 = new Dto1();
        dto1.setName1("tom");

        start(dto1,
                e -> e.getName1() + "-"+"110",
                x -> {
                    x.put("aa", "11");
                    x.put("bb", "22");
                });


    }


}
