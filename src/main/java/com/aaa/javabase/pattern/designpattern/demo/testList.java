package com.aaa.javabase.pattern.designpattern.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/16
 */
public class testList {
    public static void main(String[] args) {
        List<Integer> strings = new ArrayList<>();
        strings.add(1);
        strings.add(2);
        strings.add(5);
        strings.add(5);
        strings.add(3);
        strings.add(3);
        //不重复的数字
        List<Integer> strings2 = new ArrayList<>();
        //重复的数字
        List<Integer> strings3 = new ArrayList<>();
        for (Integer string : strings) {
            if(!strings2.contains(string)){
                strings2.add(string);
            }else {
                strings3.add(string);
            }
        }
        System.out.println(strings2);
        System.out.println(strings3);
    }
}
