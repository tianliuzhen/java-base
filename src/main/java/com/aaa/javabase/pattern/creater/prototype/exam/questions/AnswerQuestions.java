package com.aaa.javabase.pattern.creater.prototype.exam.questions;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 问答题 模型
 *
 * @author liuzhen.tian
 * @version 1.0 AnswerQuestions.java  2022/3/27 20:01
 */

@Data
@AllArgsConstructor
public class AnswerQuestions {
    // 问答题：题目
    private String problem;

    // 问答题：答案
    private String answer;


}
