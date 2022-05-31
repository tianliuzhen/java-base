package com.aaa.javabase;

import com.aaa.javabase.util.LoggingListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavabaseApplication {

    public static void main(String[] args) {
        // SpringApplication.run(JavabaseApplication.class, args);
        SpringApplication application = new SpringApplication(JavabaseApplication.class);
        // 添加 日志监听器，使 log4j2-spring.xml 可以间接读取到配置文件的属性
        application.addListeners(new LoggingListener());
        application.run(args);
    }

}
