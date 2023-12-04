package com.aaa.javabase;

import com.aaa.javabase.config.LoggingListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@MapperScan("com.aaa.javabase.h2.mapper")
@tk.mybatis.spring.annotation.MapperScan("com.aaa.javabase.h2.tkmapper")
@ImportResource(locations = {"classpath:/spring-common.xml"})
@EnableAspectJAutoProxy(proxyTargetClass = false)
// @EnableAsync  //  @Async 的使用必须先  @EnableAsync
public class JavabaseApplication {

    public static void main(String[] args) {
        // SpringApplication.run(JavabaseApplication.class, args);
        SpringApplication application = new SpringApplication(JavabaseApplication.class);
        // 添加 日志监听器，使 log4j2-spring.xml 可以间接读取到配置文件的属性
        application.addListeners(new LoggingListener());

        /**
         * 在使用maven管理webflux project时，spring-boot-starter-web和spring-boot-starter-webflux能否一起工作？
         * 默认是可以的，默认还是spring基础的配置。但可以修改
         *  官方文档中有这么一段注解：
         *  很多开发者添加spring-boot-start-webflux到他们的spring mvc web applicaiton去是为了使用reactive WebClient.
         *  如果希望更改webApplication 类型需要显示的设置，
         *  如SpringApplication.setWebApplicationType(WebApplicationType.REACTIVE).
         */
        // application.setWebApplicationType(WebApplicationType.REACTIVE);

        application.run(args);


    }

}
