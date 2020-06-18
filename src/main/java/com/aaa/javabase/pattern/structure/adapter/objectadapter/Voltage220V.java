package com.aaa.javabase.pattern.structure.adapter.objectadapter;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/17
 */
public  class Voltage220V {
    //输出220v 的电压
    public int  output220v() {
        int src = 220;
        System.out.println("原始电压是："+src+"伏");
        return src;
    }
}
