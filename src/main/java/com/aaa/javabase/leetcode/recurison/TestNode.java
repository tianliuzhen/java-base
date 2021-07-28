package com.aaa.javabase.leetcode.recurison;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 TestNode.java  2021/7/25 16:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestNode {

    private Long id;

    private String label;

    private List<TestNode> children;


}
