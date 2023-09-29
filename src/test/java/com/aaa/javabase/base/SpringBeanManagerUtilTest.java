package com.aaa.javabase.base;

import com.aaa.javabase.util.SpringContextUtil;
import com.aaa.javabase.util.SpringBeanManagerUtil;
import com.aaa.javabase.web.excel.model.ImageDemoData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author liuzhen.tian
 * @version 1.0 SpringBeanManagerUtilTest.java  2022/12/31 15:45
 */
@SpringBootTest
public class SpringBeanManagerUtilTest {

    @Autowired
    private SpringBeanManagerUtil springBeanManagerUtil;

    @Test
    public void registerSpringBean() {
        // 指定bean加载
        springBeanManagerUtil.registerSpringBean(ImageDemoData.class);
        // 读取bean是否生效
        SpringContextUtil.getBean(ImageDemoData.class);
        // 指定bean卸载
        springBeanManagerUtil.deleteSpringBean(ImageDemoData.class);
        // 此时会报错，此bean已被移除
        SpringContextUtil.getBean(ImageDemoData.class);
    }

}
