package com.aaa.javabase.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.CronExpression;
import org.quartz.TriggerUtils;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.scheduling.support.CronSequenceGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author liuzhen.tian
 * @version 1.0 CronExpressionUtil.java  2023/11/27 21:55
 */
@Slf4j
public class CronExpressionUtil {
    public static void main(String[] args) {
        /**
         0 0 12 * * ?    // 每天中午12点执行
         0 0/2 * * * ?   // 每俩分钟执行
         0 0/10 * * * ?  // 每10分钟执行
         */
        String cronExpression = "0 0/5 * * * ?";
        // 查询最近一次时间
        Date nextRunTime = getNextExecuteTime(cronExpression);
        System.out.println(DateUtil.dateToStr(nextRunTime));
        Date date2 = DateUtil.addMinute(new Date(), 1);
        System.out.println(DateUtil.greaterThan(nextRunTime, date2));
        checkCronRangeLimit(cronExpression, 60 * 5);
        System.out.println("-----");

        // 列出最新执行的时间 - Quartz
        List<Date> nextExecuteTime = selectCronRunTimeByQuartz(cronExpression, 10);
        nextExecuteTime.forEach(e -> System.out.println(DateUtil.dateToStr(e)));
        System.out.println("-----");

        // 列出最新执行的时间 - spring
        List<Date> dates = selectCronRunTimeBySpring(cronExpression, 10);
        dates.forEach(forEachWithIndex((date, index) -> {
            System.out.println(index + "-" + DateUtil.dateToStr(date));
        }));
    }

    /**
     * 校验指定cron时间范围
     * <p/>
     * cronExpression=0 0/2 * * * ? （俩分钟执行一次）
     * seconds = 60*2 --- 异常
     *
     * @param cronExpression
     * @param seconds
     */
    public static void checkCronRangeLimit(String cronExpression, long seconds) {
        List<Date> dates = selectCronRunTimeByQuartz(cronExpression, 2);
        if ((dates.get(1).getTime() - dates.get(0).getTime()) >= seconds * 1000) {
            throw new RuntimeException("Cron的间隔不在规定内,Cron: " + cronExpression);
        }
    }

    /**
     * 基于Quartz获取下一次的执行时间
     *
     * @param cronExpression
     */
    private static Date getNextExecuteTime(String cronExpression) {
        if (!CronExpression.isValidExpression(cronExpression)) {
            throw new RuntimeException("Cron格式不正确,Cron: " + cronExpression);
        }
        try {
            CronExpression cron = new CronExpression(cronExpression);
            return cron.getNextValidTimeAfter(new Date());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 基于Quartz查询cron最近执行的时间
     *
     * @param cronExpression cron表达式
     * @param numTimes       下几次运行时间
     * @return
     */
    public static List<Date> selectCronRunTimeByQuartz(String cronExpression, int numTimes) {
        if (!CronExpression.isValidExpression(cronExpression)) {
            throw new RuntimeException("Cron格式不正确,Cron: " + cronExpression);
        }
        CronTriggerImpl cronTriggerImpl = new CronTriggerImpl();
        try {
            cronTriggerImpl.setCronExpression(cronExpression);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return TriggerUtils.computeFireTimes(cronTriggerImpl, null, numTimes);
    }

    /**
     * 基于Spring查询cron最近执行的时间
     *
     * @param cronExpression cron表达式
     * @param numTimes       下几次运行时间
     * @return
     */
    public static List<Date> selectCronRunTimeBySpring(String cronExpression, int numTimes) {
        cronValidBySpring(cronExpression);

        CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(cronExpression);
        List<Date> cronTimeList = new ArrayList<>();
        Date nextTimePoint = new Date();
        for (int i = 0; i < numTimes; i++) {
            nextTimePoint = cronSequenceGenerator.next(nextTimePoint);
            cronTimeList.add(nextTimePoint);
        }
        return cronTimeList;
    }


    /**
     * Java8 通过foreach 遍历List，同时输出下标
     * 利用BiConsumer实现foreach循环支持index
     *
     * @param biConsumer
     * @param <T>
     * @return
     */
    public static <T> Consumer<T> forEachWithIndex(BiConsumer<T, Integer> biConsumer) {
        /* 这里说明一下，我们每次传入forEach都是一个重新实例化的Consumer对象，在lambada表达式中我们无法对int进行++操作,
        我们模拟AtomicInteger对象，写个getAndIncrement方法，不能直接使用AtomicInteger */
        class IncrementInt {
            int i = 0;

            public int getAndIncrement() {
                return i++;
            }
        }
        IncrementInt incrementInt = new IncrementInt();
        return t -> biConsumer.accept(t, incrementInt.getAndIncrement());
    }

    /**
     * 检验表达式是否正确-spring(org.springframework.scheduling.support.CronSequenceGenerator)
     *
     * @param cron cron表达式
     * @return 正确:true
     */
    public static boolean cronValidBySpring(String cron) {
        return StringUtils.isNotBlank(cron) && CronSequenceGenerator.isValidExpression(cron);
    }

    /**
     * 检验表达式是否正确-quartz(org.quartz.CronExpression)
     *
     * @param cron cron表达式
     * @return 正确:true
     */
    public static boolean cronValidByQuartz(String cron) {
        return StringUtils.isNotBlank(cron) && CronExpression.isValidExpression(cron);
    }
}
