package com.aaa.javabase.h2.mapper;

import com.aaa.javabase.h2.Model.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author liuzhen.tian
 * @version 1.0 DeptMapper.java  2022/7/2 10:09
 */
@Repository
@Mapper
public interface DeptMapper extends BaseMapper<Dept> {

    Dept getOne(@Param("id") Long id);

    @Select(value = "select * from dept where id=#{id}")
    Dept getDeptById(@Param("id") Long id);
}
