package com.aaa.javabase.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;

/**
 * @author liuzhen.tian
 * @version 1.0 DateUtils.java  2020/10/27 15:19
 */
public class DateUtilsLocalV1 {

    /**
     * 获取 本月 月初月末
     * type =1 start
     * type =2  end
     * @param type
     *
     */
    public static String getMonthDateByType(int type){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar= Calendar.getInstance();
        if (type==1) {
            calendar.add(Calendar.MONTH, 0);
        }
        if (type==2) {
            calendar.add(Calendar.MONTH, 1);
        }
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return format.format(calendar.getTime());
    }

    /**
     * 获取 今天是当前月多少天
     */
    public static int getTotalDayByMonth(){
        Calendar calendar= Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * LocalDate 获取月长度
     */
    public static int countDaysInMonth(Month month){
        LocalDate now = LocalDate.now();
        LocalDate localDate = now.withMonth(month.getValue());
        return localDate.lengthOfMonth();
    }

    /**
     *  Date 获取月长度
     */
    public static int countDaysInMonthV2(){
        //计算下一个月
        Calendar calendar= Calendar.getInstance();
        //获得当前日期往后推1个月 amount 为设置的月份值 +为往后推  +号可以省略  -为往前推
        calendar.add(Calendar.MONTH,0);
        //获得下一个月 因为默认月是从0月开始所以要+1
        Integer month=calendar.get(Calendar.MONTH)+1;
        //获得下一个月是多少年
        int year = calendar.get(Calendar.YEAR);
        calendar.set(year,month,0);
        //获得下一个月有多少天
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(year+"年"+month+"月有"+day+"天");
        return day;
    }

}
