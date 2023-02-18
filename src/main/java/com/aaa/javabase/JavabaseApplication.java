package com.aaa.javabase;

import com.aaa.javabase.config.LoggingListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.aaa.javabase.h2.mapper")
public class JavabaseApplication {

    public static void main(String[] args) {
        // SpringApplication.run(JavabaseApplication.class, args);
        SpringApplication application = new SpringApplication(JavabaseApplication.class);
        // 添加 日志监听器，使 log4j2-spring.xml 可以间接读取到配置文件的属性
        application.addListeners(new LoggingListener());
        application.run(args);
    }

}
