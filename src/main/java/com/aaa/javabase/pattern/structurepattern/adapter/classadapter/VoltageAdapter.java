package com.aaa.javabase.pattern.structurepattern.adapter.classadapter;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/17
 */
public class VoltageAdapter extends Voltage220V implements Voltage5v {
    @Override
    public int output5v() {
        //获取220 电压
        int src=output220v();
        int dstv=src / 44;
        return dstv;
    }
}
