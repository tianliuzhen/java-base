package com.aaa.javabase.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author liuzhen.tian
 * @version 1.0 BaseMain.java  2020/7/27 19:56
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseMain {

    private String name;

    private Date date;

    // @JsonSerialize(using = LocalDateTimeSerializer.class)
    // @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime localDateTime;
}
