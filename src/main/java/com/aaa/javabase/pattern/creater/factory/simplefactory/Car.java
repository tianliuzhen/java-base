package com.aaa.javabase.pattern.creater.factory.simplefactory;

import lombok.Getter;
import lombok.Setter;

/**
 * 简单工厂，也是静态工厂
 *
 * @author liuzhen.tian
 * @version 1.0 Car.java  2021/11/25 21:02
 */
public abstract class Car {

    /**
     * 汽车名字
     */
    @Getter
    @Setter
    private String name;

    // 抽象的方法
    public abstract void drive();

}
