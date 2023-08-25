package com.aaa.javabase.h2.tkmapper;

import com.aaa.javabase.h2.Model.Dept;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author liuzhen.tian
 * @version 1.0 TkDeptMapper.java  2023/8/14 22:34
 */
public interface TkDeptMapper {
    Dept getOne(@Param("id") Long id);

    @Select(value = "select * from dept where id=#{id}")
    Dept getDeptById(@Param("id") Long id);
}
