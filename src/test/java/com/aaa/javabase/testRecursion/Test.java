package com.aaa.javabase.testRecursion;

/**
 * @author liuzhen.tian
 * @version 1.0 Test.java  2022/6/21 22:08
 */
public class Test {
    public static void main(String[] args) {
        String[] json = "L0L0".split("(?=L)");
        System.out.println(json.toString());
    }
}
