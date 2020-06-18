package com.aaa.javabase.pattern.structure.adapter.objectadapter;


/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/17
 */
public class VoltageAdapter  implements Voltage5v {
    private Voltage220V voltage220V;
    @Override
    public int output5v() {
        //获取220 电压
        int src=voltage220V.output220v();
        int dstv=src / 44;
        return dstv;
    }

    public VoltageAdapter(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }
}
