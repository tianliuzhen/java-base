package com.aaa.javabase.pattern.behavior.chain;

import com.aaa.javabase.pattern.behavior.chain.type.Mt2101ReceiptHandler;
import com.aaa.javabase.pattern.behavior.chain.type.Mt8104ReceiptHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理者容器
 * @author liuzhen.tian
 * @version 1.0 ReceiptHandlerContainer.java  2020/11/10 11:38
 */
public class ReceiptHandlerContainer {

    private ReceiptHandlerContainer(){}

    public static List<IReceiptHandler> getReceiptHandlerList(){
        List<IReceiptHandler> receiptHandlerList = new ArrayList<>();
        receiptHandlerList.add(new Mt2101ReceiptHandler());
        receiptHandlerList.add(new Mt8104ReceiptHandler());
        return receiptHandlerList;
    }

}
