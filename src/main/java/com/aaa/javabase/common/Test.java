package com.aaa.javabase.common;


import com.aaa.javabase.common.domain.DataItem;
import com.aaa.javabase.common.domain.ParameterItem;
import com.aaa.javabase.common.domain.PeriodType;
import com.aaa.javabase.common.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/5/28
 */
public class Test {
    public static void main(String[] args) {

        testDays();
        // testMonths();
        // TODO: 2020/5/28  列表分页问题

    }

    private static void testDays() {
        ParameterItem pitems = new ParameterItem();
        pitems.setFrom(new Date(1588043311 * 1000L));// 2020-04-28 11:08:31
        pitems.setTo(new Date(1588216111 * 1000L)); // 2020-04-30 11:08:31
        pitems.setPeriodType(PeriodType.DAY);
        /**
         * 这样查的是 eg：  【 04-28 ，04-30） ，不包含 04-30 ，
         * 倘如都是闭区间，如何修改 ，两种办法
         *                 1、修改 limit +1 即可
         *                 2、或者 修改 emptyCompletion  里的    while (realList.size() < limit && (head.before(end) || head.equals(end)))
         *                 为  while (realList.size() <= limit && (head.before(end) || head.equals(end)))
         */
        pitems.setLimit(DateUtil.dayInterval(pitems.getFrom(), pitems.getTo())+1);
        HandlerEmpty handlerEmpty = new HandlerEmpty();
        List<DataItem> dataItemList = new ArrayList<>();           // 2020-04-29 11:08:31
        dataItemList.add(new DataItem(1L, DateUtil.dateTransfer(new Date(1588129711 * 1000L),PeriodType.DAY), 9L, 9L, 9L));
        List<DataItem> dataItems = handlerEmpty.emptyCompletion(pitems, dataItemList);
        System.out.println(dataItems);
    }

    private static void testMonths() {
        ParameterItem pitems = new ParameterItem();
        pitems.setFrom(new Date(1583291311000L)); // 2020-03-04 11:08:31
        pitems.setTo(new Date(1591240111000L)); // 2020-06-04 11:08:31
        pitems.setPeriodType(PeriodType.MONTH);
        pitems.setLimit(DateUtil.monthInterval(pitems.getFrom(),pitems.getTo()));
        // 如果需要当前月 limit + 1 即可
        if(( pitems.getTo()).after(DateUtil.getThisMonthStart())){
            pitems.setLimit(pitems.getLimit() + 1);
        }
        HandlerEmpty handlerEmpty = new HandlerEmpty();
        List<DataItem> dataItemList = new ArrayList<>();
        dataItemList.add(new DataItem(1L,DateUtil.dateTransfer( new Date(1588262400000L),PeriodType.MONTH), 9L, 9L, 9L));
        List<DataItem> dataItems = handlerEmpty.emptyCompletion(pitems, dataItemList);
        System.out.println(dataItems);

    }
}
