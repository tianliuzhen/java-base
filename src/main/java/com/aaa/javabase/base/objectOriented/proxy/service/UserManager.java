package com.aaa.javabase.base.objectOriented.proxy.service;

/**
 * 用户管理接口
 *
 * @author liuzhen.tian
 * @version 1.0 JavassistProxy.java  2022/10/26 20:23
 */
public interface UserManager {
    //新增用户抽象方法
    void addUser(String userName, String password);

    //删除用户抽象方法
    void delUser(String userName);
}
