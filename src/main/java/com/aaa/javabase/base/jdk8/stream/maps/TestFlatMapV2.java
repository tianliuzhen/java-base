package com.aaa.javabase.base.jdk8.stream.maps;


import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuzhen.tian
 * @version 1.0 TestFlatMap.java  2020/11/2 17:10
 */
public class TestFlatMapV2 {
    public static void main(String[] args) throws Exception{
        TestFlatMapV2 testFlatMapV2 = new TestFlatMapV2();
        testFlatMapV2.testFlatMap();

    }
    //
    private   void testFlatMap() {

        List<Integer> stringList = Arrays.asList(1,2,3);
        List<String> collect = stringList.stream().flatMap(id -> getListById(id).stream())
                .distinct().filter(StringUtils::isNotBlank).collect(Collectors.toList());

    }
    public  List<String> getListById(Integer id){
        return Arrays.asList("1");
    }



}
