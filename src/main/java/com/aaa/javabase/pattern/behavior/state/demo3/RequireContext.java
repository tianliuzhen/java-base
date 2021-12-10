package com.aaa.javabase.pattern.behavior.state.demo3;

/**
 * 需求处理上下文
 *
 * @author liuzhen.tian
 * @version 1.0 RequireContext.java  2021/12/10 21:36
 */
public class RequireContext {

    /**
     * 需求
     */
    private Require require;

    /**
     * @param requireId 需求id
     * @param data      处理数据
     */
    public void doAction(Integer requireId, Object data) {
        require.handle(requireId, data);
    }


    public void setRequire(Require require) {
        this.require = require;
    }
}
