package com.aaa.javabase.pattern.structure.bridging.demo1.channel;

import com.aaa.javabase.pattern.structure.bridging.demo1.mode.IPayMode;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * 支付宝付款
 *
 * @author liuzhen.tian
 * @version 1.0 AlipayPayChannel.java  2022/4/9 12:16
 */
@Slf4j
public class AlipayPayChannel extends PayChannel {

    public AlipayPayChannel(IPayMode iPayMode) {
        super(iPayMode);
    }

    @Override
    public String payment(String uid, String tradeId, BigDecimal money) {

        log.info("模拟支付宝风险支付校验开始...");
        boolean securityCheck = iPayMode.securityCheck(uid);
        if (!securityCheck) {
            log.info("模拟支付宝风险支付校验error...");
            return "error";
        }
        log.info("模拟支付宝风险支付校验success...");
        return "success";
    }
}
