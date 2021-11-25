package com.aaa.javabase.pattern.creater.factory.factorymethod;

import com.aaa.javabase.pattern.creater.factory.factorymethod.factory.AudiCarMakeFactory;
import com.aaa.javabase.pattern.creater.factory.factorymethod.factory.BenzCarMakeFactory;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2021/11/25 21:20
 */
public class TestMain {
    public static void main(String[] args) {
        // 创建奥迪制造工厂
        AudiCarMakeFactory audiCarDriverFactory = new AudiCarMakeFactory();
        // 开奥迪
        audiCarDriverFactory.getCar().drive();

        System.out.println("----------------");

        // 创建奔驰制造工厂
        BenzCarMakeFactory benzCarDriverFactory = new BenzCarMakeFactory();
        // 开奔驰
        benzCarDriverFactory.getCar().drive();
    }
}
