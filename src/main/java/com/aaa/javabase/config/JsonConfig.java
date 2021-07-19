package com.aaa.javabase.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author liuzhen.tian
 * @version 1.0 JsonConfig.java  2020/10/29 15:49
 */

// @Configuration
public class JsonConfig {


    /**
     * 注意：这里
     * 会导致 spring.jackson.date-format=yyyy-MM-dd HH:mm:ss 配置失效
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter() {

        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        ObjectMapper mapper = converter.getObjectMapper();


        // 针对于Date类型，文本格式化
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        //针对于JDK新时间类。序列化时带有T的问题，自定义格式化字符串
        // JavaTimeModule javaTimeModule = new JavaTimeModule();
        // javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        // javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        // javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        // mapper.registerModule(javaTimeModule);


        // 为mapper注册一个带有SerializerModifier的Factory，此modifier主要做的事情为：当序列化类型为array，list、set时，当值为空时，序列化成[]
        mapper.setSerializerFactory(mapper.getSerializerFactory().withSerializerModifier(new MyBeanSerializerModifier()));


        return converter;
    }

}
