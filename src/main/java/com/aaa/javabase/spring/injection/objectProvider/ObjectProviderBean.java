package com.aaa.javabase.spring.injection.objectProvider;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 ObjectProviderBean.java  2024/12/5 22:50
 */
@Configuration
public class ObjectProviderBean {

    /**
     * 如果在 new AnnotationConfigApplicationContext(ObjectProviderBean.class); 加载的前提下
     *
     * 这里内部类声明的bean，如果没有使用 @Component(value="myObjectProviderInfo")
     * 默认的bean名称时是："com.aaa.javabase.spring.injection.objectProvider.ObjectProviderBean$MyObjectProviderInfo"
     *
     * 源码分析：
     * org.springframework.context.annotation.ConfigurationClassParser.SourceClass#asConfigClass(org.springframework.context.annotation.ConfigurationClass)
     * org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator#buildDefaultBeanName(org.springframework.beans.factory.config.BeanDefinition)
     */
    @Component
    public static class MyObjectProviderInfo {
        MyObjectProviderInfo() {
            System.out.println();
        }

    }

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
    @MyQualifier
    public MyDataSource myOpQuartzDataSource() {
        return new MyDataSource("myOpQuartzDataSource");
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
    public MyDataSource myOpQuartzDataSourceInitializer(MyDataSource myOpDataSource,
                                                        @MyQualifier ObjectProvider<MyDataSource> myOpQuartzDataSource) {
        // 此时才会注入bean，使用时才会注入
        MyDataSource ifAvailable = myOpQuartzDataSource.getIfAvailable();
        return new MyDataSource("quartzDataSourceInitializer");
    }

}
