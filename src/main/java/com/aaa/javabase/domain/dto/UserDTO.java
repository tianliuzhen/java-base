package com.aaa.javabase.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuzhen.tian
 * @version 1.0 UserDTO.java  2025/3/22 16:17
 */
@Data
@NoArgsConstructor
public class UserDTO {
    private String name;
    private String phone;

    public UserDTO(String name) {
        this.name = name;
    }
}
