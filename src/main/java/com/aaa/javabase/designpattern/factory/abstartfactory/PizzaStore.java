package com.aaa.javabase.designpattern.factory.abstartfactory;

import com.aaa.javabase.designpattern.factory.abstartfactory.oder.BjFactory;
import com.aaa.javabase.designpattern.factory.abstartfactory.oder.OrderPizza;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/11
 */
public class PizzaStore {
    public static void main(String[] args) {
        new OrderPizza(new BjFactory());
    }
}
