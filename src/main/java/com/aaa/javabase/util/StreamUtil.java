package com.aaa.javabase.util;

import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author liuzhen.tian
 * @version 1.0 StreamUtil.java  2022/10/22 13:35
 */
public class StreamUtil {

    @SneakyThrows
    public static byte[] writeObject(Object object) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
        objectOutputStream.writeObject(object);
        return out.toByteArray();
    }

    @SneakyThrows
    public static <T> T readObject(byte[] bytes, Class<T> t) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream inputStream = new ObjectInputStream(byteArrayInputStream);
        return (T) inputStream.readObject();
    }
}
