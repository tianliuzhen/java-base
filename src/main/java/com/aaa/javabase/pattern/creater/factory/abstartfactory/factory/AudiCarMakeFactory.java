package com.aaa.javabase.pattern.creater.factory.abstartfactory.factory;

import com.aaa.javabase.pattern.creater.factory.abstartfactory.CarMakeFactory;
import com.aaa.javabase.pattern.creater.factory.abstartfactory.product.SportsCar;
import com.aaa.javabase.pattern.creater.factory.abstartfactory.product.SuvCar;
import com.aaa.javabase.pattern.creater.factory.abstartfactory.product.audi.AudiSportsCar;
import com.aaa.javabase.pattern.creater.factory.abstartfactory.product.audi.AudiSuvCar;

/**
 * 具体工厂：奥迪制造工厂
 * <p>
 * 可以制造 suv和跑车
 *
 * @author liuzhen.tian
 * @version 1.0 AudiCarDriverFactory.java  2021/11/25 21:17
 */
public class AudiCarMakeFactory implements CarMakeFactory {

    /**
     * 获取奥迪越野车
     */
    @Override
    public SuvCar getSuvCar() {
        return new AudiSuvCar();
    }

    /**
     * 获取奥迪跑车
     */
    @Override
    public SportsCar getSportsCar() {
        return new AudiSportsCar();
    }
}
