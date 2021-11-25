package com.aaa.javabase.pattern.creater.factory.abstartfactory;

import com.aaa.javabase.pattern.creater.factory.abstartfactory.product.SportsCar;
import com.aaa.javabase.pattern.creater.factory.abstartfactory.product.SuvCar;

/**
 * 抽象产品：工厂
 * <p>
 * 该工厂有俩个产品簇，分为是获取越野车 和 跑车
 *
 * @author liuzhen.tian
 * @version 1.0 CarFactory.java  2021/11/25 21:15
 */
public interface CarMakeFactory {
    /**
     * 获取越野车
     *
     * @return Car
     */
    SuvCar getSuvCar();

    /**
     * 获取跑车
     *
     * @return Car
     */
    SportsCar getSportsCar();
}
