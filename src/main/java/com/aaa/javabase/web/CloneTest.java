package com.aaa.javabase.web;

import com.aaa.javabase.domain.NumsDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 CloneTest.java  2020/10/9 15:44
 */
public class CloneTest {
    public static void main(String a[]){
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
