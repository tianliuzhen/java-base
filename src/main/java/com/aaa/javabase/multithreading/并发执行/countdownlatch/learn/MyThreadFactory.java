package com.aaa.javabase.multithreading.并发执行.countdownlatch.learn;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadFactory implements ThreadFactory{

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r,"myThread - " + atomicInteger.incrementAndGet());
    }
}
