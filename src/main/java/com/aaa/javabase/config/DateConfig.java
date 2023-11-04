package com.aaa.javabase.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author liuzhen.tian
 * @version 1.0 DateConfig.java  2020/7/27 20:05
 */
@Configuration
public class DateConfig {


    // @Bean(name = "mapperObject")
    // public ObjectMapper getObjectMapper() {
    //     ObjectMapper om = new ObjectMapper();
    //     JavaTimeModule javaTimeModule = new JavaTimeModule();
    //     javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    //     javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    //     javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
    //     om.registerModule(javaTimeModule);
    //     return om;
    // }

    // @Bean
    // public LocalDateTimeSerializer localDateTimeDeserializer() {
    //     return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    // }
    //
    // @Bean
    // public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
    //     return builder -> builder.serializerByType(LocalDateTime.class, localDateTimeDeserializer());
    // }


    /**
     * JDK1.8中添加新的时间日期API，LocalDate、LocalDateTime、LocalTime，但是我们在开发中使用时间戳作为参数值来传递是比较常用的，
     * 然而在SpringBoot中并没有为我们提供合适的JsonSerializer和JsonDeserializer。
     * 因此需要去封装一层
     *
     * spring:
     *   jackson:
     *     date-format: yyyy-MM-dd HH:mm:ss
     *     time-zone: GMT+8
     *     serialization:
     *       write-dates-as-timestamps: false
     * 这种配置只适用于Date这种，不适用于LocalDateTime等。
     * @return
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customJackson() {
        return jacksonObjectMapperBuilder -> {
            // 若POJO对象的属性值为null，序列化时不进行显示
            jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_NULL);

            // 针对于Date类型，文本格式化
            jacksonObjectMapperBuilder.simpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // 针对于JDK新时间类。序列化时带有T的问题，自定义格式化字符串
            JavaTimeModule javaTimeModule = new JavaTimeModule();
            javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            // 序列换成json时,将所有的long变成string,因为js中得数字类型不能包含所有的java long值
            javaTimeModule.addSerializer(Long.class, ToStringSerializer.instance);
            javaTimeModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
            javaTimeModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
            jacksonObjectMapperBuilder.modules(javaTimeModule);

        };
    }
}
