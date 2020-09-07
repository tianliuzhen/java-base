package com.aaa.javabase.common.dateEmptyHandler.util;


import com.aaa.javabase.common.dateEmptyHandler.domain.PeriodType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/5/28
 */
public class DateUtil {
    public static final String YYYY = "yyyy";
    public static final String YYYY_MM = "yyyy-MM";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_DD = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
    public static final String YYYY_MM_DD_HH_DD_SS = "yyyy-MM-dd HH:mm:ss";


    public static Date dateTransfer(Date date, PeriodType periodType) {
        switch (periodType) {
            case YEAR:
                return transferToTarget(date, YYYY);
            case DAY:
                return transferToTarget(date, YYYY_MM_DD);
            case MONTH:
                return transferToTarget(date, YYYY_MM);
            case HOUR:
                return DateUtil.transferToTarget(date, YYYY_MM_DD_HH);
            case MINUTE:
                return DateUtil.transferToTarget(date,YYYY_MM_DD_HH_DD);
            default:
                return null;
        }

    }

    public static Date transferToTarget(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        String dateString = dateFormat.format(date);

        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException("时间格式转换错误");
        }
    }

    public static Date addByPeriodType(Date date, int interval,PeriodType periodType) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        switch (periodType){
            case YEAR:
                c.add(Calendar.YEAR, interval);
                break;
            case DAY:
                c.add(Calendar.DATE, interval);
                break;
            case MONTH:
                c.add(Calendar.MONTH, interval);
                break;
            case HOUR:
                c.add(Calendar.HOUR, interval);
                break;
            case MINUTE:
                c.add(Calendar.MINUTE, interval);
                break;
            default:
        }
        return c.getTime();
    }
    public static int monthInterval(Date end, Date start) {
        Calendar endC = Calendar.getInstance();
        endC.setTime(end);
        Calendar startC = Calendar.getInstance();
        startC.setTime(start);
        int year = (endC.get(Calendar.YEAR) - startC.get(Calendar.YEAR)) * 12;
        int month = endC.get(Calendar.MONTH) - startC.get(Calendar.MONTH);

        return Math.abs(year + month);
    }

    public static int yearInterval(Date end, Date start) {
        Calendar endC = Calendar.getInstance();
        endC.setTime(end);
        Calendar startC = Calendar.getInstance();
        startC.setTime(start);
        int year = (endC.get(Calendar.YEAR) - startC.get(Calendar.YEAR));

        return Math.abs(year);
    }

    public static int dayInterval(Date end, Date start) {
        long inverval = Math.abs(end.getTime() - start.getTime());
        return (int) (inverval / (24 * 60 * 60 * 1000));
    }

    /**
     * 获取当前月的开始时间
     *
     * @param
     * @return
     */
    public static Date getThisMonthStart(){
        Calendar calendar = Calendar.getInstance();
        //定义数组用于存放起始时间[0]和结束时间[1]
        //获取当月前的月的起始时间
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1, 0, 0, 0);
        Date time = calendar.getTime();
        return time;
    }
}
