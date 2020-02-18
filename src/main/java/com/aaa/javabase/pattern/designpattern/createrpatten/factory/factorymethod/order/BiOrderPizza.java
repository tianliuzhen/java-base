package com.aaa.javabase.pattern.designpattern.createrpatten.factory.factorymethod.order;

import com.aaa.javabase.pattern.designpattern.createrpatten.factory.factorymethod.pizza.BjChessPizz;
import com.aaa.javabase.pattern.designpattern.createrpatten.factory.factorymethod.pizza.BjGreekPizz;
import com.aaa.javabase.pattern.designpattern.createrpatten.factory.factorymethod.pizza.Pizza;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/11
 */
public class BiOrderPizza extends  OrderPizza {
    @Override
    Pizza createPizza(String orderType) {
        Pizza pizza=null;
        if (orderType.equals("chess")) {
            pizza=new BjChessPizz();
        }else  if(orderType.equals("greek")){
            pizza=new BjGreekPizz();
        }
        return pizza;
    }
}
