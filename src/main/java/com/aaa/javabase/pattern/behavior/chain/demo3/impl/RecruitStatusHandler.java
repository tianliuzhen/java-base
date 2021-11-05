package com.aaa.javabase.pattern.behavior.chain.demo3.impl;

import com.aaa.javabase.pattern.behavior.chain.demo3.model.RecruitModel;

import java.util.List;

/**
 * 招聘状态构建 Handler
 *
 * @author liuzhen.tian
 * @version 1.0 RecruitStatusHandler.java  2021/11/5 21:56
 */
public interface RecruitStatusHandler {

    /**
     * 设置招聘列表状态
     *
     * @param recruitModels model
     */
    void buildStatus(List<RecruitModel> recruitModels);
}
