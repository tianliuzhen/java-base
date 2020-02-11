package com.aaa.javabase.designpattern.factory.simplefactory;

import com.aaa.javabase.designpattern.factory.simplefactory.order.OrderPizza2;

/**
 * description: 相当于客户端，发出订购
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/10
 */
public class PizzaStore {
    public static void main(String[] args) {
        //new OrderPizza();
        //使用简单工厂模式
//        new OrderPizza(new SimpleFactory());
        new OrderPizza2();
        System.out.println("退出程序");
    }
}
