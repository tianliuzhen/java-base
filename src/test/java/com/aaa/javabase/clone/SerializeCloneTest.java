package com.aaa.javabase.clone;

import com.aaa.javabase.domain.Demo;
import com.aaa.javabase.domain.DemoInternal;
import com.aaa.javabase.domain.NumsDTO;
import com.aaa.javabase.util.CloneUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author liuzhen.tian
 * @version 1.0 CloneTest.java  2020/10/9 15:44
 */
@SpringBootTest
public class SerializeCloneTest {

    public static void main(String[] args) {

        // 嵌套对象测试
        Demo demo = new Demo("b", "c", new DemoInternal("e", "f"));
        Demo demo1 = demo;

        // 测试1、 object.clone 执行克隆
        Demo clone = demo.clone();
        clone.setName("b-2");
        clone.getDemoInternal().setInternalName("e-2");


        // 测试2、 序列化 执行克隆
        Demo demo2 = CloneUtil.cloneByObj(demo);
        Demo clone2 = demo2.clone();
        clone2.setName("b-2");
        clone2.getDemoInternal().setInternalName("e-2");


        System.out.println("demo = " + demo);
        System.out.println("demo1 = " + demo1);
        System.out.println("clone = " + clone);


    }



    /**
     * 对象克隆
     */
    @Test
    public void cloneUtilObject() throws Exception {
        NumsDTO old = new NumsDTO(1, 1);
        NumsDTO clone = CloneUtil.deepCopyObj(old);
        clone.setMaxNum(2);
        clone.setResult(2);
        System.out.println("old = " + old);
        System.out.println("clone = " + clone);
    }



}
