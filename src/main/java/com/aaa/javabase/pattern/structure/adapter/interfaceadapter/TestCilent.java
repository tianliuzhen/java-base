package com.aaa.javabase.pattern.structure.adapter.interfaceadapter;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/17
 */
public class TestCilent {
    public static void main(String[] args) {
        AbsAdapter a=  new AbsAdapter(){
          // 我们自需要覆盖我们需要使用的方法

            @Override
            public void m1() {
                System.out.println("使用了m1的方法");
                super.m1();
            }
        };
        a.m1();
        AbsAdapterImpl a2=  new AbsAdapterImpl();
        a2.m2();
    }
}
