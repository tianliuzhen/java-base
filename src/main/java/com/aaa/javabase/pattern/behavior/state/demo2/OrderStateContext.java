package com.aaa.javabase.pattern.behavior.state.demo2;

/**
 * @author liuzhen.tian
 * @version 1.0 OrderStateContext.java  2021/11/1 22:36
 */
public class OrderStateContext {

    // 订单状态对象
    private OrderState orderState;

    /**
     * 执行订单不同状态处理的方法
     */
    public void doAction(String orderId) {
        orderState.handle(orderId);
    }


    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }
}
