package com.aaa.javabase.util;

/**
 * 数字转中文大写
 *
 * @author liuzhen.tian
 * @version 1.0 Data2ZhUtil.java  2023/1/17 21:43
 */
public class Data2ZhUtil {
    final static private String NUMBER[] = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    final static private String NUMBER2[] = {"〇", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    final static private String CBit[] = {"", "拾", "佰", "仟"};

    /**
     * 将数值大写
     */
    public static String capitalization(String szNum) {
        StringBuilder resstr = new StringBuilder();
        String tmpstr = szNum.trim();
        int sl = tmpstr.length();
        int sp = 0;
        int dotpos = tmpstr.indexOf('.');
        if (dotpos != -1) {
            while (sl > 1 && tmpstr.charAt(sl - 1) == '0') {
                sl--;
            }
            if (tmpstr.charAt(sl - 1) == '.') {
                sl--;
            }
            if (sl != tmpstr.length()) {
                tmpstr = tmpstr.substring(0, sl);
            }
        } else {
            dotpos = sl;
        }
        if (sl < 1) {
            return NUMBER[0];
        }
        if (tmpstr.charAt(0) == '-') {
            resstr.append("负");
            sp = 1;
        }
        String integerNum = tmpstr.substring(sp, dotpos - sp);
        String decimalNum = "";
        if (dotpos + 1 < sl) {
            decimalNum = tmpstr.substring(dotpos + 1);
        }
        sl = integerNum.length();
        sp = 0;
        while (sp < sl && integerNum.charAt(sp) == '0') {
            sp++;
        }
        if (sp > 0) {
            integerNum = integerNum.substring(sp);
        }
        int inl = integerNum.length();
        if (inl > 0) {
            int h = (inl - 1) % 4;
            int j = (inl - 1) / 4 + 1;
            sp = 0;
            boolean allzero = false;
            boolean preallzero = false;
            for (; j > 0; j--) {
                int k = h;
                h = 3;
                boolean preiszero = allzero;
                allzero = true;
                for (; k >= 0; k--, sp++) {
                    if (integerNum.charAt(sp) == '0') {
                        preiszero = true;
                    } else {
                        allzero = false;
                        if (preiszero) {
                            resstr.append("零");
                        }
                        preiszero = false;
                        resstr.append(NUMBER[(byte) (integerNum.charAt(sp)) - 48]).append(CBit[k]);
                    }
                }
                // end for k
                if (/* j!=0 && */ j % 2 == 0) {
                    if (!allzero) {
                        resstr.append("万");
                    }
                } else {
                    if (!allzero || !preallzero) {
                        int repyi = j / 2;
                        for (int i = 0; i < repyi; i++)
                            resstr.append("亿");
                    }
                }
                preallzero = allzero;
            }
            // end for j
        } else {
            resstr.append("零");
        }

        int dnl = decimalNum.length();
        if (dnl > 0) {
            resstr.append("点");
            for (int i = 0; i < dnl; i++) {
                resstr.append(NUMBER[(byte) (decimalNum.charAt(i)) - 48]);
            }
        }
        return resstr.toString();
    }

    /**
     * 获得某一位上的数值，如果 nBit<0 则获得小数点后面的位数
     */
    static public char getNumByte(String szNum, int nBit) {
        int sl = szNum.length();
        int nPos = 0;
        while (nPos < sl && szNum.charAt(nPos) != '.') {
            nPos++;
        }
        if (nBit < 0) {
            nPos = nPos - nBit;
        } else {
            nPos = nPos - nBit - 1;
        }
        if (nPos < 0 || nPos >= sl) {
            return '0';
        }
        return szNum.charAt(nPos);
    }

    public static String rmbChange(String rmb) {
        return capitalization((rmb.indexOf('.') >= 0 ? rmb.substring(0, rmb.indexOf('.')) : rmb)) + "元"
                + capitalization(String.valueOf(getNumByte(rmb, -1))) + "角"
                + capitalization(String.valueOf(getNumByte(rmb, -2))) + "分";
    }

    /**
     * 仅仅是把 0~9 转换为 "〇","一","二","三","四","五","六","七","八","九"
     */
    public static String changeCN(String szNum) {
        StringBuilder sb = new StringBuilder();
        String str = szNum.trim();
        int sl = str.length();
        int sp = 0;

        if (sl < 1) {
            return NUMBER2[0];
        }
        for (; sp < sl; sp++) {
            if (str.charAt(sp) >= '0' && str.charAt(sp) <= '9') {
                sb.append(NUMBER2[str.charAt(sp) - '0']);
            } else {
                sb.append(str.charAt(sp));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(rmbChange("825.45"));
        System.out.println(rmbChange("-888.999999"));
        System.out.println(changeCN("2018"));
    }
}
