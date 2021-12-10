package com.aaa.javabase.pattern.behavior.state.demo3.impl.base;

import com.aaa.javabase.pattern.behavior.state.demo3.Require;

/**
 * 【通用】 确认需求
 *
 * @author liuzhen.tian
 * @version 1.0 BaseConfirmRequire.java  2021/12/10 23:04
 */
public class BaseConfirmRequire implements Require {
    /**
     * 需求确认
     *
     * @param requireId 需求id
     * @param data      处理数据
     */
    @Override
    public void handle(Integer requireId, Object data) {
        System.out.println(requireId + "：需求已确认...");
    }

    @Override
    public String isTech() {
        return "base";
    }

    @Override
    public String type() {
        return "confirm";
    }
}
