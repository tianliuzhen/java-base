package com.aaa.javabase.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author liuzhen.tian
 * @version 1.0 TestApplicationTests.java  2022/1/5 19:54
 */
@SpringBootTest
public class TestApplicationTests {

    @Autowired
    private MyTestConfig.Car car;

    @Autowired
    private MyTestConfig.Driver driver;

    @Autowired
    private MyTestConfig2.Car car2;

    @Autowired
    private MyTestConfig2.Driver driver2;

    @Test
    public void contextLoads() {
        boolean result = driver.getCar() == car;
        System.out.println(result ? "同一个car" : "不同的car");
    }

    @Test
    public void contextLoads2() {
        boolean result = driver2.getCar() == car2;
        System.out.println(result ? "同一个car" : "不同的car");
    }

    // 结论
    /**
     * 虽然Component注解也会当做配置类，但是并不会为其生成CGLIB代理Class，
     * 所以在生成Driver对象时和生成Car对象时调用car()方法执行了两次new操作，所以是不同的对象。
     * 当时Configuration注解时，生成当前对象的子类Class，并对方法拦截，第二次调用car()方法时直接从BeanFactory之中获取对象，
     * 所以得到的是同一个对象。
     */

}
