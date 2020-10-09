package com.aaa.javabase.clone;

import com.aaa.javabase.domain.Demo;
import com.aaa.javabase.domain.DemoInternal;
import com.aaa.javabase.domain.NumsDTO;
import com.aaa.javabase.util.CloneUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 CloneTest.java  2020/10/9 15:44
 */
@SpringBootTest
public class SerializeCloneTest {

    public static void main(String[] args) {
        Demo demo = new Demo("b","c",new DemoInternal("e","f"));
        Demo demo1 = demo;
        Demo clone = demo.clone();
        clone.setName("b-2");
        System.out.println("demo = " + demo);
        System.out.println("demo1 = " + demo1);
        System.out.println("clone = " + clone);

    }


    /**
     * 集合克隆
     */
    @Test
    public  void  cloneUtilList() throws IOException, ClassNotFoundException {
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
    @Test
    public  void  cloneUtilObject() throws Exception {
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
    @Test
    public  void arrayListClone() {
        List<NumsDTO> al = new ArrayList<NumsDTO>();

        //Adding elements to the ArrayList
        al.add(new NumsDTO(1,1));
        al.add(new NumsDTO(2,2));
        System.out.println("ArrayList: "+al);

        List<NumsDTO> al2 = (List<NumsDTO>) ((ArrayList<NumsDTO>) al).clone();
        System.out.println("Shallow copy of ArrayList: "+ al2);

        //add and remove on original ArrayList
        al.add(new NumsDTO(3,3));
        al.get(0).setResult(11);
        System.out.println("---------------------");

        //Display of both ArrayLists after add & remove
        System.out.println("Original ArrayList:"+al);
        System.out.println("Cloned ArrayList:"+al2);
    }


    /**
     * 嵌套对象测试
     */

}
