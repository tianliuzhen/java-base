package com.aaa.javabase.pattern.behavior.chain;

import com.aaa.javabase.pattern.behavior.chain.model.Receipt;

/**
 * @author liuzhen.tian
 * @version 1.0 TestClient.java  2020/11/10 11:38
 */
public class TestClient {
    public static void main(String[] args) {

        //回执处理链对象
        ReceiptHandleChain receiptHandleChain = new ReceiptHandleChain();

        //模拟回执  MT2101
        // Receipt receipt=new Receipt("msg","MT2101");
        // receiptHandleChain.handleReceipt(receipt);

        //模拟回执  MT8104
        Receipt receipt2=new Receipt("msg","MT8104");
        receiptHandleChain.handleReceipt(receipt2);
    }
}
