package com.aaa.javabase.pattern.behavior.state.demo1;

/**
 * @author liuzhen.tian
 * @version 1.0 State.java  2021/11/1 22:02
 */
public interface State {
    /**
     * 状态对应的处理
     */
    void handle(String msg);
}
