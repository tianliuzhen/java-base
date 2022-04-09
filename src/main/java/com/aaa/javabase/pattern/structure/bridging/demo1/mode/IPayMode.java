package com.aaa.javabase.pattern.structure.bridging.demo1.mode;

/**
 * 支付接口
 *
 * @author liuzhen.tian
 * @version 1.0 Pay.java  2022/4/9 11:45
 */
public interface IPayMode {

    /**
     * 支付检查校验
     *
     * @return boolean
     */
    boolean securityCheck(String uid);
}
