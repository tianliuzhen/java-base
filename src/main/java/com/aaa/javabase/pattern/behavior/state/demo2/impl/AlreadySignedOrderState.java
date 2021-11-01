package com.aaa.javabase.pattern.behavior.state.demo2.impl;

import com.aaa.javabase.pattern.behavior.state.demo2.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 订单签收 service
 *
 * @author liuzhen.tian
 * @version 1.0 AlreadySignedOrderState.java  2021/11/1 22:34
 */
@Slf4j
@Component
public class AlreadySignedOrderState implements OrderState {
    @Override
    public Object handle(String orderId) {
        log.info(" orderId：{} 切换已经签收状态...", orderId);
        return "切换已经签收状态";
    }
}
