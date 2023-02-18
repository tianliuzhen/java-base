package com.aaa.javabase.h2.mapper;

import com.aaa.javabase.h2.Model.Dept;
import com.aaa.javabase.h2.Model.UserExtModel;

import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 ManualUserMapper.java  2023/2/16 20:51
 */
public interface UserExtMapper extends UserMapper{
    List<UserExtModel> getUserExtList();

    Dept getDeptNo(String dept);
}
