package com.aaa.javabase.util;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.LinkedList;

public class psvm extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Object key = "asdqweqwe12312";
            int h=key.hashCode();
        System.out.println(h ^ (h >>> 16));
        //-1011110000100011111110101111111
        //h >>> 16  =  41454
        // 1010000111101110
        // -1011110000100010101110010010001
    }
}
