package com.aaa.javabase.leetcode.type2;

/**
 * @author liuzhen.tian
 * @version $ Id: Solution.java, v 0.1 2020/7/17 18:05 liuzhen.tian Exp $
 */
import java.util.*;


public class Solution {
    /**
     *
     * @param tokens string字符串一维数组
     * @return int整型
     */
    public int evalRPN (String[] tokens) {
        // write code here
        Stack<Integer> stack = new Stack<>();
        int op1=0;
        int op2=0;
        for(String s:tokens){
            switch(s){
                case "+":
                    op2=stack.pop();
                    op1=stack.pop();
                    stack.push(op1+op2);
                    break;
                case "-":
                    op2=stack.pop();
                    op1=stack.pop();
                    stack.push(op1-op2);
                    break;
                case "*":
                    op2=stack.pop();
                    op1=stack.pop();
                    stack.push(op1*op2);
                    break;
                case "/":
                    op2=stack.pop();
                    op1=stack.pop();
                    stack.push(op1/op2);
                    break;
                default:
                    stack.push(Integer.valueOf(s));
                    break;
            }
        }
        return stack.pop();
    }
}
