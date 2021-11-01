package com.aaa.javabase.pattern.behavior.chain.demo2.impl;

import com.aaa.javabase.pattern.behavior.chain.demo2.AbstractLoggerChain;

/**
 * error 日志
 *
 * @author liuzhen.tian
 * @version 1.0 ErrorLogger.java  2021/11/1 21:19
 */
public class ErrorLogger extends AbstractLoggerChain {
    public ErrorLogger(int level) {
        super(level);
    }

    @Override
    public void print(String msg) {
        System.out.println("error msg = " + msg);
    }
}
