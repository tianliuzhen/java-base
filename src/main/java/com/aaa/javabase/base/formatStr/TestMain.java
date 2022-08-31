package com.aaa.javabase.base.formatStr;

import com.alibaba.fastjson.JSON;

import java.text.MessageFormat;
import java.util.HashMap;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2022/8/31 20:33
 */
public class TestMain {
    public static void main(String[] args) {
        // String类的format()方法用于创建格式化的字符串以及连接多个字符串对象。
        System.out.println(String.format("%s环境不支持%s操作！", "dev", "add"));

        String url = "http://xxx.xxx.xxx?name={0}&age={1}";
        String path = MessageFormat.format(url, "张三", 18);
        System.out.println(path);//http://xxx.xxx.xxx?name=张三&age=18


        // MessageFormat 提供了以与语言无关方式生成连接消息的方式。使用此方法构造向终端用户显示的消息。
        System.out.println(MessageFormat.format("req: {0}", JSON.toJSONString(new HashMap<>())));

        String url2 = "http://xxx.xxx.xxx?name=%s&age=%d";
        String path2 = String.format(url2, "张三", 18);
        System.out.println(path2);//http://xxx.xxx.xxx?name=张三&age=18


    }
}
