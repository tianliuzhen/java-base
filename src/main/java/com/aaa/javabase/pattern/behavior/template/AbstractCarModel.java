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

    // 鸣笛
    public abstract void alarm();

    // 行为
    public void run() {
        this.start();
        this.stop();

        // 不是所有的型号跑的时候都需要鸣笛
        if (this.isAlarm()) {
            this.alarm();
        }

    }

    // 模板方法拓展：这个isAlarm方法俗称钩子方法
    // 设置 isAlarm 钩子方法，由子类的一个方法返回值决定公共部分的执行结果
    protected boolean isAlarm() {
        return true;
    }

}
