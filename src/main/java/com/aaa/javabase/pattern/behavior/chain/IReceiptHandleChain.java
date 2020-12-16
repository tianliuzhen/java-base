package com.aaa.javabase.pattern.behavior.chain;

import com.aaa.javabase.pattern.behavior.chain.model.Receipt;

/**
 * 责任链接口
 * @author liuzhen.tian
 * @version 1.0 IReceiptHandleChain.java  2020/11/10 11:34
 */
public interface IReceiptHandleChain {
    void handleReceipt(Receipt receipt);
}
