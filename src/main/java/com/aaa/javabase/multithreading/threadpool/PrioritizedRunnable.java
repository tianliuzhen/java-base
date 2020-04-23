package com.aaa.javabase.multithreading.threadpool;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/4/23
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
        }else if(this.getRts()> secondOne.getRts()){
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public void run() {
        Random random = new Random();
        log.info("rts={}, name={}", rts, name);
        try {
            int sleepRandom = random.nextInt(200);
            Thread.sleep(sleepRandom);
        } catch (Exception ex) {
            log.info("sleep exception", ex);
        }
    }
}

