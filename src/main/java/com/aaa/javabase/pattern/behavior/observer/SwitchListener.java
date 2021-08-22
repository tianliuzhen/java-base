package com.aaa.javabase.pattern.behavior.observer;


import java.util.EventListener;

/**
 * 事件监听接口
 * 我们可以在这里，再定义事件监听类，这些类具体实现了监听功能和事件处理功能。
 * 也可以用下面主程序中的匿名内部类来实现。原理一样。
 *
 * @author liuzhen.tian
 * @version 1.0 SwitchListener.java  2021/8/22 18:19
 */
public interface SwitchListener extends EventListener {

    /**
     * 处理事件动作
     *
     * @param switchEvent 事件对象
     */
    void handleEvent(SwitchEvent switchEvent);
}
