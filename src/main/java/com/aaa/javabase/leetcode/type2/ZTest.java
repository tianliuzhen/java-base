package com.aaa.javabase.leetcode.type2;

/**
 * @author liuzhen.tian
 * @version $ Id: ZTest.java, v 0.1 2020/7/17 18:08 liuzhen.tian Exp $
 */
public class ZTest {
    public static void main(String[] args) {
        /**
         * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
         * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
         */
        Solution solution = new Solution();
        String[] str = {"2", "1", "+", "3", "*"};
        int i = solution.evalRPN(str);
        System.out.println(i);
    }
}
