package com.aaa.javabase.designpattern.createrpatten.builder.improve.houseType;

import com.aaa.javabase.designpattern.createrpatten.builder.improve.HouseBuilder;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/15
 */
public class HighHouse extends HouseBuilder {
    @Override
    public void buildBasic() {
        System.out.println("高楼打地基100米");
    }

    @Override
    public void buildWalls() {
        System.out.println("高楼砌墙20厘米");
    }

    @Override
    public void roofed() {
        System.out.println("高楼的透明屋顶");
    }
}
