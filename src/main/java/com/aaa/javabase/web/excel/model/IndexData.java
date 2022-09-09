package com.aaa.javabase.web.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author liuzhen.tian
 * @version 1.0 IndexData.java  2022/9/9 20:23
 */
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class IndexData {
    @ExcelProperty(value = "字符串标题", index = 0)
    private String string;

    @ExcelProperty(value = "日期标题", index = 1)
    private Date date;
    /**
     * 这里设置3 会导致第二列空的
     */
    @ExcelProperty(value = "数字标题", index = 3)
    private Double doubleData;
}
