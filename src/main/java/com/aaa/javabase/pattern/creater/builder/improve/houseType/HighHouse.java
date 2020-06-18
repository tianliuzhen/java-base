package com.aaa.javabase.pattern.creater.builder.improve.houseType;

import com.aaa.javabase.pattern.creater.builder.improve.House;
import com.aaa.javabase.pattern.creater.builder.improve.HouseBuilder;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/15
 */
public class HighHouse extends HouseBuilder {
    private House house;

    public HighHouse() {
        house = new House();
    }

    @Override
    public void buildBasic() {
        house.setBaise("给高楼打地基105m");
    }

    @Override
    public void buildWalls() {
        house.setWall("给高楼方式砌墙20cm");
    }

    @Override
    public void roofed() {
        house.setRoofed("给高楼方式封顶透明");
    }

    @Override
    public House buildHouse() {
        return house;
    }
}
