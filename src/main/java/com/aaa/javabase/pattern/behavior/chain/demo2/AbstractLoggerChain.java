package com.aaa.javabase.pattern.behavior.chain.demo2;

import com.aaa.javabase.pattern.behavior.chain.demo2.impl.PrintLogger;
import lombok.extern.slf4j.Slf4j;

/**
 * 抽象的日志类
 *
 * @author liuzhen.tian
 * @version 1.0 AbstractLoggerChain.java  2021/11/1 21:15
 */
@Slf4j
public abstract class AbstractLoggerChain implements PrintLogger {

    public static int INFO = 0;
    public static int WARN = 1;
    public static int ERROR = 2;

    // 下一个责任链
    private AbstractLoggerChain nextLogger;

    // 日志等级
    protected int level;

    public AbstractLoggerChain(int level) {
        this.level = level;
    }

    public void setNextLogger(AbstractLoggerChain nextLogger) {
        this.nextLogger = nextLogger;
    }

    // 发起请求，开始流转
    public void doAction(int level, String msg) {
        // 匹配到 level
        if (level == this.level) {
            this.print(msg);
        } else {
            if (nextLogger != null) {
                // 递归调用下一个对象
                nextLogger.doAction(level, msg);
            } else {
                log.error("当所有处理者对象均不能处理该请求时输出");
            }
        }
    }


}
