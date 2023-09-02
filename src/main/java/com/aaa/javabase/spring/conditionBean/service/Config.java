package com.aaa.javabase.spring.conditionBean.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * description: 测试：注解--@ConditionalOnBean、@ConditionalOnClass
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/4/7
 */
@Slf4j
@Configuration
@Order(11)
public class Config {

    @Bean("c")
    public City city() {
        City city = new City();
        city.setCityName("千岛湖");
        return city;
    }


    /**
     * 这里加了ConditionalOnBean注解，就代表如果c存在才实例化people
     */
    @Bean(name = {"people3", "people4"})
    @ConditionalOnBean(name = "c")
    public People people(City city) {
//        city.setCityCode(301701);
        return new People("111", 1, city);
    }

    @Bean(name = "people")
    @ConditionalOnMissingBean(name = "city")
    public People people2(City city) {

        //这里如果city实体没有成功注入 这里就会执行
//        city.setCityCode(301701);
        return new People("小小2", 2, city);
    }
}
