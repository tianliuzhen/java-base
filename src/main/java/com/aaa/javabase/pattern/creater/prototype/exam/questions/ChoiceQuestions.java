package com.aaa.javabase.pattern.creater.prototype.exam.questions;

import lombok.Data;

import java.util.Map;

/**
 * 选择题 模型
 *
 * @author liuzhen.tian
 * @version 1.0 ChoiceQuestion.java  2022/3/27 20:02
 */

@Data
public class ChoiceQuestions {

    // 题目
    private String name;
    // 选型：A、B、C、D
    private Map<String, String> option;
    // 答案：B
    private String key;

    public ChoiceQuestions(String name, Map<String, String> option, String key) {
        this.name = name;
        this.option = option;
        this.key = key;
    }
}
