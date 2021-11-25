package com.aaa.javabase.pattern.creater.factory.abstartfactory.factory;

import com.aaa.javabase.pattern.creater.factory.abstartfactory.CarMakeFactory;
import com.aaa.javabase.pattern.creater.factory.abstartfactory.product.SportsCar;
import com.aaa.javabase.pattern.creater.factory.abstartfactory.product.SuvCar;
import com.aaa.javabase.pattern.creater.factory.abstartfactory.product.benz.BenzSportsCar;
import com.aaa.javabase.pattern.creater.factory.abstartfactory.product.benz.BenzSuvCar;

/**
 * 具体工厂：奔驰制造工厂
 * <p>
 * 可以制造 suv和跑车
 *
 * @author liuzhen.tian
 * @version 1.0 BenzCarMakeFactory.java  2021/11/25 21:56
 */
public class BenzCarMakeFactory implements CarMakeFactory {

    /**
     * 获取奔驰跑车
     */
    @Override
    public SuvCar getSuvCar() {
        return new BenzSuvCar();
    }

    /**
     * 获取奔驰跑车
     */
    @Override
    public SportsCar getSportsCar() {
        return new BenzSportsCar();
    }
}
