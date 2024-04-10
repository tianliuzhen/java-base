package com.aaa.javabase.base.jclass;

/**
 * @author liuzhen.tian
 * @version 1.0 Main.java  2024/4/9 22:17
 */
public class Main {
    public static void main(String[] args) {
        // AppleServiceImpl appleService = new AppleServiceImpl();
        // OrangeSellImpl orangeSell = new OrangeSellImpl();
        // int num = OrangeSellImpl.num;

        // OrangeSellImpl[] arr = new OrangeSellImpl[10];
        // arr[0] = new OrangeSellImpl();

        // int num2 = OrangeSellImpl.num;

        try {
            ClassLoader.getSystemClassLoader().loadClass("com.aaa.javabase.base.jclass.OrangeSellImpl");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
