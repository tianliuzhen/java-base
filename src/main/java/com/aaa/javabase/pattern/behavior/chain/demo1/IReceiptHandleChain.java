package com.aaa.javabase.pattern.behavior.chain.demo1;

import com.aaa.javabase.pattern.behavior.chain.demo1.model.Receipt;

/**
 * 责任链接口
 * @author liuzhen.tian
 * @version 1.0 IReceiptHandleChain.java  2020/11/10 11:34
 */
public interface IReceiptHandleChain {
    void handleReceipt(Receipt receipt);
}
