package com.aaa.javabase.util;

import lombok.Getter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * @author liuzhen.tian
 * @version 1.0 DateUtil.java  2023/10/6 19:58
 */
public class DateUtil {
    private static final ThreadLocal<SimpleDateFormat> THREAD_LOCAL = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static final String YYYY = "yyyy";
    public static final String YYYY_MM = "yyyy-MM";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_DD = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
    public static final String YYYY_MM_DD_HH_DD_SS = "yyyy-MM-dd HH:mm:ss";

    public static Date addMinute(Date date, int minute) {
        return addByDateType(date, minute, DateType.MINUTE);
    }

    public static Date addDay(Date date, int days) {
        return addByDateType(date, days, DateType.DAY);
    }

    public static Date addMonth(Date date, int months) {
        return addByDateType(date, months, DateType.MONTH);
    }


    public static Date addByDateType(Date date, int interval, DateType dateType) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        switch (dateType) {
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


    public enum DateType {
        MINUTE("MINUTE"),
        HOUR("HOUR"),
        DAY("DAY"),
        MONTH("MONTH"),
        YEAR("YEAR"),
        ;

        @Getter
        private String code;

        DateType(String code) {
            this.code = code;
        }

        public static DateType getByCode(String code) {
            return Arrays.stream(values()).filter(e -> e.getCode().equals(code))
                    .findAny()
                    .orElse(null);
        }
    }

    public static Date strToDate(String dateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_DD_SS);
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException("时间格式转换错误");
        }
    }


    public static Date strToDate(String dateStr, String format) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.parse(dateStr);
        } catch (Exception e) {
            throw new RuntimeException("时间格式转换错误");
        }
    }

    public static String dateToStr(Date date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_DD_SS);
            return dateFormat.format(date);
        } catch (Exception e) {
            throw new RuntimeException("时间格式转换错误");
        }
    }

    public static String dateToStrSync(Date date) {
        try {
            SimpleDateFormat dateFormat = THREAD_LOCAL.get();
            return dateFormat.format(date);
        } catch (Exception e) {
            throw new RuntimeException("时间格式转换错误");
        }
    }

    /**
     * date1 > date2 ; return true;
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean greaterThan(Date date1, Date date2) {
        return date1.compareTo(date2) > 0;
    }


    public static void main(String[] args) {
        Date a1 = strToDate("2023-10-21 00:00:00");
        System.out.println(dateToStrSync(addMonth(a1, 3)));
        Date a2 = strToDate("2024-01-21 00:00:00");
        System.out.println(dateToStrSync(addMonth(a2, 3)));

        System.out.println("----------");


        Date b1 = strToDate("2023-10-21 00:00:00");
        for (int i = 0; i < 4; i++) {
            b1 = addMonth(b1, 3);
            System.out.println(dateToStrSync(b1));
        }

    }
}
