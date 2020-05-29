package com.aaa.javabase.common;


import com.aaa.javabase.common.domain.DataItem;
import com.aaa.javabase.common.domain.ParameterItem;
import com.aaa.javabase.common.domain.PeriodType;
import com.aaa.javabase.common.util.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * description: 补全日期（包含，日，月）
 *
 * @author 田留振
 * @version 1.0
 * @date 2020/5/28
 */
public class HandlerEmpty {

    /**
     * 对日期范围补空
     * @param parameterItem
     * @param dataItemList
     * @return void
     */
    public List<DataItem>   emptyCompletion(ParameterItem parameterItem, List<DataItem> dataItemList){
        //取到参数
        Date from = parameterItem.getFrom();
        Date to = parameterItem.getTo();
        PeriodType periodType = parameterItem.getPeriodType();
        Integer limit = parameterItem.getLimit();

        //初始化时间范围
        Date head = DateUtil.dateTransfer(from, periodType);
        Date end = DateUtil.dateTransfer(to, periodType);

        // 处理完之后，补空之后的真实的数据
        List<DataItem> realList = new ArrayList<>();

        for (DataItem item : dataItemList) {
            // 组装完毕返回
            if (realList.size() > limit) {
                break;
            }
            while (head.before(item.getTimeKey())){
                realList.add(buildEmpty(head));
                head = nextDateKey(head, periodType);
            }

            if (head.equals(item.getTimeKey())) {
                // 当前时间匹配加入
                realList.add(item);
            }
            head = nextDateKey(head, periodType);
        }
        // 对  日期 > time-key 后面值的补充
        while (realList.size() < limit && (head.before(end) || head.equals(end))) {
            realList.add(buildEmpty(head));
            head = nextDateKey(head, periodType);
        }
        return realList;
    }

    private Date nextDateKey(Date previous, PeriodType periodType) {
        switch (periodType) {
            case DAY:
                return DateUtil.addByPeriodType(previous, 1,PeriodType.DAY);
            case MONTH:
                return DateUtil.addByPeriodType(previous, 1,PeriodType.MONTH);
            case HOUR:
                return DateUtil.addByPeriodType(previous, 1,PeriodType.HOUR);
            case MINUTE:
                return DateUtil.addByPeriodType(previous, 1,PeriodType.MINUTE);
            default:
                return null;
        }
    }
    private DataItem buildEmpty(Date head) {
        DataItem dataItem = new DataItem();
        dataItem.setTimeKey(head);
        dataItem.setTotal(0L);
        dataItem.setSuccessCount(0L);
        dataItem.setFailCount(0L);
        return dataItem;
    }
}
