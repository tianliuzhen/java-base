package com.aaa.javabase.h2.mapper;

import com.aaa.javabase.h2.Model.UserBill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 UserBillMapper.java  2022/10/21 19:52
 */
public interface UserBillMapper extends BaseMapper<UserBill> {

    List<UserBill> getUserBillByUserId(Integer id);

    List<UserBill> getUserBillByUserIdV2(Integer id);

}
