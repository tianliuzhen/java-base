package com.aaa.javabase.h2.mapper;

import com.aaa.javabase.h2.Model.User;
import com.aaa.javabase.h2.Model.UserBill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 UserBillMapper.java  2022/10/21 19:52
 */
public interface UserBillMapper extends BaseMapper<UserBill> {

    /**
     * 根据用户id查询用户账单
     *
     * @param id 用户id
     */
    List<UserBill> getUserBillByUserId(Integer id);

    /**
     * 根据用户id查询用户账单（延迟加载）
     *
     * @param id 用户id
     */
    List<UserBill> getUserBillByUserIdV2(Integer id);

    /**
     * 根据账单id查询账单
     *
     * @param id 账单id
     * @return
     */
    UserBill getUserBillById(Integer id);


    /**
     * 根据用户id查询用户账单（join 查询）
     *
     * @param id 用户id
     */
    User getUserBillByUserIdV3(Integer id);

    /**
     * 根据用户id查询用户账单（join 查询 - 循环依赖）
     *
     * @param id 用户id
     */
    User getUserBillByUserIdV4(Integer id);
}
