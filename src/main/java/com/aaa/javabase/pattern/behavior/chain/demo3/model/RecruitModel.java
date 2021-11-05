package com.aaa.javabase.pattern.behavior.chain.demo3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 RecruitModel.java  2021/11/5 21:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecruitModel {

    /**
     * 简历名字
     */
    private String name;

    /**
     * 状态编码
     */
    private String statusCode;

    /**
     * 招聘状态（对外展示数据）
     */
    private List<String> status;

}
