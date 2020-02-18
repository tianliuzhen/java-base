package com.aaa.javabase.pattern.designpattern.createrpatten.builder;

import javafx.application.Application;
import javafx.stage.Stage;

public class Client extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        CommonHouse commonHouse = new CommonHouse();
        commonHouse.build();
    }
}
