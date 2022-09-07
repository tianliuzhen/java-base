package com.aaa.javabase.base.money;

import com.aaa.javabase.domain.Device;
import com.alibaba.fastjson.JSONObject;
import org.javamoney.moneta.FastMoney;
import org.javamoney.moneta.Money;

import javax.money.Monetary;
import java.util.HashMap;

/**
 * 参考：https://cloud.tencent.com/developer/article/1918330
 * <p>
 * 当前JDK中用来表达货币的类为java.util.Currency，这个类仅仅能够表示按照[ISO-4217]描述的货币类型。
 * 它没有与之关联的数值，也不能描述规范外的一些货币。对于货币的计算、货币兑换、货币的格式化没有提供相关的支持，
 * 甚至连能够代表货币金额的标准类型也没有提供相关说明。
 * JSR-354定义了一套标准的API用来解决相关的这些问题。
 *
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2022/8/16 20:53
 */
public class TestMain {
    public static void main(String[] args) {
        FastMoney fm1 = Monetary.getAmountFactory(FastMoney.class).setCurrency("CNY").setNumber(144).create();
        FastMoney fm2 = FastMoney.of(144, "CNY");

        Money m1 = Monetary.getAmountFactory(Money.class).setCurrency("CNY").setNumber(144).create();
        Money m2 = Money.of(144, "CNY");

        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("a", "1");

        HashMap<Object, Object> hashMap2 = new HashMap<>();
        hashMap2.put("a", "1");

        System.out.println(hashMap.hashCode() == hashMap2.hashCode());

        Object obj = "{\"a\":\"1\"}";
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(obj));
        System.out.println();

        Device device = new Device();
        device.setDevDoor("1");
        JSONObject jsonObject2 = JSONObject.parseObject(JSONObject.toJSONString(device));
    }
}
