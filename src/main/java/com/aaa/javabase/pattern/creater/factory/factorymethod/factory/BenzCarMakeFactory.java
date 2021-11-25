package com.aaa.javabase.pattern.creater.factory.factorymethod.factory;

import com.aaa.javabase.pattern.creater.factory.factorymethod.Car;
import com.aaa.javabase.pattern.creater.factory.factorymethod.CarMakeFactory;
import com.aaa.javabase.pattern.creater.factory.factorymethod.product.BenzCar;

/**
 * 具体工厂：奔驰制造工厂
 *
 * @author liuzhen.tian
 * @version 1.0 BenzCarDriverFactory.java  2021/11/25 21:18
 */
public class BenzCarMakeFactory implements CarMakeFactory {
    @Override
    public Car getCar() {
        return new BenzCar();
    }
}
