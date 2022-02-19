package com.aaa.javabase.pattern.behavior.strategy.constant;


import com.aaa.javabase.domain.BaseNum;

/**
 * @author liuzhen.tian
 * @version $ Id: InspectionEnum.java, v 0.1 2020/6/18 11:04 liuzhen.tian Exp $
 */
public enum InspectionEnum implements BaseNum {

    INSPECTION_TASK_TYPE_BATCH_CHANGE_WAREHOUSE("BATCH_CHANGE_WAREHOUSE", "转快递"),
    INSPECTION_TASK_TYPE_BATCH_CHANGE_SHIPPING("BATCH_CHANGE_SHIPPING", "转仓"),
    INSPECTION_TASK_TYPE_BATCH_REPLACE_ORDER_GOODS("REPLACE_ORDER_GOODS", "替换商品"),
    ;
    private String code;
    private String desc;

    InspectionEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
