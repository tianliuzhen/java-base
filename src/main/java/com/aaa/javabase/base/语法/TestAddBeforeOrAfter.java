package com.aaa.javabase.base.语法;

/**
 * 再次理解一下 i++ 和 ++i
 *
 * @author liuzhen.tian
 * @version 1.0 TestIPlus.java  2022/6/16 22:01
 */
public class TestAddBeforeOrAfter {

    public static void main(String[] args) {
        // ##1、字面意思：
        int a = 0, b = 0;
        // ++ 在后表示，先输出值再计算
        System.out.println("a++ : " + a++); //  0
        System.out.println(addAfter(0)); //  0

        System.out.println("----------------------------------");

        // ++ 在前表示，先计算再输出值
        System.out.println("++b : " + ++b); //  1
        System.out.println(addBefore(0)); //  1

        System.out.println("----------------------------------");

        // ##2、使用场景区别：
        // ###2.1 相同点、
        // i++和++i都是变量自增1，都等价于i=i+1；
        // 如果i++，++i是一条单独的语句，两者没有任何区别
        for (int i = 0; i < 2; i++) {
            System.out.println(i);
        }
        System.out.println("----------------------------------");
        for (int i = 0; i < 2; ++i) {
            System.out.println(i);
        }
        // ###2.2 不同点、
        // 如果i++ 和++ i不是一条单独的语句，它们就有区别，i++ 是先运算后再增1；

        // ##3、jvm 层面了解去了解 i++ 和 ++i 执行区别
    }

    public static int addAfter(int i) {
        int temp_i = i;
        temp_i = temp_i + 1;
        return i;
    }

    public static int addBefore(int i) {
        i = i + 1;
        return i;
    }

}
