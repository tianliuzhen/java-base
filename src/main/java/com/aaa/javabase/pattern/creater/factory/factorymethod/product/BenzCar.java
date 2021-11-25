package com.aaa.javabase.pattern.creater.factory.factorymethod.product;


import com.aaa.javabase.pattern.creater.factory.factorymethod.Car;

/**
 * 具体产品：奔驰汽车
 *
 * @author liuzhen.tian
 * @version 1.0 BenzCar.java  2021/11/25 21:06
 */
public class BenzCar extends Car {
    @Override
    public void drive() {
        System.out.println("开奔驰...");
    }
}
