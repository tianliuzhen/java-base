package com.aaa.javabase.multithreading.yeild;

import javafx.application.Application;
import javafx.stage.Stage;

public class TestClient extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ThreadYield allen = new ThreadYield("allen");
        ThreadYield apple = new ThreadYield("apple");
        allen.start();
        apple.start();
    }
}
