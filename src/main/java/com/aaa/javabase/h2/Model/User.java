package com.aaa.javabase.h2.Model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 User.java  2022/9/17 21:43
 */

@Data
@TableName(value = "user") //访问h2数据库
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "age")
    private Integer age;

    @TableField(value = "email")
    private String email;

    @TableField(value = "user_id")
    private String userId;


    @TableField(value = "dept_no")
    private String deptNo;


    /**
     * 假设一个员工可以对应一个部门（主部门）
     */
    private Dept dept;
    /**
     * 假设一个员工也可以对应多个部门（子部门）
     */
    private List<Dept> deptList;

    /**
     * 用户对于账单
     */
    private List<UserBill> userBillList;

    public User(Integer age) {
        System.err.println("age = " + age);
        this.age = age;
    }


    public User(Long id, String name, Integer age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }


}
