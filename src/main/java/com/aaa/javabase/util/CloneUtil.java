package com.aaa.javabase.util;

import lombok.SneakyThrows;
import org.apache.commons.lang3.SerializationUtils;

import java.io.*;

/**
 * @author liuzhen.tian
 * @version 1.0 CloneUtil.java  2020/10/9 15:49
 */
public class CloneUtil {

    /**
     * 通过 SerializationUtils 实现 clone
     *
     * @param object 克隆对象
     * @param <T>    泛型T
     * @return T
     */
    public static <T extends Serializable> T cloneByObj(T object) {
        return SerializationUtils.clone(object);
    }

    //  对象 克隆
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T deepCopyObj(T object) {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bout);
        oos.writeObject(object);
        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bin);
        // 此处不需要释放资源，说明：调用ByteArrayInputStream或ByteArrayOutputStream对象的close方法没有任何意义
        // 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，这一点不同于对外部资源（如文件流）的释放
        return (T) ois.readObject();
    }

}
