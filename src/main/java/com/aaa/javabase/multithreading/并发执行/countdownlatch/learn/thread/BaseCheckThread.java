package com.aaa.javabase.multithreading.并发执行.countdownlatch.learn.thread;

import java.util.concurrent.Callable;

public abstract class BaseCheckThread implements Callable<Boolean>{

    protected final int uid;

    public BaseCheckThread(int uid){
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }
}
