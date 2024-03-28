package com.aaa.javabase.base;

/**
 * @author liuzhen.tian
 * @version 1.0 jClassLib.java  2024/3/24 13:01
 */
public class jClassLib {

    // public static void main(String[] args) {
    //     int i = 0;
    //     i = ++i + i++ ;
    //     System.out.println("i = " + i);
    // }

    // 1 2 2 = 5
    // public static void main(String[] args) {
    //     int i = 0;
    //     i = ++i + ++i + i++;
    //     System.out.println("i = " + i);
    // }

    // 1 2 3 3 = 9
    // 1 2 3 3
    public static void main(String[] args) {
        int i = 0;
        i = ++i + ++i + i++;
        System.out.println("i = " + i);
    }
}
