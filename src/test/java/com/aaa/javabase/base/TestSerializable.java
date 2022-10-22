package com.aaa.javabase.base;

import lombok.Data;

import java.io.*;

/**
 * @author liuzhen.tian
 * @version 1.0 TestSerializable.java  2022/10/22 12:30
 */
public class TestSerializable {

    public static class WriteObject {
        public static void main(String[] args) throws IOException, ClassNotFoundException {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            Person xxx = new Person("xxx", 10);
            objectOutputStream.writeObject(xxx);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(out.toByteArray());
            ObjectInputStream inputStream = new ObjectInputStream(byteArrayInputStream);
            Person newPerson = (Person) inputStream.readObject();

            System.out.println();
        }


        @Data
        public static class Person implements Serializable {
            private String name;
            private int age;

            public Person(String name, int age) {
                this.name = name;
                this.age = age;
            }

            /**
             * writeReplace: 在序列化时，会先调用此方法，再调用writeObject方法。此方法可将任意对象代替目标序列化对象
             */
            private Object writeReplace()  {
                return new Person(name, this.age + 1);
            }

            /**
             * readResolve：反序列化时替换反序列化出的对象，反序列化出来的对象被立即丢弃。此方法在readeObject后调用。
             */
            private Object readResolve()  {
                return new Person(name, this.age + 2);
            }

        }
    }
}

