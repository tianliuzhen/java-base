package com.aaa.javabase.pattern.behavior.chain.demo2.impl;

import com.aaa.javabase.pattern.behavior.chain.demo2.AbstractLoggerChain;

/**
 * info 日志
 *
 * @author liuzhen.tian
 * @version 1.0 InfoLogger.java  2021/11/1 21:15
 */
public class InfoLogger extends AbstractLoggerChain {

    public InfoLogger(int level) {
        super(level);
    }

    @Override
    public void print(String msg) {
        System.out.println("info msg = " + msg);
    }
}
