package com.aaa.javabase.pattern.creater.factory.simplefactory;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2021/11/25 21:11
 */
public class TestMain {
    public static void main(String[] args) {
        // 从工厂里获取对象
        CarDriverFactory carDriverFactory = new CarDriverFactory();
        // 开奥迪
        carDriverFactory.getCarByName("audi").drive();
        // 开奔驰
        carDriverFactory.getCarByName("benz").drive();
    }
}
