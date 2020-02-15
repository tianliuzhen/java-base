package com.aaa.javabase.designpattern.createrpatten.builder.improve;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/15
 */
public abstract   class HouseBuilder {
    //建造流程
    //打地基
    public abstract void buildBasic();
    //砌墙
    public abstract void buildWalls();
    //封顶
    public abstract void roofed();
    // 建造房子好，将产品（房子）返回
    public abstract House buildHouse() ;
}
