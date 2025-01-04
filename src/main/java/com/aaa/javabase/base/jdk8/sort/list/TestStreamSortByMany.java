package com.aaa.javabase.base.jdk8.sort.list;

import com.aaa.javabase.base.jdk8.sort.MonthDataDto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuzhen.tian
 * @version 1.0 TestStreamSortByMany.java  2024/1/3 20:58
 */
public class TestStreamSortByMany {
    public static void main(String[] args) {
        List<MonthDataDto> list = new ArrayList<>();
        list.add(new MonthDataDto("d", "1", "937400", "1200.11", "", ""));
        list.add(new MonthDataDto("a", "1", "937400", "1200.11", "", ""));
        list.add(new MonthDataDto("c", "1", "937400", "1200.11", "", ""));
        list.add(new MonthDataDto("c1", "3", "937400", "1200.11", "", ""));
        list.add(new MonthDataDto("c2", "3", "937400", "1200.11", "", ""));
        list.add(new MonthDataDto("b", "2", "937400", "1200.11", "", ""));

        List<MonthDataDto> collect = list.stream().sorted(
                Comparator.comparing(MonthDataDto::getMoneyMonth)
                        .thenComparing(MonthDataDto::getStoreName))
                .collect(Collectors.toList());
        System.out.println();
    }
}
