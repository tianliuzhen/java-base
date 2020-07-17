package com.aaa.javabase.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liuzhen.tian
 * @version 1.0    2020/7/14 21:01
 */
@Slf4j
@Data
public class UserService {

    private String name;

    public UserService() {
        log.info("UserService begin ...");
    }
}
