package com.aaa.javabase.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 统一的日志管理
 *
 * @author liuzhen.tian
 * @version 1.0 LoggerUtil.java  2023/4/28 21:18
 */
public class LogUtil {
    /**
     * dal 层日志
     */
    public static Logger com_dal = LogManager.getLogger("com-dal");
    /**
     * web 层日志
     */
    public static Logger com_web = LogManager.getLogger("com-web");

    /**
     * 统一封装 isInfoEnabled
     *
     * @param logger
     * @param msg
     */
    public static void info(Logger logger, String msg) {
        if (logger.isInfoEnabled()) {
            logger.info(msg);
        }
    }
}
