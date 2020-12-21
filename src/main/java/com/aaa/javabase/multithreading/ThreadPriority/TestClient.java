package com.aaa.javabase.multithreading.ThreadPriority;

import javafx.application.Application;
import javafx.stage.Stage;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.UUID;

public class TestClient extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Thread1 thread1 = new Thread1("orange1");
        thread1.start();
        thread1.setPriority(Thread.NORM_PRIORITY);

        Thread1 thread3 = new Thread1("orange3");
        thread3.start();
        thread3.setPriority(Thread.MAX_PRIORITY);

        Thread1 thread2 = new Thread1("orange2");
        thread2.start();
        thread2.setPriority(Thread.MIN_PRIORITY);
        System.gc();
    }
}
