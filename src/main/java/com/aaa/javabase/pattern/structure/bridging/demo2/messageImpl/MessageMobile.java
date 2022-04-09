package com.aaa.javabase.pattern.structure.bridging.demo2.messageImpl;

/**
 * 手机消息
 *
 * @author liuzhen.tian
 * @version 1.0 MessageMobile.java  2022/4/9 13:18
 */
public class MessageMobile implements MessageImplementor {
    @Override
    public void send() {
        System.out.println("手机 消息发送");
    }
}
