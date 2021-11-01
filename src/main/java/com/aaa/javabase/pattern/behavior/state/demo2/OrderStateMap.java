package com.aaa.javabase.pattern.behavior.state.demo2;

import com.aaa.javabase.pattern.behavior.state.demo2.impl.AlreadySignedOrderState;
import com.aaa.javabase.pattern.behavior.state.demo2.impl.ShippedAlreadyOrderState;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author liuzhen.tian
 * @version 1.0 OrderStateMap.java  2021/11/1 22:48
 */

@Component
public class OrderStateMap {

    private Map<String, OrderState> map = Maps.newHashMap();

    @Autowired
    private AlreadySignedOrderState alreadySignedOrderState;

    @Autowired
    private ShippedAlreadyOrderState shippedAlreadyOrderState;

    @PostConstruct
    public void initData() {
        map.put("already-order", alreadySignedOrderState);
        map.put("shipped-order", shippedAlreadyOrderState);
    }

    public OrderState get(String code) {
        return map.get(code);
    }
}
