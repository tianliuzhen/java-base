package com.aaa.javabase.leetcode.type3;

/**
 * 一个人爬楼梯，楼梯共20层，一人一步可以走一层或两层，共多少种走法？
 * @author liuzhen.tian
 * @version 1.0 TestStair.java  2020/11/6 15:58
 */
public class TestStair {

    public static void main(String[] args) {
        System.out.println(getUpStairTypes(20));
    }
    /**
     * 递归的解法
     */
    public static int getUpStairTypes(int n){
        if ((n==1||n==2)) {
            return n;
        }
        return getUpStairTypes(n - 1 )+ getUpStairTypes(n - 2);
    }

    /**
     * for 的解法
     */
    static int  getUpStairTypes2(int n) {
        if(n <= 2){
            return n;
        }
        int step1 = 1;
        int step2 = 2;
        int sum = 0;
        for(int i = 3; i <= n; i++) {
            sum = step1+step2;
            step1 = step2;
            step2 = sum;
        }
        return sum;
    }
}
