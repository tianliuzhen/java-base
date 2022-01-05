package com.aaa.javabase.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuzhen.tian
 * @version 1.0 MyTestConfig.java  2022/1/5 19:51
 */
@Configuration
public class MyTestConfig {

    @Bean
    public Driver driver() {
        Driver driver = new Driver();
        driver.setId(1);
        driver.setName("driver");
        driver.setCar(car());
        return driver;
    }

    @Bean
    public Car car() {
        Car car = new Car();
        car.setId(1);
        car.setName("car");
        return car;
    }

    @Data
    public static class Driver {
        private Integer id;
        private String name;
        private Car car;
    }

    @Data
    public static class Car {
        private Integer id;
        private String name;
    }
}


