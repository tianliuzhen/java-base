package com.aaa.javabase.base.list.iterator;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * @author liuzhen.tian
 * @version 1.0 TestListIterator.java  2022/1/10 21:47
 */
public class TestListIterator {
    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        list.add("sss");
        list.add("222");
        // Iterator 正向迭代
        ListIterator<Object> iterator = list.listIterator();
        while (iterator.hasNext()) {
            String string = iterator.next().toString();
            System.out.println(string);
        }
        System.out.println("===================");

        // Iterator 反向迭代
        /**
         * 注意：
         *  如果使用反向迭代必须设置构造参数 public ListIterator<E> listIterator(int index){}
         *  如果使用 public ListIterator<E> listIterator() {}，必须先正向迭代，才能反向迭代。
         */
        ListIterator<Object> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            String previous = listIterator.previous().toString();
            System.out.println(previous);
        }

    }
}
