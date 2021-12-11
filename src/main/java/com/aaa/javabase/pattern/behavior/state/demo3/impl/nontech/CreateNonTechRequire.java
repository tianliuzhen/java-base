package com.aaa.javabase.pattern.behavior.state.demo3.impl.nontech;

import com.aaa.javabase.pattern.behavior.state.demo3.Require;
import com.aaa.javabase.pattern.behavior.state.demo3.model.IsTechEnum;

/**
 * 【初始状态】 非技术需求创建
 *
 * @author liuzhen.tian
 * @version 1.0 CreateTechRequire.java  2021/12/10 21:40
 */
public class CreateNonTechRequire implements Require {
    @Override
    public void handle(Integer requireId, Object data) {
        System.out.println("【初始状态】 非技术需求创建");
    }
    @Override
    public String isTech() {
        return IsTechEnum.NON_TECH.getCode();
    }

    @Override
    public String type() {
        return "create";
    }
}
