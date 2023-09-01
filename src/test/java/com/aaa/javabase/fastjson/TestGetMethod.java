package com.aaa.javabase.fastjson;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * fastjson几个点注意：
 * <p/>
 * 1. @Setter，@Getter 注解
 * 默认给基本数据类型的boolean类型set方法去掉is，get方法去掉get，而包装类Boolean不影响.
 * 默认给  String isFlag  生成 getIsFlag()\setIsFlag()
 * 默认给  Boolean isSuccess  生成 getSuccess()\setIsSuccess()
 * 默认给  boolean isFail  生成 isFail()\setFail()
 * <p/>
 * 2. 序列化默认执行时get方法
 * 如下面的：public Integer getNameByOpen()没有属性值也能执行getNameByOpen()
 * 但是如果 public void getNameByOpen()是参数则不能执行
 */
public class TestGetMethod {
    @Data
    @AllArgsConstructor
    public static class GetMethod {
        private String name;
        private String add;
        // 序列化之后（getIsXXX方法）：isFlag
        private String isFlag;
        // 序列化之后（getIsXXX方法）：isSuccess
        private Boolean isSuccess;
        // 序列化之后（isXXX方法）：fail
        // @JSONField(name = "isFail")
        private boolean isFail;
        private Date currentDate;


        public Integer getNameByOpen() {
            setName("getNameByOpen");
            return 0;
        }

        public boolean isName() {
            setName("isNameByOpen");
            return true;
        }

    }

    public static void main(String[] args) {
        GetMethod object = new GetMethod("a", "a", "1", true, true, new Date());
        String str = JSONObject.toJSONString(object, SerializerFeature.WriteDateUseDateFormat);
        GetMethod getMethod = JSONObject.parseObject(str, GetMethod.class);
        // BeanConvertUtil.beanTo(new GetMethod(), GetMethod.class);
        System.out.println(str);
    }

}
