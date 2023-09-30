package com.aaa.javabase.base;

import com.aaa.javabase.util.spring.SpringBeanManagerUtil;
import com.aaa.javabase.util.spring.SpringUtil;
import com.aaa.javabase.util.spring.SpringUtilV1;
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
    @Autowired
    private SpringUtil springUtil;

    @Test
    public void registerSpringBean() {
        // 指定bean加载
        springBeanManagerUtil.registerSpringBean(ImageDemoData.class);
        // 读取bean是否生效
        ImageDemoData bean = SpringUtilV1.getBean(ImageDemoData.class);
        // 指定bean卸载
        springBeanManagerUtil.deleteSpringBean(ImageDemoData.class);
        // 此时会报错，此bean已被移除
        SpringUtilV1.getBean(ImageDemoData.class);
    }

    //  ******************* 推荐 *******************
    @Test
    public void registerBeanWithAnnotated() {
        // 指定bean加载
        SpringBeanManagerUtil.registerBeanWithAnnotated(ImageDemoData.class, null);
        // 读取bean是否生效
        ImageDemoData bean = SpringUtilV1.getBean(ImageDemoData.class);
        // 指定bean卸载
        springBeanManagerUtil.deleteSpringBean(ImageDemoData.class);
        // 此时会报错，此bean已被移除
        SpringUtilV1.getBean(ImageDemoData.class);
    }

    //  ******************* 推荐 ******************* 这种会丢失很多spring本身属性
    @Test
    public void registerBean() {
        // 指定bean加载
        springUtil.registerBean("imageDemoData", new ImageDemoData());
        // 读取bean是否生效
        ImageDemoData bean = SpringUtilV1.getBean(ImageDemoData.class);
        ImageDemoData bean2 = SpringUtilV1.getBean(ImageDemoData.class);
        // 指定bean卸载
        springUtil.unregisterBean("imageDemoData");
        // 此时会报错，此bean已被移除
        SpringUtilV1.getBean(ImageDemoData.class);
    }

}
