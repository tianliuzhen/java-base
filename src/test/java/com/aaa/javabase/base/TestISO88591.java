package com.aaa.javabase.base;

import java.io.UnsupportedEncodingException;

/**
 * @author liuzhen.tian
 * @version 1.0 TestISO88591.java  2024/3/6 21:47
 */
public class TestISO88591 {

    /**
     * 参考：https://www.cnblogs.com/tonyliult/p/4520475.html
     * <p>
     * utf-8编码可以用gbk和iso8859-1解码后编回去
     * gbk编码后只能用iso8859-1解码后编回去
     * iso编码被utf,gbk编码后。无法解析
     *
     * @param args
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        testIsoToGbk();

        byte[] utf = "测试".getBytes("UTF8");
        byte[] gbk = "测试".getBytes("GBK");
        byte[] iso = "测试".getBytes("iso-8859-1");

        String utf_gbk = new String(utf, "GBK");
        String utf_iso = new String(utf, "iso-8859-1");

        String utf_gbk_gbk_utf = new String(utf_gbk.getBytes("GBK"), "UTF8");
        String utf_iso_ios_utf = new String(utf_iso.getBytes("iso-8859-1"), "UTF8");


        System.out.println("utf被gbk,iso编后：==========");
        System.out.println(utf_gbk);
        System.out.println(utf_iso);

        System.out.println("utf被编回：==========");
        System.out.println(utf_gbk_gbk_utf);
        System.out.println(utf_iso_ios_utf);


        String gbk_utf = new String(gbk, "UTF8");
        String gbk_iso = new String(gbk, "iso-8859-1");

        String gbk_utf_utf_gbk = new String(gbk_utf.getBytes("UTF8"), "GBK");
        String gbk_iso_iso_gbk = new String(gbk_iso.getBytes("iso-8859-1"), "GBK");
        System.out.println("gbk被utf,iso编后：==========");
        System.out.println(gbk_utf);
        System.out.println(gbk_iso);

        System.out.println("gbk被编回：==========");
        System.out.println(gbk_utf_utf_gbk);
        System.out.println(gbk_iso_iso_gbk);


        String iso_utf = new String(iso, "UTF8");
        String iso_gbk = new String(iso, "GBK");

        String iso_utf_utf_iso = new String(iso_utf.getBytes("UTF8"), "iso-8859-1");
        String iso_gbk_utf_iso = new String(iso_gbk.getBytes("GBK"), "iso-8859-1");
        System.out.println("iso被utf,gbk编后：==========");
        System.out.println(iso_utf);
        System.out.println(iso_gbk);

        System.out.println("iso被编回：==========");
        System.out.println(iso_utf_utf_iso);
        System.out.println(iso_gbk_utf_iso);
    }

    private static void testIsoToGbk() throws UnsupportedEncodingException {
        // 集团/蚂蚁集团/数字金融/保险/网关业务
        // éå¢/èèéå¢/æ°å­éè/ä¿é©/ç½å³ä¸å¡
        String testMsg = new String("éå¢/èèéå¢/æ°å­éè/ä¿é©/ç½å³ä¸å¡ "
                .getBytes("iso-8859-1"), "utf-8");
        String testMsg2 = new String("é\u009B\u0086å\u009B¢/è\u009A\u0082è\u009A\u0081é\u009B\u0086å\u009B¢/æ\u0095°å\u00AD\u0097é\u0087\u0091è\u009E\u008D/ä¿\u009Dé\u0099©/ç½\u0091å\u0085³ä¸\u009Aå\u008A¡"
                .getBytes("iso-8859-1"), "utf-8");
        System.out.println(testMsg);
        System.out.println(testMsg2);
    }
}
