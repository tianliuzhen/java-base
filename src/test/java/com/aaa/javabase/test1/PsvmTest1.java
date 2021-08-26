package com.aaa.javabase.test1;

import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 PsvmTest1.java  2021/8/26 20:53
 */
public class PsvmTest1 {
    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(1, 4, 3, 4, 5);

        for (int i = list.size() - 1; i >= 0; i--) {
            if(list.get(i)== 4){
                list.remove(i);
            }
        }
        System.out.println(list);
    }
}
