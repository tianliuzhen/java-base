package com.aaa.javabase.web.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author liuzhen.tian
 * @version 1.0 ComplexHeadData.java  2022/9/9 20:09
 */
@Data
@NoArgsConstructor
public class ComplexHeadData {
    @ExcelProperty({"主标题", "字符串标题"})
    private String string;

    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty({"主标题", "日期标题"})
    private Date date;

    @NumberFormat("#.##%")
    @ExcelProperty({"主标题", "数字标题"})
    private Double doubleData;

    @ExcelProperty({"描述"})
    private String desc;
}
