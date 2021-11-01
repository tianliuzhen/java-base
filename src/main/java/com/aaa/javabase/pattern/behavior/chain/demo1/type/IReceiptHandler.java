package com.aaa.javabase.pattern.behavior.chain.demo1.type;

import com.aaa.javabase.pattern.behavior.chain.demo1.IReceiptHandleChain;
import com.aaa.javabase.pattern.behavior.chain.demo1.model.Receipt;

/**
 * 抽象回执处理者接口
 * @author liuzhen.tian
 * @version 1.0 IReceiptHandler.java  2020/11/10 11:32
 */
public interface IReceiptHandler {
    void handleReceipt(Receipt receipt, IReceiptHandleChain handleChain);
}
