package com.aaa.javabase.designpattern.createrpatten.builder.improve;

import com.aaa.javabase.designpattern.createrpatten.builder.improve.houseType.CommonHouse;
import com.aaa.javabase.designpattern.createrpatten.builder.improve.houseType.HighHouse;
import javafx.application.Application;
import javafx.stage.Stage;

public class Client extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //盖普通房子
        CommonHouse commonHouse = new CommonHouse();
        //准备创建房子的指挥者
        HouseDirector houseDirector = new HouseDirector(commonHouse);
        //完成盖房子，返回产品
        House house = houseDirector.constructHouse();

        System.out.println("-------------");

        //盖高楼
        HighHouse highHouse = new HighHouse();
        //准备创建房子的指挥者
        houseDirector.setHouseBuilder(highHouse);
        House house1 = houseDirector.constructHouse();
    }
}
