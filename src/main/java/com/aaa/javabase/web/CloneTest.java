package com.aaa.javabase.web;

import com.aaa.javabase.domain.NumsDTO;
import com.aaa.javabase.util.CloneUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 CloneTest.java  2020/10/9 15:44
 */
public class CloneTest {
    public static void main(String a[]) throws IOException, ClassNotFoundException {

        // arrayListClone()
        // cloneUtilObject();
        cloneUtilList();
    }

    /**
     * 集合克隆
     */
    private static void  cloneUtilList() throws IOException, ClassNotFoundException {
        ArrayList<NumsDTO> old = new ArrayList<>();
        old.add(new NumsDTO(1, 1));
        old.add(new NumsDTO(2, 2));
        old.add(new NumsDTO(3, 3));
        List<NumsDTO> clone = CloneUtil.deepCopyList(old);
        clone.get(0).setResult(9);
        clone.get(0).setResult(9);
        System.out.println("old = " + old);
        System.out.println("clone = " + clone);
    }

    /**
     * 对象克隆
     */
    private static void  cloneUtilObject() throws Exception {
        NumsDTO old = new NumsDTO(1, 1);
            NumsDTO clone = CloneUtil.deepCopyObj(old);
            clone.setMaxNum(2);
            clone.setResult(2);
            System.out.println("old = " + old);
            System.out.println("clone = " + clone);
    }

    /**
     * arrayList 默认克隆
     */
    private static void arrayListClone() {
        List<NumsDTO> al = new ArrayList<NumsDTO>();

        //Adding elements to the ArrayList
        al.add(new NumsDTO(1,1));
        al.add(new NumsDTO(2,2));
        System.out.println("ArrayList: "+al);

        List<NumsDTO> al2 = (List<NumsDTO>) ((ArrayList<NumsDTO>) al).clone();
        System.out.println("Shallow copy of ArrayList: "+ al2);

        //add and remove on original ArrayList
        al.add(new NumsDTO(3,3));
        System.out.println("---------------------");

        //Display of both ArrayLists after add & remove
        System.out.println("Original ArrayList:"+al);
        System.out.println("Cloned ArrayList:"+al2);
    }


}
