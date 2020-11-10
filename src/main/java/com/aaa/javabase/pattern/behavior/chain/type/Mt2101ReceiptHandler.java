package com.aaa.javabase.pattern.behavior.chain.type;

import com.aaa.javabase.pattern.behavior.chain.IReceiptHandleChain;
import com.aaa.javabase.pattern.behavior.chain.IReceiptHandler;
import com.aaa.javabase.pattern.behavior.chain.model.Receipt;
import org.apache.commons.lang3.StringUtils;

/**
 * @author liuzhen.tian
 * @version 1.0 Mt2101ReceiptHandler.java  2020/11/10 11:35
 */
public class Mt2101ReceiptHandler implements IReceiptHandler {

    @Override
    public void handleReceipt(Receipt receipt, IReceiptHandleChain handleChain) {
        if (StringUtils.equals("MT2101",receipt.getType())) {
            System.out.println("解析报文MT2101:" + receipt.getMessage());
        }
        //处理不了该回执就往下传递
        else {
            handleChain.handleReceipt(receipt);
        }
    }
}
