package com.aaa.javabase.pattern.structure.bridging.demo1.mode;

import lombok.extern.slf4j.Slf4j;

/**
 * 刷脸校验
 *
 * @author liuzhen.tian
 * @version 1.0 PayFaceMode.java  2022/4/9 11:53
 */
@Slf4j
public class PayFaceMode implements IPayMode {
    @Override
    public boolean securityCheck(String uid) {
        log.info(uid + "：刷脸校验通过");
        return false;
    }
}
