package com.aaa.javabase.util;

import com.aaa.javabase.domain.NumsDTO;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 CloneUtil.java  2020/10/9 15:49
 */
public class CloneUtil {

    //  对象 克隆
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T deepCopyObj(T object) throws Exception{
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bout);
        oos.writeObject(object);
        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bin);
        // 此处不需要释放资源，说明：调用ByteArrayInputStream或ByteArrayOutputStream对象的close方法没有任何意义
        // 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，这一点不同于对外部资源（如文件流）的释放
        return (T) ois.readObject();
    }
    // list 克隆
    public static <T> T deepCopyList(Collection<?> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        Collection<T> dest = (Collection<T>) in.readObject();
        return (T) dest;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Integer> list = Arrays.asList(1,2,3);
        List<Integer> list1 = list;
        List<Integer> list2 = deepCopyList(list);
        // 浅克隆
        System.out.println(list == list1);
        // 深克隆
        System.out.println(list == list2);

    }
}
