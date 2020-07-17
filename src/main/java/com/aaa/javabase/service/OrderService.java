package com.aaa.javabase.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0    2020/7/14 21:03
 */
@Component
@Slf4j
@Data
public class OrderService {

    private String name;

    public OrderService() {
        log.info("OrderService begin ...");
    }
}
