package com.aaa.javabase.common.recurison;

import com.aaa.javabase.util.FileUtil;
import com.google.common.collect.Lists;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMain2.java  2022/12/24 12:15
 */
public class TestMain2 {

    public static final String path = "F:\\WorkSpace\\MyGithub\\java-base\\src\\main\\java\\com\\aaa\\javabase\\common\\recurison\\file\\TestNodeList.json";

    public static void main(String[] args) {
        // mock 数据库存的数据
        List<TestNodeModel> initData = FileUtil.getByFilePath(path, TestNodeModel.class);

        // 构建树形json节点
        List<TestNodeModel> testNodeModels = buildTreeNode(initData);

        // 查询当前节点下的所有节点id
        List<Long> ids = Lists.newArrayList();
        // getChildByNodeId(initData, 1L, ids); // 效果同下
        getChildByNodeIdV2(initData, 1L, ids);

        System.out.println();
    }
    /**
     * 根据当前节点id获取，所有的子节点（数据类型为最原始数据）
     */
    private static void getChildByNodeIdV2(List<TestNodeModel> initData, Long nodeId, List<Long> ids) {
        for (TestNodeModel initDatum : initData) {
            if (initDatum.getParentId().equals(nodeId)) {
                ids.add(initDatum.getId());
                getChildByNodeIdV2(initData, initDatum.getId(), ids);
            }
        }
    }


    /**
     * 根据当前节点id获取，所有的子节点（数据类型为解析并填充过的json数据）
     */
    private static void getChildByNodeId(List<TestNodeModel> initData, Long nodeId, List<Long> ids) {
        for (TestNodeModel initDatum : initData) {
            if (initDatum.getId().equals(nodeId)) {
                doGetChildByNodeId(initDatum.getChildren(), ids);
            }
        }
    }
    public static void doGetChildByNodeId(List<TestNodeModel> initData, List<Long> ids) {
        for (TestNodeModel initDatum : initData) {
            ids.add(initDatum.getId());
            if (!CollectionUtils.isEmpty(initDatum.getChildren())) {
                doGetChildByNodeId(initDatum.getChildren(), ids);
            }
        }
    }

    /**
     * 构建树节点
     *
     * @param initData 初始化数据
     * @return List<TestNodeModel>
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
