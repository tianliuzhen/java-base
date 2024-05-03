package com.aaa.javabase.util;

import org.junit.jupiter.api.Test;

/**
 * @author liuzhen.tian
 * @version 1.0 FrokJoinPool.java  2024/4/30 20:58
 */
public class ForkJoinPoolTest {
    int AC_SHIFT = 48;
    int TC_SHIFT = 32;
    long AC_MASK = 65535L << AC_SHIFT;
    long TC_MASK = 65535L << TC_SHIFT;

    long AC_UNIT = 1L << AC_SHIFT; // 48
    long TC_UNIT = 1L << TC_SHIFT; // 32

    long SP_MASK = 0xffffffffL; // 4294967295
    final long UC_MASK = ~SP_MASK; // -4294967295


    long np = -7;

    int SS_SEQ = 1 << 16;       // version count
    int INACTIVE = 1 << 31;       // must be negative

    @Test
    public void test1() {
        long left = (np << AC_SHIFT) & AC_MASK;
        long right = (np << TC_SHIFT) & TC_MASK;
        long c = left | right;
        int stackPred = -2147483609;
        long nc = (UC_MASK & (c + AC_UNIT)) | (SP_MASK & stackPred);
        System.out.println("UC_MASK       = " + getBinaryString(UC_MASK));
        System.out.println("(c + AC_UNIT) = " + getBinaryString((c + AC_UNIT)));
        System.out.println("SP_MASK       = " + getBinaryString(SP_MASK));
        System.out.println("v.stackPred   = " + getBinaryString(stackPred));
        System.out.println("nc            = " + getBinaryString(nc));
        int sp = (int) c;
        int vs = (sp + SS_SEQ) & ~INACTIVE;
        // next scanState
        System.out.println("c                   = " + getBinaryString(c));
        System.out.println("SS_SEQ              = " + getBinaryString(SS_SEQ));
        System.out.println("((int) c + SS_SEQ)  = " + getBinaryString((sp + SS_SEQ)));
        System.out.println("~INACTIVE           = " + getBinaryString(~INACTIVE));
        System.out.println("vs                  = " + getBinaryString(vs));
    }

    @Test
    public void test2() {
        long left = (np << AC_SHIFT) & AC_MASK;
        long right = (np << TC_SHIFT) & TC_MASK;
        long ctl = left | right;
        System.out.println("np << AC_SHIFT = " + getBinaryString(np << AC_SHIFT));
        System.out.println("&");
        System.out.println("AC_MASK        = " + getBinaryString(AC_MASK));
        System.out.println("|");
        System.out.println("np << TC_SHIFT = " + getBinaryString(np << TC_SHIFT));
        System.out.println("&");
        System.out.println("TC_MASK        = " + getBinaryString(TC_MASK));
        System.out.println("最终结果");
        System.out.println("ctl-left       = " + getBinaryString(left));
        System.out.println("ctl-right      = " + getBinaryString(right));
        System.out.println("ctl            = " + getBinaryString(ctl));
        System.out.println("ADD_WORKER     = " + getBinaryString(0x0001L << (TC_SHIFT + 15)));
        /**
         * 1111111111111001000000000000000000000000000000000000000000000000
         * |
         * 0000000000000000111111111111100100000000000000000000000000000000
         * ctl=
         * 1111111111111001111111111111100100000000000000000000000000000000
         * & ADD_WORKER
         * 0000000000000000100000000000000000000000000000000000000000000000
         */

        System.out.println("AC_MASK        = " + getBinaryString(AC_MASK));
        System.out.println("TC_MASK        = " + getBinaryString(TC_MASK));
        System.out.println("ctl0           = " + getBinaryString(ctl));
        ctl = getCtl(AC_MASK, TC_MASK, ctl, AC_UNIT, TC_UNIT);
        System.out.println("ctl1           = " + getBinaryString(ctl));
        ctl = getCtl(AC_MASK, TC_MASK, ctl, AC_UNIT, TC_UNIT);
        System.out.println("ctl2           = " + getBinaryString(ctl));
        ctl = getCtl(AC_MASK, TC_MASK, ctl, AC_UNIT, TC_UNIT);
        System.out.println("ctl3           = " + getBinaryString(ctl));
        ctl = getCtl(AC_MASK, TC_MASK, ctl, AC_UNIT, TC_UNIT);
        System.out.println("ctl4           = " + getBinaryString(ctl));
        ctl = getCtl(AC_MASK, TC_MASK, ctl, AC_UNIT, TC_UNIT);
        System.out.println("ctl5           = " + getBinaryString(ctl));
        ctl = getCtl(AC_MASK, TC_MASK, ctl, AC_UNIT, TC_UNIT);
        System.out.println("ctl7           = " + getBinaryString(ctl));
        ctl = getCtl(AC_MASK, TC_MASK, ctl, AC_UNIT, TC_UNIT);
        System.out.println("ctl8           = " + getBinaryString(ctl));
        /**
         * 验证ctl的自增 (每次 ctl的自增时，前16位依次+1)
         ctl0           = 1111111111111001(1) 1111111111111001 0000000000000000 0000000000000000
         ctl1           = 1111111111111010(2) 1111111111111010 0000000000000000 0000000000000000
         ctl2           = 1111111111111011(3) 1111111111111011 0000000000000000 0000000000000000
         ctl3           = 1111111111111100(4) 1111111111111100 0000000000000000 0000000000000000
         ctl4           = 1111111111111101(5) 1111111111111101 0000000000000000 0000000000000000
         ctl5           = 1111111111111110(6) 1111111111111110 0000000000000000 0000000000000000
         ctl7           = 1111111111111111(7) 1111111111111111 0000000000000000 0000000000000000
         ctl8           = 0
         */
    }

    private static long getCtl(long AC_MASK, long TC_MASK, long ctl, long AC_UNIT, long TC_UNIT) {
        // return (AC_MASK & (ctl + AC_UNIT)) | (TC_MASK & (ctl + TC_UNIT));
        System.err.println("(ctl + AC_UNIT) = " + getBinaryString(ctl + AC_UNIT));
        System.err.println("(AC_UNIT)       = " + getBinaryString(AC_UNIT));
        return (AC_MASK & (ctl + AC_UNIT));
    }

    private static String getBinaryString(long AC_MASK) {
        String binaryNumber = Long.toBinaryString(AC_MASK);
        if (binaryNumber.equals("0")) {
            return binaryNumber;
        }

        String result = String.format("%64s", binaryNumber).replace(' ', '0');
        StringBuilder separatedNumber = new StringBuilder();
        for (int i = 0; i < result.length(); i += 16) {
            separatedNumber.append(result.substring(i, i + 16)).append(" ");
        }
        result = separatedNumber.toString().trim(); // 移除最后一个多余的空格
        // result= result.replaceAll("(.{16})", "$1 ").trim(); // 用正则也能实现
        return result;
    }
    private static String getBinaryString(int AC_MASK) {
        String binaryNumber = Integer.toBinaryString(AC_MASK);
        if (binaryNumber.equals("0")) {
            return binaryNumber;
        }

        String result = String.format("%64s", binaryNumber).replace(' ', '0');
        StringBuilder separatedNumber = new StringBuilder();
        for (int i = 0; i < result.length(); i += 16) {
            separatedNumber.append(result.substring(i, i + 16)).append(" ");
        }
        result = separatedNumber.toString().trim(); // 移除最后一个多余的空格
        // result= result.replaceAll("(.{16})", "$1 ").trim(); // 用正则也能实现
        return result;
    }

}
