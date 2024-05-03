package com.aaa.javabase.base.堆;

/**
 * @author liuzhen.tian
 * @version 1.0 TestStack.java  2024/4/21 21:00
 */
public class TestStack {
    public static void test19() {
        int a1 = 100;
        int a2 = 200;
        int a3 = 300;
        int a4 = 400;
        int a5 = 500;
        int a6 = 600;
        int a7 = 700;
        int a8 = 800;
        int a9 = 900;
        int a10 = 1000;
        int a11 = 1000;
        if (false) {
            //maxStackSize=5
            int h1 = a1 * (a3 - (a5 + a6 * a7));
        } else {
            //maxStackSize=10
            int h2 = (a1 + (a2 + (a3 + (a4 + (a5 + (a6 + (a7 + (a8 + (a9 + (a10 + a11))))))))));//maxStackSize=5
        }
    }


    /**
     * maxStackSize=4
     *
     * 不同类型的变量在操作数栈上会占用不同数量的栈单位深度
     * 32位的数据类型（如int和float）通常占用一个栈单位深度（slot）
     * 64位的数据类型（如long和double）则占用两个栈单位深度（slot）
     */
    public void testAddOperation(){
        byte i = 1;
        short o = 2;
        int i1 = i + o;

        long a = 12L;
        long n = 800;
        a = a * n;
    }

}
