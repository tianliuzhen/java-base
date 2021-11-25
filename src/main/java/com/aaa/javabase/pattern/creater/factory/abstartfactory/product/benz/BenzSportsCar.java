package com.aaa.javabase.pattern.creater.factory.abstartfactory.product.benz;

import com.aaa.javabase.pattern.creater.factory.abstartfactory.product.SportsCar;

/**
 * 具体产品：奔驰跑车
 *
 * @author liuzhen.tian
 * @version 1.0 BenzSportsCar.java  2021/11/25 21:54
 */
public class BenzSportsCar implements SportsCar {
    @Override
    public void driver() {
        System.out.println("开奔驰【跑车】...");
    }
}
