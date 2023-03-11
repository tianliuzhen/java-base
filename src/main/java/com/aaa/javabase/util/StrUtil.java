package com.aaa.javabase.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 StrUtil.java  2023/3/11 20:28
 */
public class StrUtil {
    static List<String> regexList = Lists.newArrayList("\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|");

    /**
     * 转义字符串
     *
     * @param str
     * @return
     */
    public static String replaceSpecialStr(String str) {
        // 处理一下特殊字符
        if (regexList.contains(str)) {
            return "\\".concat(str);
        }
        return str;
    }

    /**
     * 获取字符串第几次出现的位置
     * <p>
     * StringUtils工具类：org.apache.commons.lang3.StringUtils#ordinalIndexOf(java.lang.CharSequence, java.lang.CharSequence, int)
     *
     * @param str       aaa.bbb.ccc.ddd
     * @param searchStr .
     * @param num       2
     * @return int 7
     */
    public static int getIndexOf(String str, String searchStr, int num) {
        if (num == 1) {
            return str.indexOf(searchStr);
        }
        int index = 0;
        for (int i = 0; i < num; i++) {
            if (i == 0) {
                index = str.indexOf(searchStr);
            } else {
                index = str.indexOf(searchStr, index + 1);
            }
        }
        return index;
    }

    public static void main(String[] args) {
        String data = "aaa.bbb.ccc.dddd";
        System.out.println(getIndexOf(data, ".", 2));

        System.out.println(StringUtils.ordinalIndexOf(data, ".", 2));
    }
}
