package com.aaa.javabase.multithreading.lock;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/5/5
 */
public class User {
    private String name;
    private Integer id;

    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setName("12");
        User user2 = new User();
        user2.setId(1);
        user2.setName("12");
        System.out.println(user.hashCode());
        System.out.println(user2.hashCode());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
