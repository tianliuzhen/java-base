package com.aaa.javabase.jdk8.sort;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author liuzhen.tian
 * @version 1.0 dataDto.java  2020/10/27 15:53
 */
@Data
@AllArgsConstructor
public class MonthDataDto {

    private String storeName;

    private String moneyMonth;

    private String moneyMonthTotal;

    private String moneyMonthRate;

    private String userName;

    private String storeManagerName;

    public MonthDataDto(String moneyMonth, String moneyMonthTotal) {
        this.moneyMonth = moneyMonth;
        this.moneyMonthTotal = moneyMonthTotal;
    }
}
