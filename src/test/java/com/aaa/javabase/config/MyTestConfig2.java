package com.aaa.javabase.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 MyTestConfig.java  2022/1/5 19:51
 */
@Component
public class MyTestConfig2 {

    @Bean
    public Driver driver2() {
        Driver driver = new Driver();
        driver.setId(1);
        driver.setName("driver");
        driver.setCar(car2());
        return driver;
    }

    @Bean
    public Car car2() {
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


