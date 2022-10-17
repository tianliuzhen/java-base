package com.aaa.javabase.h2.mapper;

import com.aaa.javabase.h2.Model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author liuzhen.tian
 * @version 1.0 UserMapper.java  2022/9/17 21:44
 */
// @Mapper //或者启动类 @MapperScan("com.aaa.javabase.h2.mapper")
@CacheNamespace // 注释方式开启二级缓存
public interface UserMapper extends BaseMapper<User> { // Mapper接口继承BaseMapper后，可以调用它的诸多方法

    @Select(value = "select * from user where id=#{id}")
    User getUserById(@Param("id") Long id);

    @Select(value = "select * from user where id=#{param1} or name=#{param2} ")
    User getUserByIdOrName(Long id,String name);

    User getOne(@Param("id") Long id);
}
