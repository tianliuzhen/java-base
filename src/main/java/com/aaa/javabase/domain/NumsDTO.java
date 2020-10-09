package com.aaa.javabase.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author liuzhen.tian
 * @version 1.0 NumsDTO.java  2020/8/6 18:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class NumsDTO {
    private Integer maxNum=0;
    private Integer result=0;
}
