package com.aaa.javabase.base.money;

import com.aaa.javabase.domain.MyMoney;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Currency;
import java.util.Locale;

/**
 * @author liuzhen.tian
 * @version 1.0 TestCurrency.java  2022/8/25 21:09
 */
public class TestCurrency {
    public static void main(String[] args) {
        MyMoney myMoney = new MyMoney();
        Currency cny = Currency.getInstance("CNY");
        Currency instance = Currency.getInstance(Locale.CHINA);
        myMoney.setCent(100L);
        myMoney.setCurrency(cny);
        /**
         * {"cent":100,"currency":"CNY"}
         */
        // fastJson 默认忽略：transient 修饰变量
        String jsonString = JSONObject.toJSONString(myMoney);

        Object json = JSONObject.toJSON(myMoney);

        fastjsonWithSkipTransient(myMoney);
        System.out.println();
    }

    public static void fastjsonWithSkipTransient(MyMoney myMoney) {
        SerializeWriter out = new SerializeWriter();
        JSONSerializer jsonSerializer = new JSONSerializer(out);
        jsonSerializer.config(SerializerFeature.SkipTransientField, true);
        jsonSerializer.write(myMoney);
        String string = out.toString();
    }
}
