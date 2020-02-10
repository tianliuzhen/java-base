package com.aaa.javabase.designpattern.factory.simplefactory;

import com.aaa.javabase.designpattern.factory.simplefactory.pizza.ChessPizz;
import com.aaa.javabase.designpattern.factory.simplefactory.pizza.GreekPizz;
import com.aaa.javabase.designpattern.factory.simplefactory.pizza.Pizza;

/**
 * description: 简单工厂类
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/10
 */
public class SimpleFactory {
    // 根据 orderType 返回对应的pizza对象
    public static Pizza createPizza(String ordertype){
        Pizza pizza = null;
        System.out.println("使用简单工厂模式");
        if (ordertype.equals("greek")) {
            pizza = new GreekPizz();
            pizza.setName("希腊");
        }else if (ordertype.equals("chess")){
            pizza = new ChessPizz();
            pizza.setName("奶酪");
        }
        return pizza;
    }
}
