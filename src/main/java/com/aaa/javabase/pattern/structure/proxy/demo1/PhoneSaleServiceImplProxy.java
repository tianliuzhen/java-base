package com.aaa.javabase.pattern.structure.proxy.demo1;

/**
 * 代理对象
 *
 * @author liuzhen.tian
 * @version 1.0 PhoneSaleServiceImplProxy.java  2022/9/21 19:47
 */
public class PhoneSaleServiceImplProxy implements PhoneService {
    // 被代理对象
    private PhoneService phoneService;

    public PhoneSaleServiceImplProxy() {
        this.phoneService = new PhoneServiceImpl();
    }


    @Override
    public void service() {
        phoneService.service();
        System.out.println("代理对象帮贴膜...");
    }
}
