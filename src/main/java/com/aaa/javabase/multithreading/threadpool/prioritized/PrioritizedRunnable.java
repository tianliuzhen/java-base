package com.aaa.javabase.multithreading.threadpool.prioritized;

/**
 * 线程池有界队列
 *
 * @author liuzhen.tian
 */

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class PrioritizedRunnable implements Runnable, Comparable<PrioritizedRunnable> {
    private long rts;
    private String name;


    PrioritizedRunnable(long rts, String name) {
        this.rts = rts;
        this.name = name;
    }

    public long getRts() {
        return rts;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(PrioritizedRunnable secondOne) {
        // 时间越小越优先
        log.info("compareTo. this.name={}, secondOne.name={}", this.getName(), secondOne.getName());
        if (this.getRts() < secondOne.getRts()) {
            return -1;
        } else if (this.getRts() > secondOne.getRts()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public void run() {
        Random random = new Random();
        System.out.println("rts=" + rts + ", name=" + name + "");
        log.info("rts={}, name={}", rts, name);
        try {
            int sleepRandom = random.nextInt(200);
            Thread.sleep(sleepRandom);
        } catch (Exception ex) {
            log.info("sleep exception", ex);
        }
    }
}

