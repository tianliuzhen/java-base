package com.aaa.javabase.designpattern.createrpatten.factory.abstartfactory.pizza;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/10
 */
public class BjChessPizza extends Pizza {
    @Override
    public void prepare() {
        setName("北京奶酪披萨");
        System.out.println("给奶酪披萨准备原材料");
    }
}
