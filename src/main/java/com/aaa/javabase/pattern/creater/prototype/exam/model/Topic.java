package com.aaa.javabase.pattern.creater.prototype.exam.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * @author liuzhen.tian
 * @version 1.0 Topic.java  2022/3/27 21:11
 */

@Data
@AllArgsConstructor
public class Topic {
    private Map<String, String> option;
    // 答案：B
    private String key;
}
