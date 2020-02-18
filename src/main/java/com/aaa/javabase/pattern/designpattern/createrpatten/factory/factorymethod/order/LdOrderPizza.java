package com.aaa.javabase.pattern.designpattern.createrpatten.factory.factorymethod.order;

import com.aaa.javabase.pattern.designpattern.createrpatten.factory.factorymethod.pizza.*;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/11
 */
public class LdOrderPizza extends  OrderPizza {
    @Override
    Pizza createPizza(String orderType) {
        Pizza pizza=null;
        if (orderType.equals("chess")) {
            pizza=new LdChessPizz();
        }else  if(orderType.equals("greek")){
            pizza=new LdGreekPizz();
        }
        return pizza;
    }
}
