package com.aaa.javabase.pattern.creater.factory.abstartfactory.product.benz;

import com.aaa.javabase.pattern.creater.factory.abstartfactory.product.SuvCar;

/**
 * 具体产品：奔驰越野车
 *
 * @author liuzhen.tian
 * @version 1.0 BenzSuvCar.java  2021/11/25 21:53
 */
public class BenzSuvCar implements SuvCar {
    @Override
    public void driver() {
        System.out.println("开奔驰【越野车】...");
    }
}
