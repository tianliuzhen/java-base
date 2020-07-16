package com.aaa.javabase.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0    2020/7/16 21:17
 */
@Data
@Component
@Slf4j
public class GoodsService {
    private String goodsName="goods";

    public GoodsService() {
        log.info("GoodsService begin ...");
    }
}
