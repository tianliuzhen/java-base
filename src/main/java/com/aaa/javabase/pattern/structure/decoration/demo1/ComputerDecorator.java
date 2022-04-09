package com.aaa.javabase.pattern.structure.decoration.demo1;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liuzhen.tian
 * @version 1.0 ComputerDecorator.java  2022/4/9 14:25
 */

@Slf4j
public abstract class ComputerDecorator implements ComputerCombiner {
    private ComputerCombiner computerCombiner;

    public ComputerDecorator(ComputerCombiner computerCombiner) {
        this.computerCombiner = computerCombiner;
    }

    /**
     * 组装接口
     */
    @Override
    public void assemble() {
        // this.computerCombiner.assemble();
        this.decoration();
    }

    public abstract void decoration();
}
