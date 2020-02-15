package com.aaa.javabase.designpattern.createrpatten.builder.improve.houseType;

import com.aaa.javabase.designpattern.createrpatten.builder.improve.House;
import com.aaa.javabase.designpattern.createrpatten.builder.improve.HouseBuilder;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/15
 */
public class CommonHouse extends HouseBuilder {

    private House house;

    public CommonHouse() {
        house = new House();
    }

    @Override
    public void buildBasic() {
        house.setBaise("给普通房子打地基5m");
    }

    @Override
    public void buildWalls() {
        house.setWall("给普通方式砌墙10cm");
    }

    @Override
    public void roofed() {
        house.setRoofed("给普通方式封顶");
    }

    @Override
    public House buildHouse() {
        return house;
    }
}
