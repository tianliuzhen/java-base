package com.aaa.javabase.pattern.structure.proxy.demo1;

/**
 * @author liuzhen.tian
 * @version 1.0 PhoneSaleServiceImpl.java  2022/9/21 19:46
 */
public class PhoneServiceImplV2 implements PhoneService {
    @Override
    public void service() {
        System.out.println("手机贴膜...");
    }
}
