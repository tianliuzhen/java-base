package com.aaa.javabase.pattern.creater.factory.simplefactory;

import com.aaa.javabase.pattern.creater.factory.simplefactory.impl.AudiCar;
import com.aaa.javabase.pattern.creater.factory.simplefactory.impl.BenzCar;

/**
 * @author liuzhen.tian
 * @version 1.0 CarDriverFactory.java  2021/11/25 21:08
 */
public class CarDriverFactory {

    /**
     * 根据name返回汽车
     *
     * @param name 汽车name
     * @return Car
     */
    public Car getCarByName(String name) {
        Car car = null;
        switch (name) {
            case "audi":
                car = new AudiCar();
                break;
            case "benz":
                car = new BenzCar();
                break;
            default:
                break;

        }
        return car;
    }
}
