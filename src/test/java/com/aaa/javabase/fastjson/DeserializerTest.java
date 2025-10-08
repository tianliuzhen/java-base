package com.aaa.javabase.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * @author liuzhen.tian
 * @version 1.0 DeserializerTest.java  2025/10/8 20:21
 */
public class DeserializerTest {
}


class StringByteArrayDeserializer implements ObjectDeserializer {
    public static final StringByteArrayDeserializer instance = new StringByteArrayDeserializer();

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        JSONLexer lexer = parser.getLexer();
        lexer.config(Feature.AutoCloseSource, false);
        // parser.getInput():
        // String base64Str = ((JSONScanner) lexer).bytesValue(); // 获取字符串值
        byte[] data = parser.getInput().getBytes(StandardCharsets.UTF_8); // String转byte
        return (T) data;
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }

    // 注册到ParserConfig中
    public static void register(ParserConfig parserConfig) {
        parserConfig.putDeserializer(Byte[].class, instance);
    }

    // 取消注册
    public static void cancelRegister(ParserConfig parserConfig) {
        parserConfig.clearDeserializers();
    }

    public static void main(String[] args) {
        String json = "{\"data\":\"SGVsbG8gd29ybGQ=\"}";

        ParserConfig config = new ParserConfig();
        register(config);
        JSONObject jsonObject = JSON.parseObject(json);

        Object res = JSON.parseObject(json, Byte[].class, config);

        String s = new String((byte[]) res);
        System.out.println(s); // 输出: Hello world
    }
}
