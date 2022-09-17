package com.aaa.javabase.h2.Model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author liuzhen.tian
 * @version 1.0 User.java  2022/9/17 21:43
 */
@TableName(value = "user") //访问h2数据库
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "age")
    private Integer age;

    @TableField(value = "email")
    private String email;
}
