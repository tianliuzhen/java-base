package com.aaa.javabase.pattern.structure.bridging.demo1;

import com.aaa.javabase.pattern.structure.bridging.demo1.channel.AlipayPayChannel;
import com.aaa.javabase.pattern.structure.bridging.demo1.channel.WxPayChannel;
import com.aaa.javabase.pattern.structure.bridging.demo1.mode.PayFaceMode;
import com.aaa.javabase.pattern.structure.bridging.demo1.mode.PayPasswordMode;

import java.math.BigDecimal;

/**
 * @author liuzhen.tian
 * @version 1.0 ZtestMain.java  2022/4/9 12:23
 */
public class TestMain {
    public static void main(String[] args) {
        // 模拟微信支付
        WxPayChannel wxPayChannel = new WxPayChannel(new PayFaceMode());
        wxPayChannel.payment("111", "20220409", BigDecimal.valueOf(123.00));

        System.out.println("------------------");

        // 模拟支付宝支付
        AlipayPayChannel alipayPayChannel = new AlipayPayChannel(new PayPasswordMode());
        alipayPayChannel.payment("111", "20220409", BigDecimal.valueOf(123.00));
    }
}
