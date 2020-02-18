package com.aaa.javabase.pattern.designpattern.createrpatten.prototype.deep;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/14
 */
public class client {
    public static void main(String[] args) throws CloneNotSupportedException {
        DeepProtoType d = new DeepProtoType();
        d.name = "李白";
        d.deepCloneTarget = new DeepCloneTarget("清莲剑歌五秒","李白的属性");
        // 方式1(实现clone方法) 、完成深拷贝
      /*  DeepProtoType d1= (DeepProtoType) d.clone();
        System.out.println("d.name="+ d.name + " d.deepCloneableTarget="+ d.deepCloneTarget.hashCode());
        System.out.println("d1.name="+ d1.name + " d1.deepCloneableTarget="+ d1.deepCloneTarget.hashCode());*/

        // 方式2（利用对象的序列化） 、完成深拷贝
        DeepProtoType d1= (DeepProtoType) d.deepClone();
        System.out.println("d.name="+ d.name + " d.deepCloneableTarget="+ d.deepCloneTarget.hashCode());
        System.out.println("d1.name="+ d1.name + " d1.deepCloneableTarget="+ d1.deepCloneTarget.hashCode());
    }
}
