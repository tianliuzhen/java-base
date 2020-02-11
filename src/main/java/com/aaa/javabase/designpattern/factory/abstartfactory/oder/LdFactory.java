package com.aaa.javabase.designpattern.factory.abstartfactory.oder;


import com.aaa.javabase.designpattern.factory.abstartfactory.pizza.*;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/11
 */
public class LdFactory implements AbsFactory {
    @Override
    public Pizza createFactory(String orderType) {
        System.out.println("抽象工厂模式");
        Pizza pizza=null;
        if (orderType.equals("chess")) {
            pizza=new LdChessPizza();
        }else  if(orderType.equals("greek")){
            pizza=new LdGreekPizza();
        }
        return pizza;
    }
}
