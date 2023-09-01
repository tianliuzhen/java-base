package com.aaa.javabase.base.objectOriented.extendsAndSuper;

import java.util.*;
import java.util.stream.Collectors;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/1/14
 */
class test extends B {
    int n = super.num;

    public test() {
    }

    public test(Object result) {
        // super 关键字 执行父类
        //默认是 无参构造
        super(result);
    }

    public static void main(String[] args) {
        //测试有参构造
        //  new test(new Object());
        // 测试无参构造
        test1();
    }

    public static void test1() {
        List<Stu> list = new ArrayList<>();
        list.add(new Stu("tom", 23));
        list.add(new Stu("tom", 13));

        Map<String, Integer> collect1 = list.stream().collect(Collectors.toMap(Stu::getName, Stu::getAge));
        Map<String, Integer> collect2 = list.stream().collect(Collectors.toMap(Stu::getName, Stu::getAge,
                (o, n) -> o, HashMap::new));
        List<String> collect3 = list.stream().map(Stu::getName).collect(Collectors.toList());


        //方法一,先倒序 取第一个
        List<Stu> collect = list.stream().sorted(Comparator.comparing(Stu::getAge).reversed()).collect(Collectors.toList());
        System.out.println(collect.get(0).toString());
        //方法二,使用函数
        Optional<Stu> maxMaterial = list.stream().max(Comparator.comparingInt(Stu::getAge));
        System.out.println(maxMaterial);

    }


}
