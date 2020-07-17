package com.aaa.javabase.leetcode.type1;


/**
 * 问题描述：
 *      求给定二叉树的最小深度，最小深度是指树的根结点到最近叶子结点的最短路径上结点的数量
 * 解题思路：
 *      这里的核心就是
 *          递归：通过 return  run()  +  1；
 *                           run()里面又是  return  run()  +  1；
 *                           ...
 *                           来找左右节点的深度
 *
 * @author liuzhen.tian
 * @version $ Id: Solution.java, v 0.1 2020/7/17 16:31 liuzhen.tian Exp $
 */
public class Solution {

    /**
     * 计数器
     */
    public  int count = 0;

    public int run(TreeNode treeNode) {
        count++;

        //如果根节点为空 深度就为0
        if (treeNode==null) {
            return 0;
        }

        //如果左子树为空 返回右子树的最小深度
        if (treeNode.left == null) {
            return run(treeNode.right) + 1;
        }

        //如果右子树为空 返回左子树的最小深度
        if (treeNode.right == null) {
            return run(treeNode.left) + 1;
        }

        //如果左右都不为空  那么就分别计算左右子树的深度，取最小值
        int left = run(treeNode.left);
        int right = run(treeNode.right);
        return Math.min(left,right) + 1;
    }


}
