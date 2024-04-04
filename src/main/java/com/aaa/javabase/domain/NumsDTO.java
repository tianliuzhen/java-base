package com.aaa.javabase.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liuzhen.tian
 * @version 1.0 NumsDTO.java  2020/8/6 18:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NumsDTO implements Serializable {
    private Integer maxNum=0;
    private Integer result=0;
}
