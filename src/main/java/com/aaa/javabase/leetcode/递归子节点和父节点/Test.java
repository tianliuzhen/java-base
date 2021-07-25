package com.aaa.javabase.leetcode.递归子节点和父节点;

import org.assertj.core.util.Lists;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 Test.java  2021/7/25 16:32
 */
public class Test {

    public static void main(String[] args) {
        List<TestNode> list = Lists.newArrayList();

        TestNode testNode1Children = new TestNode("父1-1", "子1-1");
        TestNode testNode2Children = new TestNode("父1-2", "子1-2");
        List<TestNode> testNodes = Lists.newArrayList(testNode1Children, testNode2Children);

        TestNode testNode1 = new TestNode("父1", "子1", testNodes);

        List<TestNodeData> list1 = Lists.newArrayList();
        List<TestNodeData> allChildren = getAllChildren(testNode1, list1);
        System.out.println();

    }

    /**
     * 获取所有子节点
     *
     * @param testNode json
     * @param list     返回集合
     */
    public static List<TestNodeData> getAllChildren(TestNode testNode, List<TestNodeData> list) {

        if (!CollectionUtils.isEmpty(testNode.getChildrenList())) {
            for (TestNode node : testNode.getChildrenList()) {
                list.add(new TestNodeData(node.getParentId(), node.getChildrenId()));
                getAllChildren(node, list);
            }
        }
        return list;
    }


}
