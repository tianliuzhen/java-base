package com.aaa.javabase.jdk8.sort;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuzhen.tian
 * @version 1.0 TestListSort.java  2020/12/9 16:17
 */
public class TestListSort {
    public static void main(String[] args) {
        /**
         * 针对 String 类型字段排序的话，可能导致 排序错乱
         */

        // 排序错误
        List<MonthDataDto> list = new ArrayList<>();
        list.add(new MonthDataDto("Malling Fashion","86863.0","937400", "1200.11","",""));
        list.add(new MonthDataDto("Malling Fashion2","96863.0","937400", "1200.11","",""));
        list.add(new MonthDataDto("Malling Fashion3","36863.0","937400", "1200.11","",""));
        list = list.stream().sorted(Comparator.comparing(MonthDataDto::getMoneyMonth)).collect(Collectors.toList());
        System.out.println(list);

        // 排序正常
        List<MonthDataDto> list2 = new ArrayList<>();
        list2.add(new MonthDataDto("937400", "1200.11"));
        list2.add(new MonthDataDto("137400", "1200.11"));
        list2.add(new MonthDataDto("737400", "1200.11"));
        list2 = list2.stream().sorted(Comparator.comparing(MonthDataDto::getMoneyMonth)).collect(Collectors.toList());
        System.out.println(list2);

        /**
         * 解决方案，尽量把排序字段转成 整型或者浮点类型
         */

    }
}
