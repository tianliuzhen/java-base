package com.aaa.javabase.pattern.behavior.state.demo2;

/**
 * @author liuzhen.tian
 * @version 1.0 OrderState.java  2021/11/1 22:16
 */
public interface OrderState {

    /**
     * 订单状态执行方法
     *
     * @param orderId 订单id
     */
    Object handle(String orderId);
}
