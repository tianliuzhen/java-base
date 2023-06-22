package com.aaa.javabase.common.recurison;

import com.google.common.collect.Lists;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuzhen.tian
 * @version 1.0 NodeHelper.java  2023/6/22 13:48
 */
public class NodeHelper {
    /**
     * 根据当前节点id获取，所有的子节点
     *
     * @param initData  数据类型-list
     * @param nodeId    当前节点
     * @param isContain true-包含当前节点
     */
    public static List<Long> getChildByNodeIdForList(List<TestNodeModel> initData, Long nodeId, boolean isContain) {
        List<Long> ids = doGetChildByNodeIdForList(initData, nodeId);
        // 是否包含当前节点
        if (isContain) {
            ids.add(nodeId);
        }
        return ids;
    }

    @NotNull
    private static List<Long> doGetChildByNodeIdForList(List<TestNodeModel> initData, Long nodeId) {
        List<Long> ids = new ArrayList<>();
        for (TestNodeModel initDatum : initData) {
            if (initDatum.getParentId().equals(nodeId)) {
                ids.add(initDatum.getId());
                ids.addAll(doGetChildByNodeIdForList(initData, initDatum.getId()));
            }
        }
        return ids;
    }

    /**
     * 根据当前节点id获取，所有的父节点
     * </p>
     * *********** 这里理解起来比较绕，相比较递归获取子节点，难理解
     * 核心点：从每个头节点，递归开始去匹配
     *
     * @param initData 数据类型-tree
     * @param nodeId   当前节点
     */
    public static List<Long> getParentByNodeIdForTree(List<TestNodeModel> initData, Long nodeId, boolean isContain) {
        for (TestNodeModel initDatum : initData) {
            if (initDatum.getId().equals(nodeId)) {
                return Lists.newArrayList(initDatum.getId());
            }
            if (!CollectionUtils.isEmpty(initDatum.getChildren())) {
                List<Long> parentByNodeIdForTree = getParentByNodeIdForTree(initDatum.getChildren(), nodeId, isContain);
                if (!CollectionUtils.isEmpty(parentByNodeIdForTree)) {
                    // 添加父节点
                    parentByNodeIdForTree.add(initDatum.getId());

                    // 如果不需要包含当前节点这里需要过滤一下
                    if (!isContain){
                        parentByNodeIdForTree.remove(nodeId);
                    }
                    return parentByNodeIdForTree;
                }
            }
        }
        return new ArrayList<>();
    }

    /**
     * 根据当前节点id获取，所有的父节点
     * </p>
     *
     * @param initData 数据类型-list
     * @param nodeId   当前节点
     */
    public static List<Long> getParentByNodeIdForList(List<TestNodeModel> initData, Long nodeId, boolean isContain) {
        List<Long> ids = doGetParentByNodeIdForList(initData, nodeId);
        // 是否包含当前节点
        if (!isContain) {
            ids.remove(nodeId);
        }
        return ids;
    }

    @NotNull
    private static List<Long> doGetParentByNodeIdForList(List<TestNodeModel> initData, Long nodeId) {
        List<Long> result = new ArrayList<>();
        // 1、先找到当前节点
        TestNodeModel node = initData.stream().filter(e -> e.getId().equals(nodeId)).findFirst().get();

        // 2、用其父节点去递归查询
        if (node.getParentId().equals(0L)) {
            result.add(node.getId());
        } else {
            result.add(nodeId);
            result.addAll(doGetParentByNodeIdForList(initData, node.getParentId()));
        }
        return result;
    }

    /**
     * 根据当前节点id获取，所有的子节点
     *
     * @param initData 数据类型-tree
     * @param nodeId   当前节点
     */
    public static List<Long> getChildByNodeIdForTree(List<TestNodeModel> initData, Long nodeId, boolean isContain) {
        List<Long> ids = new ArrayList<>();
        for (TestNodeModel initDatum : initData) {
            if (initDatum.getId().equals(nodeId)) {
                //  是否包含当前节点
                if (isContain) {
                    ids.add(nodeId);
                }
                ids.addAll(getChildByNodeIdForTree(initDatum.getChildren()));
            } else {
                ids.addAll(getChildByNodeIdForTree(initDatum.getChildren(), nodeId, isContain));
            }
        }
        return ids;
    }

    public static List<Long> getChildByNodeIdForTree(List<TestNodeModel> initData) {
        List<Long> ids = new ArrayList<>();
        if (!CollectionUtils.isEmpty(initData)) {
            for (TestNodeModel initDatum : initData) {
                ids.add(initDatum.getId());
                ids.addAll(getChildByNodeIdForTree(initDatum.getChildren()));
            }
        }
        return ids;
    }

    /**
     * 构建树节点
     *
     * @param initData 数据类型-list
     * @return List<TestNodeModel> 数据类型-tree
     */
    public static List<TestNodeModel> buildTreeNode(List<TestNodeModel> initData) {
        // 筛选出顶级节点
        List<TestNodeModel> rootNode = getRootNode(initData);
        for (TestNodeModel testNodeModel : rootNode) {
            // 递归组装顶级节点的
            toBuildTreeNode(initData, testNodeModel);
            // toBuildTreeNodeV2(initData, testNodeModel); // 效果一样等同于toBuildTreeNode
        }
        return rootNode;
    }

    /**
     * 递归方法 1
     */
    private static void toBuildTreeNode(List<TestNodeModel> initData, TestNodeModel testNodeModel) {
        List<TestNodeModel> children = Lists.newArrayList();
        for (TestNodeModel initDatum : initData) {
            if (initDatum.getParentId().equals(testNodeModel.getId())) {
                children.add(initDatum);
            }
        }
        testNodeModel.setChildren(children);
        for (TestNodeModel child : children) {
            toBuildTreeNode(initData, child);
        }
    }

    /**
     * 递归方法 2
     */
    private static TestNodeModel toBuildTreeNodeV2(List<TestNodeModel> initData, TestNodeModel testNodeModel) {
        List<TestNodeModel> children = Lists.newArrayList();
        for (TestNodeModel initDatum : initData) {
            if (initDatum.getParentId().equals(testNodeModel.getId())) {
                children.add(toBuildTreeNodeV2(initData, initDatum));
            }
        }
        testNodeModel.setChildren(children);
        return testNodeModel;
    }

    @NotNull
    private static List<TestNodeModel> getRootNode(List<TestNodeModel> initData) {
        return initData.stream().filter(e -> e.getParentId().equals(0L)).collect(Collectors.toList());
    }
}
