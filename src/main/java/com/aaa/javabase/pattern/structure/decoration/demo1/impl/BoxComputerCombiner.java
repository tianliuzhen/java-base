package com.aaa.javabase.pattern.structure.decoration.demo1.impl;

import com.aaa.javabase.pattern.structure.decoration.demo1.ComputerCombiner;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liuzhen.tian
 * @version 1.0 BoxComputerCombiner.java  2022/4/9 14:35
 */
@Slf4j
public class BoxComputerCombiner implements ComputerCombiner {
    @Override
    public void assemble() {
        log.info("装配机箱盒...");
    }
}
