package com.aaa.javabase.pattern.creater.factory.simplefactory.order;


import com.aaa.javabase.pattern.creater.factory.simplefactory.pizza.Pizza;

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
public class OrderPizza {
    //未使用设计模式的传统方式
    /*
    public OrderPizza() {

        Pizza pizza=null;
        String ordertype;// 订购披萨的类型
        do {
            ordertype = getType();
            if (ordertype.equals("greek")) {
                pizza = new GreekPizz();
                pizza.setName("希腊");
            }else if (ordertype.equals("chess")){
                pizza = new ChessPizz();
                pizza.setName("奶酪");
            }else {
                break;
            }
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        }while (true);
    }
    */

    // 定义简单工厂对象
    SimpleFactory simpleFactory;
    Pizza pizza = null;

    //构造器
    public OrderPizza(SimpleFactory simpleFactory) {
        setFactory(simpleFactory);
    }

    public void setFactory(SimpleFactory simpleFactory) {
        String orderType = "";
        this.simpleFactory = simpleFactory;
        do {
            orderType=getType();
            pizza=   this.simpleFactory.createPizza(orderType);
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
