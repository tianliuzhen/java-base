package com.aaa.javabase.h2.tkmapper;

import com.aaa.javabase.h2.Model.Dept;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

/**
 * @author liuzhen.tian
 * @version 1.0 TkDeptMapper.java  2023/8/14 22:34
 */
public interface TkDeptMapper extends Mapper<Dept> {
    Dept getOne(@Param("id") Long id);

    @Select(value = "select * from dept where id=#{id}")
    Dept getDeptById(@Param("id") Long id);

    @SelectProvider(type = SqlProvider.class, method = "getDeptCheck")
    Dept getDeptCheck(@Param("deptName")  String deptName);

    class SqlProvider{
        public String getDeptCheck(Map<String, Object> para) {
            return "select * from dept where dept_name=" + para.get("deptName") ;
        }
    }

}
