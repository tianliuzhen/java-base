package com.aaa.javabase.pattern.behavior.state.demo1.impl;

import com.aaa.javabase.pattern.behavior.state.demo1.State;

/**
 * 状态接口具体实现子类
 *
 * @author liuzhen.tian
 * @version 1.0 StopProcessState.java  2021/11/1 22:03
 */
public class StopProcessState implements State {
    @Override
    public void handle(String msg) {
        System.out.println("关闭流程状态..." + msg);
    }
}
