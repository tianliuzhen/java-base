package com.aaa.javabase.objectOriented.interfaces;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/1/18
 */
@Data
@AllArgsConstructor
public class Animal {
   private int age;
   private String name;
   public void  eat(){
       System.out.println(name + "：在吃");
   }
    public void  speak(){
        System.out.println(name + "：在说");
    }
}
