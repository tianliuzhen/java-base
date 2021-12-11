package com.aaa.javabase.pattern.behavior.state.demo3.impl.base;

import com.aaa.javabase.pattern.behavior.state.demo3.Require;

/**
 * 【通用】 需求评价
 *
 * @author liuzhen.tian
 * @version 1.0 BaseEvaluateRequire.java  2021/12/10 23:06
 */
public class BaseEvaluateRequire implements Require {
    @Override
    public void handle(Integer requireId, Object data) {
        System.out.println(requireId + "：需求已评价...");
    }

    @Override
    public String isTech() {
        return "";
    }

    @Override
    public String type() {
        return "evaluate";
    }
}
