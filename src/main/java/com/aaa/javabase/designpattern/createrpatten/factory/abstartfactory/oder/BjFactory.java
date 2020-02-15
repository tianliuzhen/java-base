package com.aaa.javabase.designpattern.createrpatten.factory.abstartfactory.oder;


import com.aaa.javabase.designpattern.createrpatten.factory.abstartfactory.pizza.BjChessPizza;
import com.aaa.javabase.designpattern.createrpatten.factory.abstartfactory.pizza.BjGreekPizza;
import com.aaa.javabase.designpattern.createrpatten.factory.abstartfactory.pizza.Pizza;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/11
 */
public class BjFactory implements AbsFactory {
    @Override
    public Pizza createFactory(String orderType) {
        System.out.println("抽象工厂模式");
        Pizza pizza=null;
        if (orderType.equals("chess")) {
            pizza=new BjChessPizza();
        }else  if(orderType.equals("greek")){
            pizza=new BjGreekPizza();
        }
        return pizza;
    }
}
