package com.aaa.javabase.pattern.structurepattern.adapter.interfaceadapter;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/17
 */
public class AbsAdapterImpl extends  AbsAdapter {
    @Override
    public void m2() {
        System.out.println("使用了m2的方法");
        super.m2();
    }
}
