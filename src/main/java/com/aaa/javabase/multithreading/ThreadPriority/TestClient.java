package com.aaa.javabase.multithreading.ThreadPriority;

import javafx.application.Application;
import javafx.stage.Stage;

public class TestClient extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Thread1 thread1 = new Thread1("orange");
        thread1.start();
        thread1.setPriority(Thread.NORM_PRIORITY);
        Thread1 thread2 = new Thread1("orange2");
        thread2.start();
        thread2.setPriority(Thread.MIN_PRIORITY);
    }
}
