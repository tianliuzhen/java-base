package com.aaa.javabase.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author liuzhen.tian
 * @version 1.0 BaseEntity.java  2023/6/2 21:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
    // @JsonSerialize(using = ToStringSerializer.class)
    private Long id = 1L;

    private LocalDateTime beginTime;

    private Date endTime;
    private int status = 0;

    public BaseEntity(Long id, LocalDateTime beginTime, Date endTime) {
        this.id = id;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }
}
