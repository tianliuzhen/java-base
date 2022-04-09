package com.aaa.javabase.pattern.structure.bridging.demo1.channel;

import com.aaa.javabase.pattern.structure.bridging.demo1.mode.IPayMode;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * 微信付款
 * @author liuzhen.tian
 * @version 1.0 WxPayChannel.java  2022/4/9 12:22
 */
@Slf4j
public class WxPayChannel extends PayChannel {
    public WxPayChannel(IPayMode iPayMode) {
        super(iPayMode);
    }

    @Override
    public String payment(String uid, String tradeId, BigDecimal money) {
        log.info("模拟微信风险支付校验开始...");
        boolean securityCheck = iPayMode.securityCheck(uid);
        if (!securityCheck) {
            log.info("模拟微信风险支付校验error...");
            return "error";
        }
        log.info("模拟微信风险支付校验success...");
        return "success";
    }
}
