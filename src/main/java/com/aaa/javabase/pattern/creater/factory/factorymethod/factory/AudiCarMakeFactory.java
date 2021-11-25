package com.aaa.javabase.pattern.creater.factory.factorymethod.factory;

import com.aaa.javabase.pattern.creater.factory.factorymethod.Car;
import com.aaa.javabase.pattern.creater.factory.factorymethod.CarMakeFactory;
import com.aaa.javabase.pattern.creater.factory.factorymethod.product.AudiCar;

/**
 * 具体工厂：奥迪制造工厂
 *
 * @author liuzhen.tian
 * @version 1.0 AudiCarDriverFactory.java  2021/11/25 21:17
 */
public class AudiCarMakeFactory implements CarMakeFactory {
    @Override
    public Car getCar() {
        return new AudiCar();
    }
}
