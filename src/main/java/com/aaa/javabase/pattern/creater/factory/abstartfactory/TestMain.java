package com.aaa.javabase.pattern.creater.factory.abstartfactory;


import com.aaa.javabase.pattern.creater.factory.abstartfactory.factory.AudiCarMakeFactory;
import com.aaa.javabase.pattern.creater.factory.abstartfactory.factory.BenzCarMakeFactory;
import com.aaa.javabase.pattern.creater.factory.abstartfactory.product.SportsCar;
import com.aaa.javabase.pattern.creater.factory.abstartfactory.product.SuvCar;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2021/11/25 22:02
 */
public class TestMain {
    public static void main(String[] args) {
        // 奥迪抽象制造工厂
        AudiCarMakeFactory audi = new AudiCarMakeFactory();
        SportsCar sportsCar = audi.getSportsCar();
        sportsCar.driver();
        SuvCar suvCar = audi.getSuvCar();
        suvCar.driver();

        System.out.println("------------------------------");

        // 奔驰抽象制造工厂
        BenzCarMakeFactory benz = new BenzCarMakeFactory();
        SportsCar sportsCar1 = benz.getSportsCar();
        sportsCar1.driver();
        SuvCar suvCar1 = benz.getSuvCar();
        suvCar1.driver();


    }
}
