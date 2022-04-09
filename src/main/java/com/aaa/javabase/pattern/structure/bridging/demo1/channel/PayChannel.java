package com.aaa.javabase.pattern.structure.bridging.demo1.channel;

import com.aaa.javabase.pattern.structure.bridging.demo1.mode.IPayMode;

import java.math.BigDecimal;

/**
 * 支付渠道
 *
 * @author liuzhen.tian
 * @version 1.0 PayChannel.java  2022/4/9 12:11
 */
public abstract class PayChannel {
    protected IPayMode iPayMode;

    public PayChannel(IPayMode iPayMode) {
        this.iPayMode = iPayMode;
    }

    /**
     * 付款
     *
     * @param uid     uid
     * @param tradeId 交易id
     * @param money   金额
     * @return String
     */
    public abstract String payment(String uid, String tradeId, BigDecimal money);
}
