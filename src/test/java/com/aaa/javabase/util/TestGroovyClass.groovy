package com.aaa.javabase.util

import com.alibaba.fastjson.JSONArray
import com.google.common.collect.Lists

/**
 * idea 跑Groovy脚本需要 groovy-all 依赖
 *
 <!-- https://mvnrepository.com/artifact/org.apache.groovy/groovy-all -->
 <dependency>
 <groupId>org.apache.groovy</groupId>
 <artifactId>groovy-all</artifactId>
 <version>4.0.9</version>
 <type>pom</type>
 <scope>test</scope>
 </dependency>

 */
class TestGroovyClass {
    def testGroovyMethod() {
        // 数组遍历
        def list = Lists.newArrayList("1", "2", "3")
        for (final def obj in list) {
            println(obj)
        }

        // 字符串转换测试
        def str = "[{\"id\":1,\"name\":\"demoData\",\"desc\":\"demoData\"},{\"id\":2,\"name\":\"demoData2\",\"desc\":\"demoData2\"}]"
        def array = JSONArray.parseArray(str)
        for (final def one in array) {
            println(one)
        }
    }

    // 执行入口
    static void main(String[] args) {
        def test = new TestGroovyClass()
        test.testGroovyMethod()
    }
}
