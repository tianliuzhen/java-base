package com.aaa.javabase.pattern.behavior.state.demo2.impl;

import com.aaa.javabase.pattern.behavior.state.demo2.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 ShippedAlreadyOrderState.java  2021/11/1 22:35
 */
@Slf4j
@Component
public class ShippedAlreadyOrderState implements OrderState {
    @Override
    public Object handle(String orderId) {
        log.info(" orderId：{} 切换为已经发货状态...", orderId);
        return "已经发货..";
    }
}
