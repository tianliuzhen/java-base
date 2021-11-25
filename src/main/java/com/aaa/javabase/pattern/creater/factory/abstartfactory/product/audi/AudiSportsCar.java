package com.aaa.javabase.pattern.creater.factory.abstartfactory.product.audi;

import com.aaa.javabase.pattern.creater.factory.abstartfactory.product.SportsCar;

/**
 * 具体产品：奥迪跑车
 *
 * @author liuzhen.tian
 * @version 1.0 AudiSportsCar.java  2021/11/25 21:50
 */
public class AudiSportsCar implements SportsCar {
    @Override
    public void driver() {
        System.out.println("开奥迪【跑车】...");
    }
}
