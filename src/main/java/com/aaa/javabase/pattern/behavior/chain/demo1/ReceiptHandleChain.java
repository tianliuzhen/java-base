package com.aaa.javabase.pattern.behavior.chain.demo1;

import com.aaa.javabase.pattern.behavior.chain.demo1.model.Receipt;
import com.aaa.javabase.pattern.behavior.chain.demo1.type.IReceiptHandler;

import java.util.List;

/**
 * 责任链实现类
 * @author liuzhen.tian
 * @version 1.0 ReceiptHandleChain.java  2020/11/10 11:34
 */
public class ReceiptHandleChain implements IReceiptHandleChain {
    //记录当前处理者位置
    private int index = 0;
    //处理者集合
    private static List<IReceiptHandler> receiptHandlerList;

    static {
        //从容器中获取处理器对象
        receiptHandlerList = ReceiptHandlerContainer.getReceiptHandlerList();
    }

    @Override
    public void handleReceipt(Receipt receipt) {
        if (receiptHandlerList !=null && receiptHandlerList.size() > 0) {
            if (index != receiptHandlerList.size()) {
                IReceiptHandler receiptHandler = receiptHandlerList.get(index++);
                receiptHandler.handleReceipt(receipt,this);
            }
        }
    }
}
