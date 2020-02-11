package com.aaa.javabase.designpattern.factory.simplefactory.order;


import com.aaa.javabase.designpattern.factory.simplefactory.pizza.Pizza;

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
public class OrderPizza2 {
    Pizza pizza = null;
    public OrderPizza2() {
        do {
            String orderType = getType();
            pizza=   SimpleFactory.createPizza(orderType);
            //输出pizza
            if (pizza != null) {
                //订购成功
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            }else {
                System.out.println("订购披萨失败");
            }
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
