package com.aaa.javabase.multithreading.任务拆分;

/**
 * @author liuzhen.tian
 * @version 1.0 TestHashMap.java  2024/4/4 14:55
 */
public class TestHashMap {
    public static void main(String[] args) {
        // (n-1) & hash 不等于 hash % n
        System.out.println("i = (n - 1) & hash");
        System.out.println(7 & 6);  // 6
        System.out.println(8 % 6);  // 2
        System.out.println(15 & 6); // 6
        System.out.println(16 % 6); // 4
        System.out.println("++++++++++++++++++++");
        // hash & (n-1) 等于 n % hash
        System.out.println(6 & 7);  // 6
        System.out.println(6 % 8);  // 6
        System.out.println(6 & 15); // 6
        System.out.println(6 % 16); // 6
        System.out.println("++++++++++++++++++++");

        /*
         * 抽象成计算式：X % 2n = X & (2n - 1)  todo 注意不是 2n % X  =   (2n - 1) & X
         * 1、名词 解释
         * 【取模运算 X % 2n】：
         *   X % 2^n：这个操作是找出 X 除以 2^n 之后的余数。
         *   比如说，如果我们有 X = 10 和 n = 3，那么 10 % 8（因为 2^3 = 8）的结果就是 2，因为 10 除以 8 的余数是 2
         * 【位与运算 X & (2^n - 1)】
         *   这个操作是将 X 的二进制表示与 2^n - 1 的二进制表示进行逐位比较，只有当两个相应的位都是 1 时，结果的相应位才是 1
         *   比如，2^3 - 1 等于 7，其二进制是 0111。
         *   如果我们与 10（二进制为 1010）进行按位与运算，结果是 0010，即 2
         *   1010
         *   0111 &
         *   0010 => 2 （本质上也是取小的余数）
         *
         * 2、为什么这两个操作在某些情况下会给出相同的结果？（from 文心一言）
         *  当我们对一个数 X 进行 X % 2^n 操作时，我们实际上是在找出 X 除以 2^n 的余数。
         *  这个余数只与 X 的最低n位有关，因为 2^n 的二进制表示中只有最低n位是1。
         *
         *  类似地，当我们对 X 进行 X & (2^n - 1) 操作时，我们实际上是在保留 X 的最低n位，并将其余位设置为0。
         *  这是因为 2^n - 1 的二进制表示中只有最低n位是1。
         *
         *  因此，无论是进行取模运算还是按位与运算，我们都是在处理 X 的最低n位。
         *  这就是为什么这两个操作在某些情况下会给出相同的结果
         *
         * @param args
         */
        System.out.println(17 % 8);
        System.out.println(17 & 7);

        System.out.println(32 % 33);
        System.out.println(1667 & 15);
    }
}
