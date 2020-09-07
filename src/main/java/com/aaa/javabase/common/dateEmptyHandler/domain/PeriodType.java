/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.aaa.javabase.common.dateEmptyHandler.domain;

import lombok.Getter;

/**
 * @author 田留振
 */
@Getter
public enum PeriodType {
    MINUTE("MINUTE"),
    HOUR("HOUR"),
    DAY("DAY"),
    MONTH("MONTH"),
    YEAR("YEAR"),
    ;

    private String code;

    PeriodType(String code) {
        this.code = code;
    }

    public static PeriodType getByCode(String code) {
        for (PeriodType type : values()) {
            if (type.code.equalsIgnoreCase(code)) {
                return type;
            }
        }

        return null;
    }

}
