package com.aaa.javabase.common.dateEmptyHandler.domain;

import lombok.Data;

import java.util.Date;

/**
 * description: 传入参数
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/5/28
 */
@Data
public class ParameterItem {
    private Date from;
    private Date to;
    private PeriodType periodType;
    private Integer limit;
}
