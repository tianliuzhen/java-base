package com.aaa.javabase.base.objectOriented.jdkandcglib.service;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/4/24
 */
//用户管理接口
public interface UserManager {
    //新增用户抽象方法
    void addUser(String userName,String password);
    //删除用户抽象方法
    void delUser(String userName);

}
