package com.aaa.javabase.pattern.structure.adapter.objectadapter;


/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/17
 */
public class TestClient {
    public static void main(String[] args) {
        System.out.println(" === 对象适配器模式 === ");
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter(new Voltage220V()));



    }
}
