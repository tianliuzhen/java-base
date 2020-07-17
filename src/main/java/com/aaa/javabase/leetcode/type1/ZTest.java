package com.aaa.javabase.leetcode.type1;

/**
 * @author liuzhen.tian
 * @version $ Id: Test.java, v 0.1 2020/7/17 18:05 liuzhen.tian Exp $
 */
public class ZTest {
    public static void main(String[] args) {
        /**
         这里模拟二叉树

                     treeNode(0)
              left（1）        right(3)
          left（2）                 right(4)
                                      right(5)
                                          right(6)
         */
        TreeNode t = getTreeNode();

        Solution solution = new Solution();
        int run = solution.run(t);
        System.out.println(run);
        System.out.println(solution.count);

    }

    private static TreeNode getTreeNode() {
        TreeNode t = new TreeNode(0);
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        //左
        t.left = t1;
        t1.left = t2;
        //右
        t.right = t3;
        t3.right = t4;
        t4.right = t5;
        t5.right = t6;
        return t;
    }
}
