package com.aaa.javabase.designpattern.factory.factorymethod.pizza;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/10
 */
public class BjGreekPizz extends Pizza {
    @Override
    public void prepare() {
        System.out.println("希腊奶酪披萨");
        System.out.println("给希腊披萨准备原材料");
    }
}
