package com.aaa.javabase.pattern.structure.decoration.demo1;

/**
 * @author liuzhen.tian
 * @version $ Id: ComputerWithAsus.java, v 0.1 2020/6/18 15:43 liuzhen.tian Exp $
 */
public class ComputerWithAsus extends ComputerCore {
    @Override
    protected String getDesc() {
        return super.getDesc()+"=》"+"采用华硕芯片";
    }
}
