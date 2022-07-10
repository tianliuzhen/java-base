package com.aaa.javabase.base.类加载器.impl;

import com.aaa.javabase.pattern.behavior.strategy.handler.ChangeShippingSolver;

/**
 * @author liuzhen.tian
 * @version 1.0 UserServiceImpl.java  2022/7/10 10:07
 */
public class UserServiceImpl {
    public String getUser(String userId) {
        if (userId == "") {
            return "";
        }
        ChangeShippingSolver changeShippingSolver = new ChangeShippingSolver();

        return changeShippingSolver.supports().getCode();
    }

}
