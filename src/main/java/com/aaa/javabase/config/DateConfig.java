package com.aaa.javabase.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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


    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customJackson() {
        return jacksonObjectMapperBuilder -> {
            //若POJO对象的属性值为null，序列化时不进行显示
            jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_NULL);

            //针对于Date类型，文本格式化
            jacksonObjectMapperBuilder.simpleDateFormat("yyyy-MM-dd HH:mm:ss");

            //针对于JDK新时间类。序列化时带有T的问题，自定义格式化字符串
            JavaTimeModule javaTimeModule = new JavaTimeModule();
            javaTimeModule.addSerializer(LocalDateTime.class,new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            javaTimeModule.addDeserializer(LocalDateTime.class,new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            jacksonObjectMapperBuilder.modules(javaTimeModule);

        };
    }
}
