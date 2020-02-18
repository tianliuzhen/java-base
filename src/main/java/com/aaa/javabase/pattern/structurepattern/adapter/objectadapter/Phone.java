package com.aaa.javabase.pattern.structurepattern.adapter.objectadapter;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/17
 */
public class Phone {
    public void charging(Voltage5v voltage5v){
        if (voltage5v.output5v() >= 5) {
            System.out.println("电压为5v，可以充电");
        }
    }
}
