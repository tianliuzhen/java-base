package com.aaa.javabase.spring.injection.objectProvider;

import lombok.AllArgsConstructor;
import lombok.Data;
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

    @AllArgsConstructor
    @Data
    public class MyDataSource2 {
        private String name;
    }


    @Bean(autowireCandidate = false)
    public MyDataSource myOpDataSource() {
        return new MyDataSource("myOpDataSource");
    }



    /**
     * 思考 @QuartzDataSource 注解原理
     * see org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration.JdbcStoreTypeConfiguration#quartzDataSourceInitializer(javax.sql.DataSource, org.springframework.beans.factory.ObjectProvider, org.springframework.core.io.ResourceLoader, org.springframework.boot.autoconfigure.quartz.QuartzProperties)
     * <p>
     * 这里 @MyQualifier 覆盖了 @Qualifier
     * <p>
     * myOpQuartzDataSource参数里面带有 @MyQualifier, 必须满足 quartzDataSource  @Bean 声明时，也带@MyQualifier ，否则无法注入
     * <p>
     * myOpQuartzDataSource 参数不带@MyQualifier，并且 quartzDataSource  @Bean 声明时，也不带@MyQualifier ，也能注入
     *
     * @param myOpDataSource
     * @param myOpQuartzDataSource
     * @return
     */
    @Bean
    public MyDataSource myOpQuartzDataSourceInitializer(MyDataSource myOpDataSource) {
        // 此时才会注入bean，使用时才会注入
        return new MyDataSource("quartzDataSourceInitializer");
    }

}
