package com.aaa.javabase.pattern.behavior.state.demo1;

/**
 * 状态上下文
 *
 * @author liuzhen.tian
 * @version 1.0 State.java  2021/11/1 21:01
 */
public class ContextState {

    // 状态流程实例
    private State state;

    // 要调用的方法
    public void doAction(String msg) {
        state.handle(msg);
    }

    public void setState(State state) {
        this.state = state;
    }

}
