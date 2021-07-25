package com.aaa.javabase.jdk8.sort.list;

/**
 * @author liuzhen.tian
 * @version 1.0 Psvm.java  2021/7/24 0:23
 */
public class Psvm {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println("i = " + i);
            for (int i1 = 0; i1 < 5; i1++) {
                if (i1 == 1) {
                    return;
                }
                System.out.println("i1 = " + i1);
            }
        }
    }
}
