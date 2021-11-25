package com.aaa.javabase.pattern.creater.factory.abstartfactory.product.audi;

import com.aaa.javabase.pattern.creater.factory.abstartfactory.product.SuvCar;

/**
 * 具体产品：奥迪越野车
 *
 * @author liuzhen.tian
 * @version 1.0 AudiSuvCar.java  2021/11/25 21:50
 */
public class AudiSuvCar implements SuvCar {
    @Override
    public void driver() {
        System.out.println("开奥迪【越野车】...");
    }
}
