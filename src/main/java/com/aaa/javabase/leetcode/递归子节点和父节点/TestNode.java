package com.aaa.javabase.leetcode.递归子节点和父节点;

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
public class TestNode {

    private String parentId;

    private String childrenId;

    private List<TestNode> childrenList;


    public TestNode(String parentId, String childrenId, List<TestNode> childrenList) {
        this.parentId = parentId;
        this.childrenId = childrenId;
        this.childrenList = childrenList;
    }

    public TestNode(String parentId, String childrenId) {
        this.parentId = parentId;
        this.childrenId = childrenId;
    }
}
