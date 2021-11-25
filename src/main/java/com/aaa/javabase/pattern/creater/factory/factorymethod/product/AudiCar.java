package com.aaa.javabase.pattern.creater.factory.factorymethod.product;


import com.aaa.javabase.pattern.creater.factory.factorymethod.Car;

/**
 * 具体产品：奥迪汽车
 *
 * @author liuzhen.tian
 * @version 1.0 AudiCar.java  2021/11/25 21:05
 */
public class AudiCar extends Car {
    @Override
    public void drive() {
        System.out.println("开奥迪...");
    }
}
