package com.aaa.javabase.base.堆;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author liuzhen.tian
 * @version 1.0 LocalVariablesTest.java  2024/4/24 21:21
 */
public class LocalVariablesTest {
    public void test1() {
        int a = 10;
        int b = 10;
        int c = a + b;
    }

    public static void test2() {
        int a = 10;
        int b = 10;
        int c = a + b;
    }

    public String test3() {
        long a = 10L;
        Long a1 = 12L;

        double b = 10.00D;
        Double b1 = 12.00D;

        float c = 11.11F;
        Float c1 = 11.12F;

        ArrayList list = new ArrayList<>();
        HashMap map = new HashMap<>();

        return "test3";
    }

    public void test4() {
        int a = 10;
        {
            int b = 1;
            b = a + b;
        }
        int c = 11;
    }

    public void test5() {
        int a = 10;
        System.out.println(a);
    }

}
