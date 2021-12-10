package com.aaa.javabase.pattern.behavior.state.demo3.impl.tech;

import com.aaa.javabase.pattern.behavior.state.demo3.Require;

/**
 * 【分配】 技术需求
 *
 * @author liuzhen.tian
 * @version 1.0 DistributionTechRequire.java  2021/12/10 22:56
 */
public class DistributionTechRequire implements Require {
    @Override
    public void handle(Integer requireId, Object data) {
        System.out.println("【分配】 技术需求...");
    }
    @Override
    public String isTech() {
        return "tech";
    }

    @Override
    public String type() {
        return "distribution";
    }
}
