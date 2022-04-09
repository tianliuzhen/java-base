package com.aaa.javabase.pattern.structure.bridging.demo2.messageImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * SMS 消息
 *
 * @author liuzhen.tian
 * @version 1.0 MessageSMS.java  2022/4/9 13:18
 */
@Slf4j
public class MessageSMS implements MessageImplementor {
    @Override
    public void send() {
        System.out.println("sms 消息发送");
    }
}
