package com.aaa.javabase.designpattern.createrpatten.factory.factorymethod.order;



import com.aaa.javabase.designpattern.createrpatten.factory.factorymethod.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/10
 */
public abstract class OrderPizza {
    //定义一个  抽象方法  ，createPizza，让各个工厂自己去实现
    abstract Pizza createPizza(String orderType);
    // 构造器完成订购
    public OrderPizza() {
        Pizza pizza=null;
        String ordertype;// 订购披萨的类型
        do {
            ordertype = getType();
            // 抽象方法由工厂子类去实现
            pizza = createPizza(ordertype);
            //输出pizza 制作过程
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        }while (true);
    }


    private String getType() {
        BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入披萨种类：");
        String str = null;
        try {
            str = strin.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
