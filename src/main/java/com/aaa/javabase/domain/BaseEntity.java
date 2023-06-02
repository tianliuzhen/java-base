package com.aaa.javabase.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuzhen.tian
 * @version 1.0 BaseEntity.java  2023/6/2 21:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id = 1L;
}
