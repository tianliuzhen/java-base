package com.aaa.javabase.multithreading.并发执行.completableFuture;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 CompletableBean.java  2021/2/24 21:38
 */
@EnableAsync  //  @Async 的使用必须先  @EnableAsync
@Configuration
public class CompletableBean {

    private RestTemplate restTemplate=new RestTemplate();

    //直接测试代码
    public static void main(String[] args) throws Exception{


        //通过 spring bean 测试
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.aaa.javabase");
        CompletableBean service1 = applicationContext.getBean("completableBean", CompletableBean.class);

        long begin = System.currentTimeMillis();
        CompletableFuture<String> baidu = service1.testBaidu();
        CompletableFuture<String> sina = service1.testSina();

        //聚合几个查询的结果返回
        CompletableFuture.allOf(baidu,sina).join();

        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - begin) / 1000.0);
        System.out.println("end...");

    }

    @Bean(name = "myExecutor")
    public Executor buildExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(3);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("hyq线程前缀-");
        executor.initialize();
        return executor;
    }

    @Async("myExecutor")
    public CompletableFuture<String> testBaidu(){
        System.out.println("baidu : "+Thread.currentThread().getName());
        ResponseEntity<String> result = restTemplate.getForEntity("https://www.baidu.com/", String.class);
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String body = result.getBody();
        return CompletableFuture.completedFuture(body);
    }

    @Async("myExecutor")
    public CompletableFuture<String> testSina(){
        System.out.println("sina: "+Thread.currentThread().getName());
        ResponseEntity<String> result =  restTemplate.getForEntity("https://www.sina.com.cn/", String.class);
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String body = result.getBody();
        return CompletableFuture.completedFuture(body);
    }
}
