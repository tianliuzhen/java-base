package com.aaa.javabase.pattern.behavior.state.demo3;

/**
 * @author liuzhen.tian
 * @version 1.0 Require.java  2021/12/10 21:31
 */
public interface Require {

    /**
     * 创建需求、暂存、进行中 等
     *
     * @param requireId 需求id
     * @param data      处理数据
     */
    void handle(Integer requireId, Object data);

    /**
     * 技术类型
     */
    String isTech();

    /**
     * 状态类型
     */
    String type();
}
