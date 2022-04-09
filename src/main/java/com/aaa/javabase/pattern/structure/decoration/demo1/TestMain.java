package com.aaa.javabase.pattern.structure.decoration.demo1;

import com.aaa.javabase.pattern.structure.decoration.demo1.impl.BoxComputerCombiner;
import com.aaa.javabase.pattern.structure.decoration.demo1.impl.CpuCombiner;
import com.aaa.javabase.pattern.structure.decoration.demo1.impl.KeyboardCombiner;
import com.aaa.javabase.pattern.structure.decoration.demo1.impl.MonitorCombiner;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2022/4/9 14:31
 */
public class TestMain {
    public static void main(String[] args) {
        // 第一步先装配盒子
        BoxComputerCombiner computerCombiner = new BoxComputerCombiner();
        computerCombiner.assemble();

        // 装配 显示器
        MonitorCombiner monitorCombiner = new MonitorCombiner(computerCombiner);
        monitorCombiner.assemble();

        // 装配 cpu
        CpuCombiner cpuCombiner = new CpuCombiner(monitorCombiner);
        cpuCombiner.assemble();


        // 装配键盘
        KeyboardCombiner keyboardCombiner = new KeyboardCombiner(cpuCombiner);
        keyboardCombiner.assemble();


    }
}
