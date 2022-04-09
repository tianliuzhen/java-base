package com.aaa.javabase.pattern.structure.decoration.demo1.impl;

import com.aaa.javabase.pattern.structure.decoration.demo1.ComputerCombiner;
import com.aaa.javabase.pattern.structure.decoration.demo1.ComputerDecorator;
import lombok.extern.slf4j.Slf4j;

/**
 * 屏幕 组装
 *
 * @author liuzhen.tian
 * @version 1.0 MonitorCombiner.java  2022/4/9 14:19
 */

@Slf4j
public class MonitorCombiner extends ComputerDecorator {
    public MonitorCombiner(ComputerCombiner computerCombiner) {
        super(computerCombiner);
    }

    @Override
    public void decoration() {
        log.info("装配 屏幕...");
    }

}
