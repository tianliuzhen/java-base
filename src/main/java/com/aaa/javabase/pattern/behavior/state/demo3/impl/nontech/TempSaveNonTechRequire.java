package com.aaa.javabase.pattern.behavior.state.demo3.impl.nontech;

import com.aaa.javabase.pattern.behavior.state.demo3.Require;
import com.aaa.javabase.pattern.behavior.state.demo3.model.IsTechEnum;

/**
 * 【暂存】 非技术需求暂存
 *
 * @author liuzhen.tian
 * @version 1.0 TempSaveNonTechRequire.java  2021/12/10 22:54
 */
public class TempSaveNonTechRequire implements Require {
    @Override
    public void handle(Integer requireId, Object data) {
        System.out.println("【暂存】 非技术需求暂存");
    }

    @Override
    public String isTech() {
        return IsTechEnum.NON_TECH.getCode();
    }

    @Override
    public String type() {
        return "tempSave";
    }
}
