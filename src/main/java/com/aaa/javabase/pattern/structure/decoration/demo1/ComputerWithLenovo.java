package com.aaa.javabase.pattern.structure.decoration.demo1;

/**
 * @author liuzhen.tian
 * @version $ Id: ComputerWithLenovo.java, v 0.1 2020/6/18 15:41 liuzhen.tian Exp $
 */
public class ComputerWithLenovo extends ComputerCore {

    @Override
    protected String getDesc() {
        return super.getDesc()+"=》 采用联想芯片";
    }
}
