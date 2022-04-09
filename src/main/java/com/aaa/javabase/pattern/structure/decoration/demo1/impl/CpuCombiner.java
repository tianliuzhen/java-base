package com.aaa.javabase.pattern.structure.decoration.demo1.impl;

import com.aaa.javabase.pattern.structure.decoration.demo1.ComputerCombiner;
import com.aaa.javabase.pattern.structure.decoration.demo1.ComputerDecorator;
import lombok.extern.slf4j.Slf4j;

/**
 * cpu 组装
 *
 * @author liuzhen.tian
 * @version 1.0 LenovCpu.java  2022/4/9 14:18
 */
@Slf4j
public class CpuCombiner extends ComputerDecorator {

    public CpuCombiner(ComputerCombiner computerCombiner) {
        super(computerCombiner);
    }

    @Override
    public void decoration() {
        log.info("装配 cpu ...");
    }
}
