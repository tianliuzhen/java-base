package com.aaa.javabase.util;

import com.alibaba.fastjson.JSON;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liuzhen.tian
 * @version 1.0 RegexUtil.java  2022/11/11 21:29
 */
public class RegexUtil {
    // 以GG为分组条件进行匹配
    private static final Pattern NAME_PATTERN = Pattern.compile(".+G(.+)G");
    // 以()为分组条件进行匹配
    private static final Pattern NAME_PATTERN2 = Pattern.compile("(.+)\\((.+)\\)");

    public static void main(String[] args) {
        Matcher matcher = NAME_PATTERN.matcher("唐三G123456G");
        if (matcher.matches()) {
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
        }

        System.out.println("=======================");

        Matcher matcher2 = NAME_PATTERN2.matcher("唐三(123456)");
        if (matcher2.matches()) {
            System.out.println(matcher2.group(0));
            System.out.println(matcher2.group(1));
            System.out.println(matcher2.group(2));
        }
        System.out.println(JSON.toJSONString(null));
    }
}
