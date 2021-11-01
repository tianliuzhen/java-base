package com.aaa.javabase.pattern.behavior.chain.demo2;

import com.aaa.javabase.pattern.behavior.chain.demo2.impl.ErrorLogger;
import com.aaa.javabase.pattern.behavior.chain.demo2.impl.InfoLogger;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2021/11/1 21:31
 */
public class TestMain {
    public static void main(String[] args) {
        //获取对象责任链
        AbstractLoggerChain infoLogger = getInfoLogger();

        infoLogger.doAction(AbstractLoggerChain.INFO, "info");
        infoLogger.doAction(AbstractLoggerChain.ERROR, "error");
    }

    /**
     * 获取对象责任链
     */
    public static AbstractLoggerChain getInfoLogger() {
        InfoLogger infoLogger = new InfoLogger(AbstractLoggerChain.INFO);
        ErrorLogger errorLogger = new ErrorLogger(AbstractLoggerChain.ERROR);
        infoLogger.setNextLogger(errorLogger);
        return infoLogger;
    }
}
