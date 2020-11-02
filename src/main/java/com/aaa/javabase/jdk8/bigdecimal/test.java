package com.aaa.javabase.jdk8.bigdecimal;

import java.math.BigDecimal;

/**
 * @author liuzhen.tian
 * @version 1.0 test.java  2020/11/2 15:46
 */
public class test {
    public static void main(String[] args) {

        // ROUND_CEILING  向正无穷方向舍入
        System.out.println(BigDecimal.valueOf(10.117).setScale(2, BigDecimal.ROUND_CEILING));
        //ROUND_FLOOR  向零方向舍入
        System.out.println(BigDecimal.valueOf(-10.117).setScale(2, BigDecimal.ROUND_FLOOR));
        System.out.println("---------------------");

        //ROUND_DOWN  向零方向舍入
        System.out.println(BigDecimal.valueOf(10.117).setScale(2, BigDecimal.ROUND_DOWN));
        //ROUND_DOWN  向远离零方向舍入
        System.out.println(BigDecimal.valueOf(10.117).setScale(2, BigDecimal.ROUND_UP));
        System.out.println("---------------------");

        //ROUND_HALF_DOWN  (五舍六入)
        System.out.println(BigDecimal.valueOf(10.115).setScale(2, BigDecimal.ROUND_HALF_DOWN));
        //ROUND_HALF_UP (四舍五入)
        System.out.println(BigDecimal.valueOf(10.115).setScale(2, BigDecimal.ROUND_HALF_UP));
        System.out.println("---------------------");

        //ROUND_HALF_EVEN  (向（距离）最近的一边舍入) 银行家算法
        //四舍六入，五看前一位是偶数舍，奇数进位
        System.out.println(BigDecimal.valueOf(10.115).setScale(2, BigDecimal.ROUND_HALF_EVEN ));
        System.out.println("---------------------");

        //ROUND_UNNECESSARY   (计算结果是精确的，不需要舍入模式 )
        System.out.println(BigDecimal.valueOf(10.115).setScale( BigDecimal.ROUND_UNNECESSARY ));

    }
}
