package com.aaa.javabase.pattern.behavior.observer;

import java.util.LinkedList;

/**
 * @author liuzhen.tian
 * @version 1.0 Switch.java  2021/8/22 18:22
 */
public class Switch {

    private LinkedList<SwitchListener> switchListenerList = new LinkedList();

    /**
     * 添加监听事件
     *
     * @param listener 监听事件
     */
    public void addListener(SwitchListener listener) {
        switchListenerList.add(listener);
    }

    /**
     * 动作 -打开开关
     */
    public void open() {
        notifyListeners(new SwitchEvent(this, "开"));
    }

    /**
     * 动作 -关闭开关
     */
    public void close() {
        notifyListeners(new SwitchEvent(this, "关"));
    }

    /**
     * 唤醒监听器
     *
     * @param switchEvent 监听对象
     */
    public void notifyListeners(SwitchEvent switchEvent) {
        switchListenerList.forEach(item -> item.handleEvent(switchEvent));
    }
}
