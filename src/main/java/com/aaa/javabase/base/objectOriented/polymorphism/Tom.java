package com.aaa.javabase.base.objectOriented.polymorphism;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/1/18
 */
public class Tom extends Employee {
    public Tom(String name, String address, int number) {
        super(name, address, number);
    }

    @Override
    public double computePay() {
        return 0;
    }
}
