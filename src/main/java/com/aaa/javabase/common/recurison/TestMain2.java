package com.aaa.javabase.common.recurison;

import com.aaa.javabase.util.FileUtil;

import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMain2.java  2022/12/24 12:15
 */
public class TestMain2 {


    public static void main(String[] args) {
        String path = "F:\\WorkSpace\\MyGithub\\java-base\\src\\main\\java\\com\\aaa\\javabase\\common\\recurison\\file\\TestNodeList.json";

        // mock 数据库存的数据
        List<TestNodeModel> initData = FileUtil.getByFilePath(path, TestNodeModel.class);
        // 查询当前节点下的所有子节点id
        List<Long> childNodeId = NodeHelper.getChildByNodeIdForList(initData, 1L, false);
        // 查询当前节点下的所有父节点id
        List<Long> parentByNodeIdForTree = NodeHelper.getParentByNodeIdForList(initData, 4L, false);


        // 构建树形json节点
        List<TestNodeModel> testNodeModels = NodeHelper.buildTreeNode(initData);
        // 查询当前节点下的所有子节点id
        List<Long> childNodeId2 = NodeHelper.getChildByNodeIdForTree(testNodeModels, 1L, false);
        // 查询当前节点下的所有父节点id
        List<Long> parentByNodeIdForTree2 = NodeHelper.getParentByNodeIdForTree(testNodeModels, 4L, true);
        System.out.println();
    }


}
