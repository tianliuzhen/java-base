package com.aaa.javabase.multithreading.threadpool.delayed;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 Order.java  2021/12/17 21:28
 */
public class Order implements Delayed {
    /**
     * 延迟时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private long time;
    String name;

    public Order(String name, long time, TimeUnit unit) {
        this.name = name;
        this.time = System.currentTimeMillis() + (time > 0 ? unit.toMillis(time) : 0);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return time - System.currentTimeMillis();
    }
    @Override
    public int compareTo(Delayed o) {
        Order Order = (Order) o;
        long diff = this.time - Order.time;
        if (diff <= 0) {
            return -1;
        } else {
            return 1;
        }
    }
}
