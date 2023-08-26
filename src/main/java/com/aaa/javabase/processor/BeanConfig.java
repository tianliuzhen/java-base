package com.aaa.javabase.processor;

import com.aaa.javabase.spring.factoryBean.FruitFactoryBean;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuzhen.tian
 * @version 1.0    2020/7/14 21:10
 */
// @Component
@Configuration
public class BeanConfig {

    @Autowired
    private FruitFactoryBean fruitFactoryBean;


    @Bean
    public TestBean testBean01(FruitFactoryBean fruitFactoryBean) {
        return new TestBean(fruitFactoryBean);
    }

    @Bean
    public TestBean02 testBean02() {
        testBean01(fruitFactoryBean);//这里也会调用testBean方法。
        return new TestBean02();
    }

    @AllArgsConstructor
    public class TestBean {
        private FruitFactoryBean testBeanReq;
        public TestBean() {
            System.err.println("TestBean.**************");
        }
    }

    public class TestBean02 {
    }


}
