package com.aaa.javabase.base;

/**
 * @author liuzhen.tian
 * @version 1.0 TestBase.java  2020/12/21 16:43
 */
public class TestBase {
    public static void main(String[] args) {
        Integer a = new Integer(96354);
        String b = "abc";
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(a.hashCode()==b.hashCode());
        System.out.println(a.equals(b));

    }
}
