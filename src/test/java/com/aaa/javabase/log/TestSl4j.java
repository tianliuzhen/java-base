package com.aaa.javabase.log;

import lombok.extern.log4j.Log4j2;

/**
 * @author liuzhen.tian
 * @version 1.0 TestSl4j.java  2022/5/28 21:11
 */
@Log4j2
public class TestSl4j {
    public static void main(String[] args) {
        try {
            // 模拟空指针异常
            //Integer nullInt = Integer.valueOf(null);
            int[] array = {1, 2, 3, 4, 5};
            int outBoundInt = array[5];
        } catch (Exception e) {
            // 直接打印，则只输出异常类型
            log.error(e.getMessage());
            // 使用字符串拼接
            log.error("使用 + 号链接直接输出 e : " + e);
            log.error("使用 + 号链接直接输出 e.getMessage() : " + e.getMessage());
            log.error("使用 + 号链接直接输出 e.toString() : " + e.toString());
            // 使用逗号分隔，调用两个参数的error方法
            log.error("使用 , 号 使第二个参数做为Throwable : ", e);
            // 尝试使用分隔符,第二个参数为Throwable,会发现分隔符没有起做用，第二个参数的不一样据，调用不一样的重载方法
            log.error("第二个参数为Throwable，使用分隔符打印 {} : ", e);
            // 尝试使用分隔符，第二个参数为Object,会发现分隔符起做用了，根据第二个参数的不一样类型，调用不一样的重载方法
            log.error("第二个参数为Object，使用分隔符打印 {} ", 123);
        }
    }
}
