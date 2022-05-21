package com.aaa.javabase.common.recurison;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2021/7/28 21:26
 */

import com.alibaba.fastjson.JSON;
import org.assertj.core.util.Lists;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author wb-tlz746088
 * @version $Id: TestMain.java,v 0.1 2021年07月28日  14:38:06 Exp $
 */
public class TestMain {
    public static void main(String[] args) {
        List<TestNodeModel> TestNodeModels = mockData();

        // 1、获取节点id的所有父节点集合(包含当前节点id)
        List<TestNodeModel> parentId = getParentNodesById(TestNodeModels, 101);

        // 2、获取节点id获取某个节点
        TestNodeModel nodesById = getNodesById(TestNodeModels, 9);

        // 4、获取节点id获取该节点下的所有节点
        // 4.1、先获取某个节点对象
        List<TestNodeModel> nodeResult = Lists.newArrayList();
        getNodesById2(TestNodeModels, 1, nodeResult);
        // 4.2、先获取某个节点对象
        List<TestNodeModel> result = Lists.newArrayList();
        result.add(nodeResult.get(0));
        getChildrenNodesById(nodeResult.get(0), result);

        // 5、根据节点id获取兄弟所有节点
        List<TestNodeModel> brotherNodesById = getBrotherNodesById(TestNodeModels, 9);


        // 6、根据当前节点id，获取及所有的父级兄弟节点的所有父节点
        List<TestNodeModel> parentBrotherAllNodesById = getParentBrotherAllNodesById(TestNodeModels, 1);

        // 7、根据节点id获取当前节点
        TestNodeModel TestNodeModel = getNodesById3(TestNodeModels, 8);

    }


    /**
     * 根据当前节点id，获取及所有的父级兄弟节点的所有父节点
     *
     * @param TestNodeModels
     * @param id
     */
    private static List<TestNodeModel> getParentBrotherAllNodesById(List<TestNodeModel> TestNodeModels, int id) {
        List<TestNodeModel> result = Lists.newArrayList();
        TestNodeModel TestNodeModel = null;
        // 1、获取当前节点id父节点的父节点
        List<TestNodeModel> parentNodes = getParentNodesById(TestNodeModels, id);

        // 小于3表示当前父节点是顶级节点
        if (parentNodes.size() < 3) {
            return Collections.singletonList(parentNodes.get(parentNodes.size() - 1));
        }

        TestNodeModel = parentNodes.get(2);

        // 2、获取父节点的父节点所有兄弟节点
        List<TestNodeModel> children = TestNodeModel.getChildren();
        for (TestNodeModel child : children) {
            List<TestNodeModel> parentNodesById = getParentNodesById(TestNodeModels, child.getId());
            if (parentNodesById.size() < 2) {
                continue;
            }
            result.addAll(parentNodesById.subList(0, parentNodesById.size() - 1));

        }

        return result;

    }

    private static void getChildrenNodesById(TestNodeModel TestNodeModels, List<TestNodeModel> result) {
        // result.add(TestNodeModels);
        if (!CollectionUtils.isEmpty(TestNodeModels.getChildren())) {
            for (TestNodeModel TestNodeModel : TestNodeModels.getChildren()) {
                result.add(TestNodeModel);
                if (!CollectionUtils.isEmpty(TestNodeModel.getChildren())) {
                    getChildrenNodesById(TestNodeModel, result);
                }
            }
        }
    }

    /**
     * 1、根据子节点获取父节点集合
     *
     * @param TestNodeModels 节点集合
     * @param id        节点id
     * @return list
     */
    private static List<TestNodeModel> getParentNodesById(List<TestNodeModel> TestNodeModels, long id) {
        for (TestNodeModel TestNodeModel : TestNodeModels) {
            // 匹配id
            if (TestNodeModel.getId().equals(id)) {
                return Lists.newArrayList(TestNodeModel);
            }

            if (!CollectionUtils.isEmpty(TestNodeModel.getChildren())) {
                // 核心思路：从集合第一级开始递归查询其所有的子节点，并匹配id
                List<TestNodeModel> parentsNodes = getParentNodesById(TestNodeModel.getChildren(), id);
                if (!CollectionUtils.isEmpty(parentsNodes)) {
                    parentsNodes.add(TestNodeModel);
                    return parentsNodes;
                }
            }
        }
        return Lists.newArrayList();
    }

    /**
     * 2、根据ID获取该节点的对象 -- 写法1
     *
     * @param TestNodeModels 节点集合
     * @param id        节点id
     * @return TestNodeModel
     */
    private static TestNodeModel getNodesById(List<TestNodeModel> TestNodeModels, long id) {
        // 调用根据子节点获取父节点的方法
        List<TestNodeModel> parentNodesByChildrenId = getParentNodesById(TestNodeModels, id);
        if (!CollectionUtils.isEmpty(parentNodesByChildrenId)) {
            return parentNodesByChildrenId.get(0);
        }
        return null;
    }

    /**
     * 3、根据ID获取该节点的对象 -- 写法2
     *
     * @param TestNodeModels 节点集合
     * @param id        节点id
     * @return TestNodeModel
     */
    private static void getNodesById2(List<TestNodeModel> TestNodeModels, long id, List<TestNodeModel> TestNodeModelList) {
        for (TestNodeModel TestNodeModel : TestNodeModels) {
            // 匹配到节点
            if (TestNodeModel.getId().equals(id)) {
                TestNodeModelList.add(TestNodeModel);
            }

            if (!CollectionUtils.isEmpty(TestNodeModel.getChildren())) {
                getNodesById2(TestNodeModel.getChildren(), id, TestNodeModelList);
            }
        }


    }

    /**
     * 3、根据ID获取该节点的对象 -- 写法3
     * @param TestNodeModels 节点集合
     * @param id 节点id
     * @return TestNodeModel
     */
    private static TestNodeModel getNodesById3(List<TestNodeModel> TestNodeModels, long id) {
        for (TestNodeModel TestNodeModel : TestNodeModels) {
            // 匹配到节点
            if (TestNodeModel.getId().equals(id)) {
                return TestNodeModel;
            }
            if (!CollectionUtils.isEmpty(TestNodeModel.getChildren())) {
                TestNodeModel node = getNodesById3(TestNodeModel.getChildren(), id);
                if (node != null) {
                    return node;
                }
            }
        }
        return null;
    }

    /**
     * 5、根据ID获取所有子节点的所有兄弟节点
     *
     * @param TestNodeModels 节点集合
     * @param id        节点id
     * @return TestNodeModel
     */
    private static List<TestNodeModel> getBrotherNodesById(List<TestNodeModel> TestNodeModels, long id) {
        // 非顶级节点：获取节点父节点对象里的children
        List<TestNodeModel> parentNodes = getParentNodesById(TestNodeModels, id);
        if (parentNodes.size() >= 2) {
            return parentNodes.get(1).getChildren();
        }

        // 顶级节点：第一级是自己，从原始数组中遍历第一层即可
        parentNodes.clear();
        parentNodes.addAll(TestNodeModels);
        return parentNodes;

    }

    private static List<TestNodeModel> mockData() {
        String json = "[\n" +
                "    {\n" +
                "        \"id\":1,\n" +
                "        \"label\":\"一级 1\",\n" +
                "        \"children\":[\n" +
                "            {\n" +
                "                \"id\":4,\n" +
                "                \"label\":\"二级 1-1\",\n" +
                "                \"children\":[\n" +
                "                    {\n" +
                "                        \"id\":9,\n" +
                "                        \"label\":\"三级 1-1-1\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"id\":10,\n" +
                "                        \"label\":\"三级 1-1-2\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\":41,\n" +
                "                \"label\":\"二级 1-2\",\n" +
                "                \"children\":[\n" +
                "                    {\n" +
                "                        \"id\":91,\n" +
                "                        \"label\":\"三级 1-2-1\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"id\":101,\n" +
                "                        \"label\":\"三级 1-2-2\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\":2,\n" +
                "        \"label\":\"一级 2\",\n" +
                "        \"children\":[\n" +
                "            {\n" +
                "                \"id\":5,\n" +
                "                \"label\":\"二级 2-1\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\":6,\n" +
                "                \"label\":\"二级 2-2\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\":3,\n" +
                "        \"label\":\"一级 3\",\n" +
                "        \"children\":[\n" +
                "            {\n" +
                "                \"id\":7,\n" +
                "                \"label\":\"二级 3-1\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\":8,\n" +
                "                \"label\":\"二级 3-2\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]";

        return JSON.parseArray(json, TestNodeModel.class);
    }
}
