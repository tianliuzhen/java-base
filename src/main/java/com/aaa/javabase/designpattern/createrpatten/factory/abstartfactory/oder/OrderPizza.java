package com.aaa.javabase.designpattern.createrpatten.factory.abstartfactory.oder;

import com.aaa.javabase.designpattern.createrpatten.factory.abstartfactory.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/11
 */
public class OrderPizza {
    AbsFactory factory;

    public OrderPizza(AbsFactory factory) {
//        this.factory = factory;
        setFactory(factory);
    }

    private void setFactory(AbsFactory factory){
        Pizza pizza=null;
        String orderType = "";
        this.factory = factory;
        do {
            orderType = getType();
            pizza = factory.createFactory(orderType);
            if (pizza != null) {
                //输出pizza 制作过程
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            }else {
                System.out.println("订购失败");
                break;
            }

        } while (true);
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
