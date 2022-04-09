package com.aaa.javabase.pattern.structure.bridging.demo2;

import com.aaa.javabase.pattern.structure.bridging.demo2.messageImpl.MessageMobile;
import com.aaa.javabase.pattern.structure.bridging.demo2.messageImpl.MessageSMS;
import com.aaa.javabase.pattern.structure.bridging.demo2.messageType.AbstractMessage;
import com.aaa.javabase.pattern.structure.bridging.demo2.messageType.CommonMessage;
import com.aaa.javabase.pattern.structure.bridging.demo2.messageType.SpecialUrgentlyMessage;
import com.aaa.javabase.pattern.structure.bridging.demo2.messageType.UrgentlyMessage;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2022/4/9 13:38
 */
public class TestMain {
    public static void main(String[] args) {
        //sms消息 场景1: 发送普通sms消息
        AbstractMessage messageSMS = new CommonMessage(new MessageSMS());
        messageSMS.sendMessage();

        // sms消息 场景2: 加急普通sms消息
        UrgentlyMessage sms2 = new UrgentlyMessage(new MessageSMS());
        sms2.sendMessage();
        sms2.watch();

        System.out.println("---------------------");

        // 手机消息 场景1: 发送普通sms消息
        UrgentlyMessage mobile = new UrgentlyMessage(new MessageMobile());
        mobile.sendMessage();

        // 手机消息 场景2: 加急普通sms消息
        SpecialUrgentlyMessage mobile2 = new SpecialUrgentlyMessage(new MessageMobile());
        mobile2.sendMessage();
        mobile2.getResult();


    }
}
