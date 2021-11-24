package com.aaa.javabase.spring.initBean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 MyInitializingBean.java  2021/11/14 23:24
 */
@Component
public class MyInitializingBean implements InitializingBean {

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 初始化bean
        System.out.println();
    }
}
