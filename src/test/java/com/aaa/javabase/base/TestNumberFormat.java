package com.aaa.javabase.base;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 测试小数转化
 *
 * @author liuzhen.tian
 * @version 1.0 TestNumberFormat.java  2022/8/1 21:37
 */
public class TestNumberFormat {
    public static void main(String[] args) {
        double f = 111231.5585;
        BigDecimal b = new BigDecimal(f);
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(f1);

        double b2 = 12310;
        DecimalFormat df = new DecimalFormat("#.00");
        String format = df.format(b2 / 100);
        System.out.println(format);

        // 按指定百分比格式输出
        NumberFormat deciFormat = new DecimalFormat();
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(2);
        System.out.println(nf.format(0.34577));


        double d = 3.1415926;
        String result = String.format("%.2f", d);
        System.out.println(result);

        double x = 23.5455;
        NumberFormat ddf1 = NumberFormat.getNumberInstance();
        ddf1.setMaximumFractionDigits(2);
        String s = ddf1.format(x);
        System.out.print(s);
    }
}
