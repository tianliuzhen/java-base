package com.aaa.javabase.designpattern.createrpatten.factory.factorymethod.pizza;

/**
 * description: 将pizza 类作为抽象类、接口亦可
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/10
 */
public abstract class Pizza {
    private String name;
    public abstract  void prepare();
    public void bake(){
        // 烘烤
        System.out.println(name + "bake");
    };
    public void cut(){
        // 切割
        System.out.println(name + "cut");
    };
    public void box(){
        // 打包
        System.out.println(name + "cut");
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
