package com.aaa.javabase.pattern.creater.prototype.improve;

/**
 * description: 描述
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/14
 */
public class Client {
    private Sheep sheep1;

    public static void main(String[] args) throws CloneNotSupportedException {
        System.out.println("测试原型创建");
        Sheep sheep = new Sheep("duoli", 99, "白色");
        sheep.friend = new Sheep("朋友", 25, "灰色");
        Sheep sheep1 = (Sheep) sheep.clone();
        Sheep sheep2 = (Sheep) sheep.clone();
        Sheep sheep3 = (Sheep) sheep.clone();

        System.out.println("sheep1="+ sheep1 +"sheep1.hashcode= "+ sheep1.hashCode()+" sheep1.friend.hashCode= "+ sheep1.friend.hashCode());
        System.out.println("sheep2="+ sheep2 +"sheep1.hashcode= "+ sheep2.hashCode()+" sheep2.friend.hashCode= "+ sheep2.friend.hashCode());
        System.out.println("sheep3="+sheep3+"sheep1.hashcode= "+sheep3.hashCode()+" sheep3.friend.hashCode=  "+sheep3.friend.hashCode());
        System.out.println(sheep1 == sheep2);
        System.out.println("-----------------------");

    }
}
