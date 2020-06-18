package com.aaa.javabase.pattern.structure.decoration.demo1;

/**
 * @author liuzhen.tian
 * @version $ Id: Test.java, v 0.1 2020/6/18 15:45 liuzhen.tian Exp $
 */
public class Test {
    public static void main(String[] args) {
        ComputerWithAsus computerWithLenovo = new ComputerWithAsus();
        String desc = computerWithLenovo.getDesc();
        System.out.println(desc);
    }
}
