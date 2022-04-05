package com.aaa.javabase.pattern.behavior.template;

/**
 * 抽象人
 *
 * @author liuzhen.tian
 * @version 1.0 AbstractClass.java  2022/4/5 13:21
 */
public abstract class AbstractCarModel {

    // 启动
    public abstract void start();

    // 熄火
    public abstract void stop();

    // alarm
    public abstract void alarm();

    // 行为
    public void run() {
        this.start();
        this.stop();
    }

}
