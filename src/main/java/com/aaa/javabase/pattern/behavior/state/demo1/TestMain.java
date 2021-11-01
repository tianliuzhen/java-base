package com.aaa.javabase.pattern.behavior.state.demo1;

import com.aaa.javabase.pattern.behavior.state.demo1.impl.StartProcessState;
import com.aaa.javabase.pattern.behavior.state.demo1.impl.StopProcessState;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2021/11/1 22:07
 */
public class TestMain {
    public static void main(String[] args) {

        // 1、构造状态上下文
        ContextState contextState = new ContextState();

        // 2、设置调用状态对象
        contextState.setState(new StartProcessState());
        // contextState.setState(new StopProcessState());

        // 3、执行方法
        contextState.doAction("starting");
        // contextState.doAction("stopping");

    }
}
