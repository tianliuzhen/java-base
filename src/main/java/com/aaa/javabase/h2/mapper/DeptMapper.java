package com.aaa.javabase.h2.mapper;

import com.aaa.javabase.h2.Model.Dept;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 DeptMapper.java  2022/7/2 10:09
 */
@Repository
public interface DeptMapper {

    Dept getOne(@Param("id") Long id, @Param("id2") Long id2);

    // @Select(value = "select * from dept where id=#{id}")
    Dept getDeptById(Long id);

    List<Dept> selectByDept(Dept dept);
}
