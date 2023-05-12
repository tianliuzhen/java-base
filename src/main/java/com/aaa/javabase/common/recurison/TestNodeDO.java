package com.aaa.javabase.common.recurison;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.assertj.core.util.Lists;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuzhen.tian
 * @version 1.0 TestNodeDO.java  2022/5/21 21:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestNodeDO {

    /**
     * 节点主键ID
     */
    private Long id;

    /**
     * 标签、名称
     */
    private String label;

    /**
     * 无父节点ID默认是0
     */
    private Long parentId;


    /**
     * mock 数据库数据
     *     * @return List<TestNodeModel>
     */
    public static List<TestNodeDO> getTestNodeDOList() {
        return Lists.newArrayList(
                new TestNodeDO(1L, "电脑品牌", 0L),
                new TestNodeDO(12L, "华硕", 1L),
                new TestNodeDO(13L, "联想", 1L),
                new TestNodeDO(4L, "手机品牌", 0L),
                new TestNodeDO(41L, "华为", 4L),
                new TestNodeDO(42L, "小米", 4L),
                new TestNodeDO(421L, "小米手机", 42L),
                new TestNodeDO(422L, "小米手环", 42L)
        );
    }

    public static void main(String[] args) {
        // mock 数据库数据
        List<TestNodeDO> testNodeDOList = getTestNodeDOList();

        // list转tree数据
        List<TestNodeModel> testNodeModeTree = getTestNodeModeTree(testNodeDOList);

        // tree数据转list
        List<String> labels = parseLabelNByTree(testNodeModeTree);

        System.out.println(testNodeModeTree);
    }

    /**
     * 递归解析tree数据格式
     *
     * @param testNodeModeTree tree数据格式
     * @return
     */
    public static List<String> parseLabelNByTree(List<TestNodeModel> testNodeModeTree) {
        List<String> result = new ArrayList<>();
        for (TestNodeModel nodeModel : testNodeModeTree) {
            result.add(nodeModel.getLabel());
            if (!CollectionUtils.isEmpty(nodeModel.getChildren())) {
                result.addAll(parseLabelNByTree(nodeModel.getChildren()));
            }
        }

        return result;
    }

    /**
     * 查询 testNodeDOList 转成json树格式
     *
     * @param testNodeDOList 所以的 TestNodeDO 数据
     * @return List<TestNodeModel>
     */
    public static List<TestNodeModel> getTestNodeModeTree(List<TestNodeDO> testNodeDOList) {
        // 筛选顶级父节点节点
        List<TestNodeDO> parentNodes = testNodeDOList.stream().filter(e -> e.getParentId().equals(0L)).collect(Collectors.toList());

        List<TestNodeModel> allTreeJson = getAllTreeJson(testNodeDOList, parentNodes);

        return allTreeJson;
    }

    /**
     * 递归获取json树
     *
     * @param testNodeDOList 所有数据集合
     * @param parentNodes    父级节点集合
     */
    public static List<TestNodeModel> getAllTreeJson(List<TestNodeDO> testNodeDOList, List<TestNodeDO> parentNodes) {
        List<TestNodeModel> result = Lists.newArrayList();
        for (TestNodeDO parentNode : parentNodes) {
            TestNodeModel testNodeModel = new TestNodeModel();
            testNodeModel.setId(parentNode.getId());
            testNodeModel.setLabel(parentNode.getLabel());
            testNodeModel.setParentId(parentNode.getParentId());
            List<TestNodeModel> children = Lists.newArrayList();
            testNodeModel.setChildren(children);
            // 看一下当前节点是不是别人的父节点
            List<TestNodeDO> parentNodeChildren = testNodeDOList.stream().filter(e -> parentNode.getId().equals(e.getParentId())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(parentNodeChildren)) {
                children.addAll(getAllTreeJson(testNodeDOList, parentNodeChildren));
            }
            result.add(testNodeModel);

        }
        return result;
    }
}
