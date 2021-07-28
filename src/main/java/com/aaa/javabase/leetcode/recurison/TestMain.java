package com.aaa.javabase.leetcode.recurison;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2021/7/28 21:26
 */

import com.alibaba.fastjson.JSON;
import org.assertj.core.util.Lists;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author wb-tlz746088
 * @version $Id: TestMain.java,v 0.1 2021年07月28日  14:38:06 Exp $
 */
public class TestMain {
    public static void main(String[] args) {
        List<TestNode> testNodes = mockData();

        // 1、获取节点id的所有父节点集合
        List<TestNode> parentId = getParentNodesById(testNodes, 6);

        // 2、获取节点id获取某个节点
        TestNode nodesById = getNodesById(testNodes, 9);

        // 4、获取节点id获取某个节点
        // 4.1、先获取某个节点对象
        List<TestNode> nodeResult = Lists.newArrayList();
        getNodesById2(testNodes, 1, nodeResult);
        // 4.2、先获取某个节点对象
        List<TestNode> result = Lists.newArrayList();
        result.add(nodeResult.get(0));
        getChildrenNodesById(nodeResult.get(0), result);

        System.out.println(123);

    }

    private static void getChildrenNodesById(TestNode testNodes, List<TestNode> result) {
        // result.add(testNodes);
        if (!CollectionUtils.isEmpty(testNodes.getChildren())) {
            for (TestNode testNode : testNodes.getChildren()) {
                result.add(testNode);
                if (!CollectionUtils.isEmpty(testNode.getChildren())) {
                    getChildrenNodesById(testNode, result);
                }
            }
        }
    }

    /**
     * 1、根据子节点获取父节点集合
     *
     * @param testNodes 节点集合
     * @param id        节点id
     * @return list
     */
    private static List<TestNode> getParentNodesById(List<TestNode> testNodes, long id) {
        for (TestNode testNode : testNodes) {
            // 匹配id
            if (testNode.getId().equals(id)) {
                return Lists.newArrayList(testNode);
            }

            if (!CollectionUtils.isEmpty(testNode.getChildren())) {
                // 核心思路：从集合第一级开始递归查询其所有的子节点，并匹配id
                List<TestNode> parentsNodes = getParentNodesById(testNode.getChildren(), id);
                if (!CollectionUtils.isEmpty(parentsNodes)) {
                    parentsNodes.add(testNode);
                    return parentsNodes;
                }
            }
        }
        return null;
    }

    /**
     * 2、根据ID获取该节点的对象
     *
     * @param testNodes 节点集合
     * @param id        节点id
     * @return TestNode
     */
    private static TestNode getNodesById(List<TestNode> testNodes, long id) {
        // 调用根据子节点获取父节点的方法
        List<TestNode> parentNodesByChildrenId = getParentNodesById(testNodes, id);
        if (!CollectionUtils.isEmpty(parentNodesByChildrenId)) {
            return parentNodesByChildrenId.get(0);
        }
        return null;
    }

    /**
     * 3、根据ID获取所有子节点的对象，首先把该节点的对象找出来，上面getNodesById（）这个方法
     *
     * @param testNodes 节点集合
     * @param id        节点id
     * @return TestNode
     */
    private static void getNodesById2(List<TestNode> testNodes, long id, List<TestNode> testNodeList) {
        for (TestNode testNode : testNodes) {
            // 匹配到节点
            if (testNode.getId().equals(id)) {
                testNodeList.add(testNode);
            }

            if (!CollectionUtils.isEmpty(testNode.getChildren())) {
                getNodesById2(testNode.getChildren(), id, testNodeList);
            }
        }


    }

    private static List<TestNode> mockData() {
        String json = "[\n" +
                "         {\n" +
                "          \"id\":1,\n" +
                "          \"label\":\"一级 1\",\n" +
                "          \"children\":[{\n" +
                "            \"id\":4,\n" +
                "            \"label\":\"二级 1-1\",\n" +
                "            \"children\":[{\n" +
                "              \"id\":9,\n" +
                "              \"label\":\"三级 1-1-1\"\n" +
                "            }, {\n" +
                "              \"id\":10,\n" +
                "              \"label\":\"三级 1-1-2\"\n" +
                "            }]\n" +
                "          }]\n" +
                "        }, \n" +
                "        {\n" +
                "          \"id\":2,\n" +
                "          \"label\":\"一级 2\",\n" +
                "          \"children\":[{\n" +
                "            \"id\":5,\n" +
                "            \"label\":\"二级 2-1\"\n" +
                "          },{\n" +
                "            \"id\":6,\n" +
                "            \"label\":\"二级 2-2\"\n" +
                "          }]\n" +
                "        }, \n" +
                "        {\n" +
                "          \"id\":3,\n" +
                "          \"label\":\"一级 3\",\n" +
                "          \"children\":[{\n" +
                "            \"id\":7,\n" +
                "            \"label\":\"二级 3-1\"\n" +
                "          }, {\n" +
                "            \"id\":8,\n" +
                "            \"label\":\"二级 3-2\"\n" +
                "          }]\n" +
                "        }]";

        return JSON.parseArray(json, TestNode.class);
    }
}
