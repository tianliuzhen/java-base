package com.aaa.javabase.enums;

import com.aaa.javabase.pattern.behavior.strategy.constant.InspectionEnum;
import com.aaa.javabase.util.EnumUtil;


/**
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2021/9/26 20:06
 */
public class TestMain {
    public static void main(String[] args) throws Exception {
        InspectionEnum enumByCode = EnumUtil.getEnumByCode(InspectionEnum.class, "BATCH_CHANGE_WAREHOUSE");
        InspectionEnum enumByCodeV2 = EnumUtil.getEnumByCodeV2(InspectionEnum.class, "BATCH_CHANGE_SHIPPING");
        String byCode = EnumUtil.getStringByCode(InspectionEnum.class, "BATCH_CHANGE_SHIPPING");
        String byCodeV2 = EnumUtil.getStringByCodeV2(InspectionEnum.class, "REPLACE_ORDER_GOODS");
        System.out.println();


    }
}
