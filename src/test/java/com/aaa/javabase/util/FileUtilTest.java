package com.aaa.javabase.util;

import com.aaa.javabase.domain.ConfigVarModel;

import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 FileUtil.java  2022/8/1 21:16
 */
public class FileUtilTest {
    public static void main(String[] args) {
        List<ConfigVarModel> configVars = FileUtil.getConfigVars();
        System.out.println();
    }
}
