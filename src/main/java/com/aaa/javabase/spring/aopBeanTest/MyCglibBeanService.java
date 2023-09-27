package com.aaa.javabase.spring.aopBeanTest;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liuzhen.tian
 * @version 1.0 MyCglibBean.java  2023/9/17 14:59
 */
@Transactional
@Component
public class MyCglibBeanService implements MyCglibInterface {

    @Override
    public void test() {

    }
}
