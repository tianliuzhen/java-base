package com.aaa.javabase.pattern.structure.decoration.demo1.impl;

import com.aaa.javabase.pattern.structure.decoration.demo1.ComputerCombiner;
import com.aaa.javabase.pattern.structure.decoration.demo1.ComputerDecorator;
import lombok.extern.slf4j.Slf4j;

/**
 * 组装键盘
 *
 * @author liuzhen.tian
 * @version 1.0 KeyboardCombiner.java  2022/4/9 14:22
 */
@Slf4j
public class KeyboardCombiner extends ComputerDecorator {

    public KeyboardCombiner(ComputerCombiner computerCombiner) {
        super(computerCombiner);
    }

    @Override
    public void decoration() {
        log.info("装配 键盘...");
    }
}
