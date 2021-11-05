package com.aaa.javabase.pattern.behavior.chain.demo1.impl;

import com.aaa.javabase.pattern.behavior.chain.demo1.IReceiptHandleChain;
import com.aaa.javabase.pattern.behavior.chain.demo1.model.Receipt;
import org.apache.commons.lang3.StringUtils;

/**
 * @author liuzhen.tian
 * @version 1.0 Mt8104ReceiptHandler.java  2020/11/10 11:37
 */
public class Mt8104ReceiptHandler implements IReceiptHandler {

    @Override
    public void handleReceipt(Receipt receipt, IReceiptHandleChain handleChain) {
        if (StringUtils.equals("MT8104",receipt.getType())) {
            System.out.println("解析报文MT8104:" + receipt.getMessage());
        }
        //处理不了该回执就往下传递
        else {
            handleChain.handleReceipt(receipt);
        }
    }
}
