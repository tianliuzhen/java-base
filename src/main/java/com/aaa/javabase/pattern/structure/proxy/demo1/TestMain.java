package com.aaa.javabase.pattern.structure.proxy.demo1;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2022/9/21 19:49
 */
public class TestMain {
    public static void main(String[] args) {
        PhoneSaleProxy phoneSaleServiceImplProxy = new PhoneSaleProxy(new PhoneServiceImplV2());
        phoneSaleServiceImplProxy.service();
    }
}
