package com.aaa.javabase.pattern.designpattern.createrpatten.prototype.deep;

import java.io.*;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/2/14
 */
public class DeepProtoType implements Serializable,Cloneable {
    // String 属性
    public String name;
    // 引用类型
    public DeepCloneTarget deepCloneTarget;

    public DeepProtoType() {
    }
    // 深拷贝方式1 - 使用 clone 方法
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object deep = null;
        // 1、这里完成对基本数据类型（属性）和String 的克
        deep = super.clone();
        //2、 对引用类型的属性进行单独处理
        DeepProtoType   deepProtoType = (DeepProtoType) deep;
        deepProtoType.deepCloneTarget = (DeepCloneTarget) deepCloneTarget.clone();
        return deepProtoType;
    }
    // 深拷贝方式2 - 使用 对象的序列化
    public Object deepClone(){
        // 创建流对象
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;

        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;

        try {
            //序列化 输入流
            bos = new ByteArrayOutputStream();// 字节数组输出流
            oos = new ObjectOutputStream(bos);// 对象输出流
            oos.writeObject(this);// 当前这个对象以对象流的方式输出
            //反序列化 输出流
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            DeepProtoType copyObj= (DeepProtoType) ois.readObject();
            return copyObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                //关闭流
                bos.close();
                oos.close();
                bis.close();
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
