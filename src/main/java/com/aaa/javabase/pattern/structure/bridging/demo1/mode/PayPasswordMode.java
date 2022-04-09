package com.aaa.javabase.pattern.structure.bridging.demo1.mode;

import lombok.extern.slf4j.Slf4j;

/**
 * 密码校验
 *
 * @author liuzhen.tian
 * @version 1.0 PayPasswordMode.java  2022/4/9 11:55
 */
@Slf4j
public class PayPasswordMode implements IPayMode {
    @Override
    public boolean securityCheck(String uid) {
        log.info(uid + "：刷脸校验通过");
        return false;
    }
}
