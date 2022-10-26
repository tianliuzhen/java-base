package com.aaa.javabase.base.objectOriented.proxy.service;

/**
 * 用户管理实现类,实现用户管理接口
 *
 * @author liuzhen.tian
 * @version 1.0 JavassistProxy.java  2022/10/26 20:23
 */
public class UserManagerImpl implements UserManager {
    private Long id;
    private String name;

    public UserManagerImpl() {
    }

    public UserManagerImpl(String name) {
        this.name = name;
    }

    public UserManagerImpl(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void addUser(String userName, String password) {
        System.out.println("调用了新增的方法！");
        System.out.println("传入参数为 userName: " + userName + " password: " + password);
    }

    @Override
    public void delUser(String userName) {
        System.out.println("调用了删除的方法！");
        System.out.println("传入参数为 userName: " + userName);
    }

}
