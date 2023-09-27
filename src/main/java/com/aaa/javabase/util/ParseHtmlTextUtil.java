package com.aaa.javabase.util;

import org.jsoup.Jsoup;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liuzhen.tian
 * @version 1.0 ParseHtmlTextUtil.java  2023/9/19 22:07
 */
public class ParseHtmlTextUtil {
    private static String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
    private static String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
    private static String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式
    private static Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
    private static Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
    private static Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);

    /**
     * 正则方法来剔除html标签
     *
     * @param htmlStr
     * @return
     */
    public static String delHTMLTag(String htmlStr) {
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); //过滤script标签

        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); //过滤style标签

        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); //过滤html标签
        return htmlStr.trim(); //返回文本字符串
    }

    /**
     * 使用JDK自带的HTML解析器
     */
    public static class JavaHtmlParser extends HTMLEditorKit.ParserCallback {
        private static JavaHtmlParser test = new JavaHtmlParser();
        private StringBuffer buff;

        private void parse(String str) throws IOException {
            InputStream iin = new ByteArrayInputStream(str.getBytes());
            Reader in = new InputStreamReader(iin);
            buff = new StringBuffer();
            ParserDelegator delegator = new ParserDelegator();
            delegator.parse(in, this, Boolean.TRUE);
            iin.close();
            in.close();
        }

        @Override
        public void handleText(char[] text, int pos) {
            buff.append(text);
        }

        private String getText() {
            return buff.toString();
        }

        public static String getContent(String str) {
            try {
                test.parse(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return test.getText();
        }

    }

    public static void main(String[] args) {
        String htmlStr = "<font red='color'>这是标题</font>";
        // 正则过滤
        System.out.println(delHTMLTag(htmlStr));

        // 基于jsoup依赖
        System.out.println(Jsoup.parse(htmlStr).text());

        // JDK自带的HTML解析器
        System.out.println(JavaHtmlParser.getContent(htmlStr));
    }
}
