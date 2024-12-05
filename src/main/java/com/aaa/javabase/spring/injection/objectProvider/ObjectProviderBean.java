package com.aaa.javabase.spring.injection.objectProvider;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuzhen.tian
 * @version 1.0 ObjectProviderBean.java  2024/12/5 22:50
 */
@Configuration
public class ObjectProviderBean {
    @AllArgsConstructor
    @Data
    public class MyDataSource {
        private String name;
    }

    @Bean
    public MyDataSource myOpDataSource() {
        return new MyDataSource("myOpDataSource");
    }


    @Bean
    @Qualifier
    public MyDataSource myOpQuartzDataSource() {
        return new MyDataSource("myOpQuartzDataSource");
    }


    /**
     * myOpQuartzDataSource参数里面带有 @Qualifier, 必须满足 quartzDataSource  @Bean 声明时，也带@Qualifier ，否则无法注入
     * <p>
     * myOpQuartzDataSource 参数不带@Qualifier，并且 quartzDataSource  @Bean 声明时，也不带@Qualifier ，也能注入
     *
     * @param myOpDataSource
     * @param myOpQuartzDataSource
     * @return
     */
    @Bean
    public MyDataSource myOpQuartzDataSourceInitializer(MyDataSource myOpDataSource,
                                                        @Qualifier ObjectProvider<MyDataSource> myOpQuartzDataSource) {
        myOpQuartzDataSource.getIfAvailable(); // 此时才会注入bean，使用时才会注入
        return new MyDataSource("quartzDataSourceInitializer");
    }

}
